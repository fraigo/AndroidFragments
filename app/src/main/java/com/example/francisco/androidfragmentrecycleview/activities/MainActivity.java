package com.example.francisco.androidfragmentrecycleview.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.example.francisco.androidfragmentrecycleview.fragments.DetailFragment;
import com.example.francisco.androidfragmentrecycleview.R;

import java.util.UUID;

public class MainActivity extends FragmentActivity {

    private static final String ITEM_ID = "data_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertFragment();
    }

    public void insertFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = getNewFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    public static Intent newIntent(Context packageContext, UUID uuid){
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(ITEM_ID, uuid);
        return intent;
    }

    protected Fragment getNewFragment() {
        //return new DetailFragment();
        UUID dataId = (UUID) getIntent().getSerializableExtra(ITEM_ID);
        return DetailFragment.newInstance(dataId);

    }
}
