package com.example.francisco.androidfragmentrecycleview.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "items.db";

    private static final String PRIMARY_KEY_FIELD ="_id integer PRIMARY KEY AUTOINCREMENT";

    public DbHelper(Context context){
        super(context, DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(String.format("CREATE TABLE %s  ( %s , %s, %s )",
                DbSchema.ItemTable.NAME,
                PRIMARY_KEY_FIELD,
                DbSchema.ItemTable.Columns.UUID,
                DbSchema.ItemTable.Columns.TITLE,
                DbSchema.ItemTable.Columns.DATE,
                DbSchema.ItemTable.Columns.SOLVED
                ));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
