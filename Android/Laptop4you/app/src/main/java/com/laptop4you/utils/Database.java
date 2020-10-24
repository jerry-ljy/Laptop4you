package com.laptop4you.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Database {
    private final Context context;
    private SQLiteDatabase database;
    private DatabaseOpenHelper dbHelper;

    public Database(Context context){
        this.context = context;
        dbHelper = new DatabaseOpenHelper(context);
    }

    public Database open() throws SQLException
    {
        dbHelper.openDataBase();
        dbHelper.close();
        database = dbHelper.getReadableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public Cursor exeSql(String sql){
        try{
            Cursor cursor = database.rawQuery(sql,null);
            return cursor;
        }catch (SQLException e){

        }
        return null;
    }
}