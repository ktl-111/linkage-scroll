package com.example.steam_lb.myapplication.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.steam_lb.myapplication.R;
import com.example.steam_lb.myapplication.adapter.FloorListAdapter;
import com.example.steam_lb.myapplication.utils.DPUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by steam_lb on 2018/7/15/015.
 */

public class FloorListFragment extends Fragment {

    private View view;
    private RecyclerView rv_list;
    public static final String ARG_DATA = "arg_data";
    private List<String> mData = new ArrayList<>();
    public static int count = 10;
    private FloorListAdapter adapter;

    public static FloorListFragment newInstance(ArrayList<String> data) {
        FloorListFragment fragment = new FloorListFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_floorlist, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initView();
    }


    private void init() {
        mData = getArguments().getStringArrayList(ARG_DATA);
    }

    private void initView() {
        rv_list = view.findViewById(R.id.rv_floor_list);
        final int dp15 = DPUtils.dip2px(getContext(), 15);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_list.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = dp15;
                } else {
                    outRect.top = 0;
                }
                outRect.bottom = dp15;
            }
        });
        adapter = new FloorListAdapter(getContext());
        adapter.setListData(mData);
        rv_list.setAdapter(adapter);
    }
}
