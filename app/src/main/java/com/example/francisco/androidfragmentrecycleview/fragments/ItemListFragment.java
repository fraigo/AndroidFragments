package com.example.francisco.androidfragmentrecycleview.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francisco.androidfragmentrecycleview.R;
import com.example.francisco.androidfragmentrecycleview.activities.ItemListActivity;
import com.example.francisco.androidfragmentrecycleview.activities.ItemPagerActivity;
import com.example.francisco.androidfragmentrecycleview.activities.MainActivity;
import com.example.francisco.androidfragmentrecycleview.models.Item;
import com.example.francisco.androidfragmentrecycleview.models.ItemStorage;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment {

    private RecyclerView mItemRecyclerView;
    private ItemAdapter mAdapter;

    public ItemListFragment() {
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
        if (mAdapter==null){
            mAdapter = new ItemAdapter(items);
            mItemRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Item mItem;
        public TextView mTitleTextView, mDateTextView;
        private ImageView mSolvedImage, mDeleteImage;

        public ItemHolder(final View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.item_text);
            mDateTextView = itemView.findViewById(R.id.date_text);
            mSolvedImage = itemView.findViewById(R.id.solved_image);
            mDeleteImage = itemView.findViewById(R.id.delete_icon);
            itemView.setOnClickListener(this);
            mSolvedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItem.setSolved(!mItem.isSolved());
                    if (mItem.isSolved()){
                        mSolvedImage.setImageResource(android.R.drawable.btn_star_big_on);
                    }else{
                        mSolvedImage.setImageResource(android.R.drawable.btn_star_big_off);
                    }
                }
            });
            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int col=itemView.getDrawingCacheBackgroundColor();
                    itemView.setBackgroundColor(Color.LTGRAY);
                    deleteItem();
                    itemView.setBackgroundColor(col);
                    /*
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            ItemListFragment.this.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    deleteItem();
                                    itemView.setBackgroundColor(col);
                                }
                            });
                        }
                    }, 300);
                    */
                }
            });
        }

        public void deleteItem(){
            ItemStorage storage = ItemStorage.get(getActivity());
            ArrayList<Item> items = storage.getItems();
            int pos = items.indexOf(mItem);
            items.remove(pos);
            mAdapter.notifyItemRemoved(pos);
        }

        public void bindItem(Item item){
            mItem = item;
            mTitleTextView.setText(item.getTitle());
            mDateTextView.setText(DateFormat.getDateInstance().format(item.getDate()));
            if (item.isSolved()){
                mSolvedImage.setImageResource(android.R.drawable.btn_star_big_on);
            }else{
                mSolvedImage.setImageResource(android.R.drawable.btn_star_big_off);
            }
        }

        @Override
        public void onClick(View view){
            //Intent intent = ItemListActivity.newIntent(getActivity(), mItem.getId());
            Intent intent = ItemPagerActivity.newIntent(getActivity(),mItem.getId());
            startActivity(intent);
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
