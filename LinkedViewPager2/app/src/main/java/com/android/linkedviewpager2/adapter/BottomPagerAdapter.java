package com.android.linkedviewpager2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class BottomPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mData;

    public BottomPagerAdapter(List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        this.mData = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position % mData.size());
    }

    @Override
    public int getCount() {
        return 10000;
//        return mData.size();
    }
}