package com.example.dragtest;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv_img = (ImageView) findViewById(R.id.iv_img);
        LinearLayout bottom_layout = (LinearLayout) findViewById(R.id.bottom_layout);
        RelativeLayout top_layout = (RelativeLayout) findViewById(R.id.top_layout);
        iv_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //设置数据
                    Intent intent = new Intent();
                    intent.putExtra("test","测试");
                    ClipData clipData = ClipData.newIntent("asd", intent);
                    //复制view
                    View.DragShadowBuilder builder = new View.DragShadowBuilder(v);
                    //开始
                    ViewCompat.startDragAndDrop(v, clipData, builder, v, 0);
                    return true;
                }
                return false;
            }
        });
        View.OnDragListener listener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                setDrageLocation((ViewGroup) v, event);
                return true;
            }
        };
        bottom_layout.setOnDragListener(listener);
        top_layout.setOnDragListener(listener);
    }
    public static void startDrag(View view){
//        Intent intent = new Intent();
//        intent.putExtra("data", "test");
//        ClipData dragData = ClipData.newIntent("value", intent);
        ClipData.Item item = new ClipData.Item("test");
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData dragData = new ClipData("test", mimeTypes, item);
        View.DragShadowBuilder myShadow = new View.DragShadowBuilder(view);
        // 震动反馈，不需要震动权限
        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.startDragAndDrop(dragData, myShadow, null, View.DRAG_FLAG_GLOBAL);
        }else{
            view.startDrag(dragData, myShadow, null, View.DRAG_FLAG_GLOBAL);
        }
    }
    private void setDrageLocation(ViewGroup layout, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                //获得控件
                View otherView = (View) event.getLocalState();
                //获得数据类
                ClipData clipData = event.getClipData();
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item itemAt = clipData.getItemAt(i);
                    Log.d("setDrageLocation", "Item: "+itemAt.getIntent().getStringExtra("test"));
                }
                int[] location = new int[2];
                otherView.getLocationInWindow(location);
                int[] screen = new int[2];
                otherView.getLocationOnScreen(screen);
                Log.d("setDrageLocation", "x:" + location[0] + "--y:" + location[1] + "\n x:" + screen[0] + "---y" + screen[1]);
                //先获取父类,移除在父类的存在
                ViewGroup owner = (ViewGroup) otherView.getParent();
                owner.removeView(otherView);
                //再添加
                layout.addView(otherView);
                break;
        }
    }
}
