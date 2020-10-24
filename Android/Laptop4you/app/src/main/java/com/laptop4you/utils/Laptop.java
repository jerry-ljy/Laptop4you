package com.laptop4you.utils;
import android.database.Cursor;
import android.util.Log;

public class Laptop {
    private String uuid;
    public String name;
    public String price;
    public String processor;
    public int memory;
    public String hard_drive;
    public String graphics;
    public String img;
    private Database database;


    public Laptop(String uuid, Database db){
        this.uuid=uuid;
        this.database=db;
        init();
    }

    private void init(){
        database.open();
        Cursor cursor = database.exeSql("SElECT * FROM LAPTOPS WHERE UUID='"+this.uuid+"';");
        if (cursor.moveToFirst()){
            do{
                this.name = cursor.getString(cursor.getColumnIndex("name"));
                this.price = cursor.getString(cursor.getColumnIndex("price"));
                this.processor = cursor.getString(cursor.getColumnIndex("processor"));
                this.memory=cursor.getInt(cursor.getColumnIndex("memory"));
                this.hard_drive=cursor.getString(cursor.getColumnIndex("hard_drive"));
                this.graphics=cursor.getString(cursor.getColumnIndex("graphics"));
                this.img=cursor.getString(cursor.getColumnIndex("img"));
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        Log.d("laptop",name+price+processor+memory+hard_drive+graphics+img);
    }

    public String getUuid() {
        return uuid;
    }
}
