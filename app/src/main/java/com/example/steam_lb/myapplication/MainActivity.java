package com.example.steam_lb.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.steam_lb.myapplication.adapter.FloorAdapter;
import com.example.steam_lb.myapplication.fragment.FloorListFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ViewPager vp_content;
    private LinearLayout ll_point;
    private Drawable point_noselect;
    private Drawable point_select;
    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int position = msg.arg1;
            ViewPager vp_content = (ViewPager) msg.obj;
            vp_content.setCurrentItem(position);
        }
    };
    private static Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_floor);
        initView();
        initEvent();
    }

    private void initEvent() {
        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ll_point.getChildCount(); i++) {
                    ImageView iv_point = (ImageView) ll_point.getChildAt(i);
                    if (i == position) {
                        iv_point.setImageDrawable(point_select);
                    } else {
                        iv_point.setImageDrawable(point_noselect);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        final List<List<String>> bigList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 29; i++) {
            if (i != 0 && i % FloorListFragment.count == 0) {
                bigList.add(list);
                list = new ArrayList<>();
            }
            list.add("楼栋" + i);
        }
        if (list.size() != 0) {
            bigList.add(list);
        }
        vp_content = (ViewPager) findViewById(R.id.vp_floor_content);
        FloorAdapter floorAdapter = new FloorAdapter(getSupportFragmentManager(), bigList);
        vp_content.setAdapter(floorAdapter);
        ll_point = (LinearLayout) findViewById(R.id.ll_point);
        point_select = getResources().getDrawable(R.drawable.point_select);
        point_noselect = getResources().getDrawable(R.drawable.point_no_select);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        for (int i = 0; i < bigList.size(); i++) {
            ImageView point = (ImageView) LayoutInflater.from(this).inflate(R.layout.view_point, new LinearLayout(this), false);
            point.setLayoutParams(layoutParams);
            if (i == 0) {
                point.setImageDrawable(point_select);
            }
            ll_point.addView(point);
        }
        runnable = new Runnable() {
            int i = 0;

            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.arg1 = i % bigList.size();
                msg.obj = vp_content;
                if (mHandler != null) {
                    mHandler.sendMessage(msg);
                }
                i++;
                start();
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        start();
    }

    @Override
    public void onPause() {
        super.onPause();
        stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
        runnable = null;
        mHandler = null;
    }

    public void start() {
        mHandler.postDelayed(runnable, 1000);
    }

    public void stop() {
        mHandler.removeCallbacks(runnable);
    }
}
