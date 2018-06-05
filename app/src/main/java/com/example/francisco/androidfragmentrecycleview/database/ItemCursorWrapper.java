package com.example.francisco.androidfragmentrecycleview.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.francisco.androidfragmentrecycleview.models.Item;

import java.util.Date;
import java.util.UUID;

public class ItemCursorWrapper extends CursorWrapper {

    public ItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Item getCrime() {
        String uuidString = getString(getColumnIndex(DbSchema.ItemTable.Columns.UUID));
        String title = getString(getColumnIndex(DbSchema.ItemTable.Columns.TITLE));
        long date = getLong(getColumnIndex(DbSchema.ItemTable.Columns.DATE));
        int isSolved = getInt(getColumnIndex(DbSchema.ItemTable.Columns.SOLVED));

        Item crime = new Item(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        return crime;
    }


}
