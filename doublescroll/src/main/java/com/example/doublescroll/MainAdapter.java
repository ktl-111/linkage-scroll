package com.example.doublescroll;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steam_lb on 2018/9/12/012.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    private List<MainActivity.WaiData> mWaiData = new ArrayList<>();
    Context mContext;
    private final LayoutInflater mInflater;

    public MainAdapter(Context context, List<MainActivity.WaiData> data) {
        mContext = context;
        mWaiData = data;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setNewData(List<MainActivity.WaiData> data) {
        mWaiData = data;
        notifyDataSetChanged();
    }


    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, parent, false);
        return new MainHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final MainHolder holder, final int position) {
        MainActivity.WaiData waiData = mWaiData.get(position);
        holder.mTv_title.setText(waiData.title);
        if (holder.mRv_content.getAdapter() == null) {
            holder.mRv_content.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            holder.mRv_content.setAdapter(new ContentAdapter(mContext, waiData.content));
        }
        //            holder.mRv_content.scrollTo(tag1[0],0);
        ((LinearLayoutManager) holder.mRv_content.getLayoutManager()).scrollToPositionWithOffset(ScrollManager.position, ScrollManager.left);
        ScrollManager.addRecyclerView(holder.mRv_content);
        Object tag = holder.mRv_content.getTag();
        RecyclerView.OnScrollListener listener;
        if (tag != null) {
            holder.mRv_content.removeOnScrollListener((RecyclerView.OnScrollListener) tag);
            listener = (RecyclerView.OnScrollListener) tag;
        } else {
            listener = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    Log.d(recyclerView.toString(), "onScrolled: " + dx + "---" + dy);
                    if (!ScrollManager.isScroll) {
                        ScrollManager.scroll(recyclerView, dx, dy);
                    }
                }
            };
            holder.mRv_content.setTag(listener);
        }
        holder.mRv_content.addOnScrollListener(listener);
        holder.mRv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "rv", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, position + " row", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWaiData.size();
    }

    public static class MainHolder extends RecyclerView.ViewHolder {

        public final TextView mTv_title;
        public final RecyclerView mRv_content;

        public MainHolder(View itemView) {
            super(itemView);
            mTv_title = itemView.findViewById(R.id.item_title);
            mRv_content = itemView.findViewById(R.id.item_rv);
        }
    }
}
