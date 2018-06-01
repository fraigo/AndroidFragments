package com.example.francisco.androidfragmentrecycleview.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.francisco.androidfragmentrecycleview.R;
import com.example.francisco.androidfragmentrecycleview.fragments.ItemListFragment;

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
