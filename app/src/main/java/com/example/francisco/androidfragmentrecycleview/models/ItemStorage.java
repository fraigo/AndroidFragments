package com.example.francisco.androidfragmentrecycleview.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by francisco on 2018-05-28.
 */

public class ItemStorage {

    static ItemStorage sItemStorage;
    private ArrayList<Item> items;

    private ItemStorage(Context context){
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
}
