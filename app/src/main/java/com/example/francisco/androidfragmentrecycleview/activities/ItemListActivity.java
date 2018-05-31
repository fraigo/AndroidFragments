package com.example.francisco.androidfragmentrecycleview.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.francisco.androidfragmentrecycleview.R;
import com.example.francisco.androidfragmentrecycleview.fragments.ItemFragment;

import java.util.UUID;

public class ItemListActivity extends MainActivity {

    public static final String ITEM_ID = "item_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected Fragment getNewFragment() {
        return new ItemFragment();
    }

    public static Intent newIntent(Context packageContext, UUID uuid){
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(ITEM_ID, uuid);
        return intent;
    }
}
