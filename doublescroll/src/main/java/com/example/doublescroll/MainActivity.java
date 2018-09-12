package com.example.doublescroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv_list;
    private TextView mTv_title;
    private RecyclerView mRv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv_title = (TextView) findViewById(R.id.item_title);
        mRv_list = (RecyclerView) findViewById(R.id.item_rv);
        mRv_main = (RecyclerView) findViewById(R.id.main_rv);
        mRv_main.setLayoutManager(new LinearLayoutManager(this));
        mRv_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        initData();
        initEvent();
    }

    private void initEvent() {
        mRv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(recyclerView.toString(), "onScrolled: " + dx + "---" + dy);
                if (!ScrollManager.isScroll) {
                    ScrollManager.scroll(recyclerView, dx, dy);
                }
            }
        });
    }

    public class WaiData {
        public String title;
        public List<String> content;
    }

    private void initData() {
        List<WaiData> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            WaiData data = new WaiData();
            data.title = "title" + i;
            List<String> content = new ArrayList<>();
            for (int j = 0; j < 30; j++) {
                content.add("content" + j);
            }
            data.content = content;
            list.add(data);
        }
        mRv_main.setAdapter(new MainAdapter(this, list));
        WaiData data = new WaiData();
        data.title = "first title";
        List<String> content = new ArrayList<>();
        for (int j = 0; j < 30; j++) {
            content.add("first content" + j);
        }
        data.content = content;
        mTv_title.setText(data.title);
        mRv_list.setAdapter(new ContentAdapter(this, data.content));
        ScrollManager.addRecyclerView(mRv_list);
    }
}
