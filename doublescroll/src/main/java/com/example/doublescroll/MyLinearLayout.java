package com.example.doublescroll;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by steam_lb on 2018/9/12/012.
 */

public class MyLinearLayout extends LinearLayout {

    private float mStartX;
    private float mStartY;

    public MyLinearLayout(Context context) {
        this(context, null);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                mStartY = ev.getY();
                postDelayed(action, 200);
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = Math.abs(ev.getY() - mStartY);
                float dx = Math.abs(ev.getX() - mStartX);
                if (dx > 50 && dx-dy > 0) {
                    setBackgroundColor(Color.WHITE);
                    removeCallbacks(action);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setBackgroundColor(Color.WHITE);
                removeCallbacks(action);
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    Runnable action = new Runnable() {
        @Override
        public void run() {
            setBackgroundColor(Color.RED);
        }
    };
}
