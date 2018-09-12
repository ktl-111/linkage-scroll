package com.example.doublescroll;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steam_lb on 2018/9/12/012.
 */

public class ScrollManager {
    public static List<RecyclerView> mRecyclerviewList = new ArrayList<>();
    public static int position;
    public static int left;

    public static void addRecyclerView(RecyclerView recyclerView) {
        if (!mRecyclerviewList.contains(recyclerView)) {
            mRecyclerviewList.add(recyclerView);
        }
    }

    public static boolean isScroll = false;
    public static void scroll(RecyclerView recyclerView, int dx, int dy) {
        if (dx == 0) {
            return;
        }
        isScroll = true;
        int i = 0;
        for (RecyclerView rv : mRecyclerviewList) {
            if (!rv.equals(recyclerView)) {
                rv.scrollBy(dx, dy);
                if (i == 0) {
                    getPositionAndOffset(rv);
                }
                i++;
            }
        }
        isScroll = false;
    }

    private static void getPositionAndOffset(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //获取可视的第一个view
        View topView = layoutManager.getChildAt(0);
        if (topView != null) {
            //获取与该view的顶部的偏移量
            left = topView.getLeft();
            //得到该View的数组位置
            position = layoutManager.getPosition(topView);
        }
    }
}
