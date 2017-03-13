package com.lqr.adapter;

import android.view.MotionEvent;
import android.view.View;

/**
 * CSDN_LQR
 * item的触摸回调
 */
public interface OnItemTouchListener {
    boolean onItemTouch(LQRViewHolder helper, View childView, MotionEvent event, int position);
}
