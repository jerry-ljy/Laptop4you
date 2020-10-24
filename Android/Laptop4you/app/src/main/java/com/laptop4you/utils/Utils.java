package com.laptop4you.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    private Context mContext;
    private Map<String, float[]> mClusters;

    public Utils(){}
    public Utils(Context context){
        this.mContext=context;
    }

    public void initDatabase(){
        String appDataPath = mContext.getApplicationInfo().dataDir;
        File dbFolder = new File(appDataPath + "/databases");//Make sure the /databases folder exists
        dbFolder.mkdir();//This can be called multiple times.

        File dbFilePath = new File(appDataPath + "/databases/LaptopDB.db");

        try {
            InputStream inputStream = mContext.getAssets().open("LaptopDB.db");
            OutputStream outputStream = new FileOutputStream(dbFilePath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer))>0)
            {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e){
            //handle
            e.printStackTrace();
        }
    }

    public void initClusters(){
        mClusters = new HashMap<>();
        try{
            Database db = new Database(mContext);
            db.open();
            Cursor cursor = db.exeSql("select * from clusters");
            if (cursor.moveToFirst()){
                do{
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    float[] cords={cursor.getFloat(cursor.getColumnIndex("cord_1")),
                            cursor.getFloat(cursor.getColumnIndex("cord_2")),
                            cursor.getFloat(cursor.getColumnIndex("cord_3")),
                            cursor.getFloat(cursor.getColumnIndex("cord_4"))};
                    mClusters.put(name,cords);
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String getCluster(int[] req){
        double distance = Double.MAX_VALUE;
        String cluster="";
        for(String cluster_name:mClusters.keySet()){
            float[] clusterCenter = mClusters.get(cluster_name);
            double temp = getEuclideanDis(req,clusterCenter);
            if(temp<distance){
                distance=temp;
                cluster=cluster_name;
            }
        }
        return cluster;
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

    public int[] refreshUserReq(String answer, int[] currentReq){
        int[] new_config;
        int low = 0;
        int mid = 1;
        int high = 2;
        switch (answer) {
            case "moba":{
                new_config = new int[]{mid,low,mid,mid};
                break;
            }
            case "mmorpg": case "code":{
                new_config = new int[]{high,high,high,high};
                break;
            }
            case "smallgame":{
                new_config = new int[]{low,low,mid,low};
                break;
            }
            case "design":{
                new_config = new int[]{mid,high,high,mid};
                break;
            }
            case "office":{
                new_config = new int[]{low,low,mid,mid};
                break;
            }
            case "video" :{
                new_config = new int[]{low,low,low,mid};
                break;
            }
            default: {
                new_config = new int[]{low,low,low,low};
                break;
            }
        }

        for(int i = 0; i < 4; i++){
            if(new_config[i] <= currentReq[i]){
                 new_config[i] = currentReq[i];
            }
        }
        return new_config;
    }

    public ArrayList<Laptop> getLaptops(String cluster, int minPrice, int maxPrice){
        ArrayList<String> uuidList = getUuids(cluster,minPrice,maxPrice);
        ArrayList<Laptop> laptops = new ArrayList<>();
        Database db = new Database(mContext);
        db.open();
        for(String uuid:uuidList){
            Cursor laptopCursor = db.exeSql("SElECT * FROM LAPTOPS WHERE UUID='"+uuid+"';");
            if (laptopCursor.moveToFirst()){
                do{
                    String name = laptopCursor.getString(laptopCursor.getColumnIndex("name"));
                    String price =laptopCursor.getString(laptopCursor.getColumnIndex("price"));
                    String processor = laptopCursor.getString(laptopCursor.getColumnIndex("processor"));
                    int memory = laptopCursor.getInt(laptopCursor.getColumnIndex("memory"));
                    String hard_drive = laptopCursor.getString(laptopCursor.getColumnIndex("hard_drive"));
                    String graphics = laptopCursor.getString(laptopCursor.getColumnIndex("graphics"));
                    String img = laptopCursor.getString(laptopCursor.getColumnIndex("img"));
                    Laptop laptop = new Laptop(uuid,name,price,processor,memory,hard_drive,graphics,img);
                    laptops.add(laptop);
                }while(laptopCursor.moveToNext());
            }

            laptopCursor.close();
        }
        db.close();
        return laptops;
    }

    private ArrayList<String> getUuids(String cluster, int minPrice, int maxPrice){
        if(cluster=="" || (minPrice==-1 && maxPrice==-1)){
            return null;
        }

        ArrayList<String> uuidList = new ArrayList<>();
        Database db = new Database(mContext);
        db.open();
        Cursor cursor=null;
        if(minPrice==-1){
            cursor= db.exeSql("SElECT * FROM "+cluster+" WHERE PRICE<="+maxPrice+";");
        }else if(maxPrice==-1){
            cursor= db.exeSql("SElECT * FROM "+cluster+" WHERE PRICE>="+minPrice+";");
        }else{
            cursor= db.exeSql("SElECT * FROM "+cluster+" WHERE PRICE>="+minPrice+" and PRICE<="+maxPrice+";");
        }

        if (cursor.moveToFirst()){
            do{
                String uuid = cursor.getString(cursor.getColumnIndex("uuid"));
                uuidList.add(uuid);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return uuidList;
    }
}