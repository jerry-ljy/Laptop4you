package com.laptop4you.utils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "LaptopDB.db";
    public static final String DB_SUB_PATH = "/databases/" + DB_NAME;
    private static String APP_DATA_PATH = "";
    private SQLiteDatabase dataBase;
    private final Context context;

    public DatabaseOpenHelper(Context context){
        super(context, DB_NAME, null, 1);
        APP_DATA_PATH = context.getApplicationInfo().dataDir;
        this.context = context;
        copyDBFile();
    }

    public boolean openDataBase() throws SQLException {
        String mPath = APP_DATA_PATH + DB_SUB_PATH;
        //Note that this method assumes that the db file is already copied in place
        dataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);
        return dataBase != null;
    }

    @Override
    public synchronized void close(){
        if(dataBase != null) {dataBase.close();}
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    private void copyDBFile(){
        String appDataPath = context.getApplicationInfo().dataDir;
        File dbFolder = new File(appDataPath + "/databases");//Make sure the /databases folder exists
        dbFolder.mkdir();//This can be called multiple times.

        File dbFilePath = new File(appDataPath + "/databases/LaptopDB.db");

        try {
            InputStream inputStream = context.getAssets().open("LaptopDB.db");
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
        }
    }
}