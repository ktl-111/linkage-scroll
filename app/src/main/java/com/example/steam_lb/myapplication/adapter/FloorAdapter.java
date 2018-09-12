package com.example.steam_lb.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.steam_lb.myapplication.fragment.FloorListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steam_lb on 2018/7/15/015.
 */

public class FloorAdapter extends FragmentPagerAdapter {
    private List<List<String>> mData;

    public FloorAdapter(FragmentManager fm, List<List<String>> data) {
        super(fm);
        mData = data;
    }

    @Override
    public Fragment getItem(int position) {
        return FloorListFragment.newInstance((ArrayList<String>) mData.get(position));
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
