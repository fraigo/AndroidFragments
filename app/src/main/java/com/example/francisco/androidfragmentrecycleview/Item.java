package com.example.francisco.androidfragmentrecycleview;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by francisco on 2018-05-28.
 */

public class Item {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Item(){
        // Generate UUID
        mId = UUID.randomUUID();
        mTitle = "";
        mDate = Calendar.getInstance().getTime();
        mSolved = true;
    }

    public String getTitle() {
        return mTitle;
    }

    public UUID getId() {
        return mId;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    @Override
    public String toString() {
        return "Item{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mDate=" + DateFormat.getDateInstance().format(mDate) +
                ", mSolved=" + mSolved +
                '}';
    }
}
