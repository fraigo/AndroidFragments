package com.example.francisco.androidfragmentrecycleview.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.francisco.androidfragmentrecycleview.activities.ItemListActivity;
import com.example.francisco.androidfragmentrecycleview.models.Item;
import com.example.francisco.androidfragmentrecycleview.R;
import com.example.francisco.androidfragmentrecycleview.models.ItemStorage;

import java.util.UUID;

/**
 * Created by francisco on 2018-05-28.
 */

public class DetailFragment extends Fragment {

    private Item mItem;
    private EditText mItemData;
    private Button mButton;
    private CheckBox mCheckbox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID uuid= (UUID) getActivity().getIntent().getSerializableExtra(ItemListActivity.ITEM_ID);
        mItem = ItemStorage.get(getActivity()).getItem(uuid);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        mItemData = v.findViewById(R.id.item_data);
        mItemData.setText(mItem.getTitle());
        mItemData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // left blank
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                mItem.setTitle(text.toString());
                System.out.println(mItem);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Empty
            }
        });

        mButton = v.findViewById(R.id.date_button);
        mButton.setText(mItem.getDate().toString());
        mButton.setEnabled(false);


        mCheckbox = v.findViewById(R.id.check_item);
        mCheckbox.setChecked(mItem.isSolved());
        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mItem.setSolved(isChecked);
                System.out.println(mItem);
            }
        });


        return v;
    }


}
