package com.example.doublescroll;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by steam_lb on 2018/9/12/012.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> {
    List<String> mData;
    Context mContext;
    private final LayoutInflater mInflater;

    public ContentAdapter(Context context, List<String> data) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false);
        return new ContentHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ContentHolder holder, final int position) {
        holder.mTv_content.setText(mData.get(position));
        holder.mTv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mData.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ContentHolder extends RecyclerView.ViewHolder {

        public final TextView mTv_content;

        public ContentHolder(View itemView) {
            super(itemView);
            mTv_content = itemView.findViewById(R.id.text);
        }
    }
}
