package com.example.francisco.androidfragmentrecycleview.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.francisco.androidfragmentrecycleview.R;
import com.example.francisco.androidfragmentrecycleview.models.Item;
import com.example.francisco.androidfragmentrecycleview.models.ItemStorage;

import java.text.DateFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {

    private RecyclerView mItemRecyclerView;
    private ItemAdapter mAdapter;

    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        mItemRecyclerView = view.findViewById(R.id.item_recycler_view);
        mItemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateView();

        return view;
    }

    private void updateView(){
        ItemStorage storage=ItemStorage.get(getActivity());
        ArrayList<Item> items=storage.getItems();
        mAdapter = new ItemAdapter(items);
        mItemRecyclerView.setAdapter(mAdapter);


    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        private Item mItem;
        public TextView mTitleTextView, mDateTextView;
        private CheckBox mSolvedCheckbox;

        public ItemHolder(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.title_text);
            mDateTextView = itemView.findViewById(R.id.date_text);
            mSolvedCheckbox = itemView.findViewById(R.id.solved_checkbox);

        }

        public void bindItem(Item item){
            mItem = item;
            mTitleTextView.setText(item.getTitle());
            mDateTextView.setText(DateFormat.getDateInstance().format(item.getDate()));
            mSolvedCheckbox.setChecked(item.isSolved());
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        private ArrayList<Item> mItems;

        public ItemAdapter(ArrayList<Item> items) {
            mItems = items;
        }

        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.item_list, parent, false);
            return new ItemHolder(view);
        }
        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item item = mItems.get(position);
            holder.bindItem(item);
        }
        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

}
