package com.example.francisco.androidfragmentrecycleview.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.francisco.androidfragmentrecycleview.DatePickerFragment;
import com.example.francisco.androidfragmentrecycleview.models.Item;
import com.example.francisco.androidfragmentrecycleview.R;
import com.example.francisco.androidfragmentrecycleview.models.ItemStorage;

import java.util.Date;
import java.util.UUID;

/**
 * Created by francisco on 2018-05-28.
 */

public class ItemFragment extends Fragment {

    private static final String ARG_DATA_ID = "data_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private Item mItem;
    private EditText mItemData;
    private Button mButton;
    private CheckBox mCheckbox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID dataId= (UUID) getArguments().getSerializable(ARG_DATA_ID);
        mItem = ItemStorage.get(getActivity()).getItem(dataId);
    }

    public static ItemFragment newInstance(UUID dataId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA_ID, dataId);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
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
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mItem.getDate());
                dialog.setTargetFragment(ItemFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("RESULT");
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mItem.setDate(date);
            mButton.setText(mItem.getDate().toString());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        ItemStorage.get(getActivity())
                .updateItem(mItem);
    }


}
