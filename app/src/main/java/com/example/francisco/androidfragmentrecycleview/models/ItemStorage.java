package com.example.francisco.androidfragmentrecycleview.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.francisco.androidfragmentrecycleview.database.DbHelper;
import com.example.francisco.androidfragmentrecycleview.database.DbSchema;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by francisco on 2018-05-28.
 */

public class ItemStorage {

    static ItemStorage sItemStorage;
    private ArrayList<Item> items;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    private ItemStorage(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DbHelper(mContext).getWritableDatabase();
        items = new ArrayList<Item>();
        for (int i=0; i<20; i++){
            Item item=new Item();
            item.setTitle(String.format("Item #%02d",i+1));
            item.setDate(Calendar.getInstance().getTime());
            item.setSolved(i%2==0);
            items.add(item);
        }
    }

    public static ItemStorage get(Context context){
        if (sItemStorage == null){
            sItemStorage = new ItemStorage(context);
        }
        return sItemStorage;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(UUID uuid){
        for (Item item: items ) {
            if (item.getId().equals(uuid)){
                return item;
            }
        }
        return null;
    }

    private static ContentValues getContentValues(Item item) {
        ContentValues values = new ContentValues();
        values.put(DbSchema.ItemTable.Columns.UUID, item.getId().toString());
        values.put(DbSchema.ItemTable.Columns.TITLE, item.getTitle());
        values.put(DbSchema.ItemTable.Columns.DATE, item.getDate().getTime());
        values.put(DbSchema.ItemTable.Columns.SOLVED, item.isSolved() ? 1 : 0);
        return values;
    }

    public void addItem(Item item){
        ContentValues values=getContentValues(item);
        mDatabase.insert(DbSchema.ItemTable.NAME, null, values);
    }

    public void updateItem(Item item) {
        String uuidString = item.getId().toString();
        ContentValues values = getContentValues(item);
        mDatabase.update(DbSchema.ItemTable.NAME, values,
                DbSchema.ItemTable.Columns.UUID + " = ?",
                new String[] { uuidString });
    }

    private Cursor queryItems(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                DbSchema.ItemTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return cursor;
    }
}
