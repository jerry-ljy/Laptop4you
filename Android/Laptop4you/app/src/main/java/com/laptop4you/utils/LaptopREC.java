package com.laptop4you.utils;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaptopREC {
    private String db_url = "jdbc:sqlite:"+ System.getProperty("user.dir")+"\\data\\LaptopDB.db";
    private Map<String, float[]> clusters;
    private Database database;

    public LaptopREC(Database db){
        this.clusters = new HashMap<>();
        this.database = db;
        init();
    }

    private void init(){
        database.open();
        Cursor cursor = database.exeSql("SElECT * FROM CLUSTERS;");
        if (cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                float[] cords={cursor.getFloat(cursor.getColumnIndex("cord_1")),
                        cursor.getFloat(cursor.getColumnIndex("cord_2")),
                        cursor.getFloat(cursor.getColumnIndex("cord_3")),
                        cursor.getFloat(cursor.getColumnIndex("cord_4"))};
                this.clusters.put(name,cords);
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        Log.d("LaptopREC", "Get clusters successfully");
    }

    public ArrayList<Laptop> getLaptopRECList(int[] requirement, float price){
        ArrayList<Laptop> laptops = new ArrayList<>();

        double distance = Double.MAX_VALUE;
        String cluster="";
        for(String cluster_name:this.clusters.keySet()){
            double temp = getEuclideanDis(requirement,this.clusters.get(cluster_name));
            if(temp<distance){
                distance=temp;
                cluster=cluster_name;
            }
        }
        Log.d("Inferenced cluster", cluster);

        database.open();
        Cursor cursor = database.exeSql("SElECT * FROM "+cluster+" WHERE PRICE<"+price+";");
        if (cursor.moveToFirst()){
            do{
                String laptop_uuid = cursor.getString(cursor.getColumnIndex("uuid"));
                Laptop laptop = new Laptop(laptop_uuid,database);
                laptops.add(laptop);
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return laptops;
    }

    private double getEuclideanDis(int[] requirement, float[] cluster_center) {
        double count_dis = 0;

        if (requirement.length != cluster_center.length) {
            throw new IllegalArgumentException("length of array must be equal!");
        }

        for (int i = 0; i < requirement.length; i++) {
            count_dis += Math.pow(requirement[i] - cluster_center[i], 2);
        }
        return Math.sqrt(count_dis);
    }
}
