package com.example.francisco.androidfragmentrecycleview;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by francisco on 2018-05-28.
 */

public class ItemStorage {

    static ArrayList<Item> items;

    static {
        items = new ArrayList<Item>();
        for (int i=0; i<20; i++){
            Item item=new Item();
            item.setTitle(String.format("Crile #%02d",i+1));
            item.setDate(Calendar.getInstance().getTime());
            item.setSolved(i%2==0);
            items.add(item);
        }

    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static Item getItem(UUID uuid){
        for (Item item: items ) {
            if (item.getId().equals(uuid)){
                return item;
            }
        }
        return null;
    }
}
