package com.lqr.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * CSDN_LQR
 * item的长按回调
 */
public interface OnItemLongClickListener {
    /**
     * @param helper
     * @param parent   如果是RecyclerView的话，parent为空
     * @param itemView
     * @param position
     */
    boolean onItemLongClick(LQRViewHolder helper, ViewGroup parent, View itemView, int position);
}
