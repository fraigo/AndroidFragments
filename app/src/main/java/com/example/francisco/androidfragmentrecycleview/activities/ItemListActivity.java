package com.example.francisco.androidfragmentrecycleview.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.francisco.androidfragmentrecycleview.R;
import com.example.francisco.androidfragmentrecycleview.fragments.DetailFragment;
import com.example.francisco.androidfragmentrecycleview.fragments.ItemListFragment;

import java.util.UUID;

public class ItemListActivity extends MainActivity {

    private static final String ITEM_ID = "data_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected Fragment getNewFragment() {
        return new ItemListFragment();
    }


}
