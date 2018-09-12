package com.example.steam_lb.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.steam_lb.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steam_lb on 2018/7/15/015.
 */

public class FloorListAdapter extends RecyclerView.Adapter<FloorListAdapter.FloorHolder> {
    Context mContext;
    private final LayoutInflater inflater;
    private List<String> mContent = new ArrayList<>();

    public FloorListAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setListData(List<String> content) {
        mContent = content;
    }

    @Override
    public FloorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_floor, parent, false);
        return new FloorHolder(view);
    }

    @Override
    public void onBindViewHolder(FloorHolder holder, int position) {
        holder.tv_content.setText(mContent.get(position));
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

    public static class FloorHolder extends RecyclerView.ViewHolder {

        public final TextView tv_content;

        public FloorHolder(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }
}
