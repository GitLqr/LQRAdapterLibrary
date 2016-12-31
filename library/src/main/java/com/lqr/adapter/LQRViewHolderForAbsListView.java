package com.lqr.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ListView和GridView通用的ViewHodler
 */
public class LQRViewHolderForAbsListView {

    private Context mContext;
    private View mConvertView;
    private SparseArray<View> mViews;

    public LQRViewHolderForAbsListView(Context context, int defaultLayouId, ViewGroup parent) {
        mContext = context;
        mViews = new SparseArray<>();
        mConvertView = View.inflate(context, defaultLayouId, null);
        mConvertView.setTag(this);
    }

    /**
     * 得到ViewHolder
     * <p>
     * 如果之前对应的convertView没有Viewholder就创建一个新的，否则直接从convertView中获取。
     */
    public static LQRViewHolderForAbsListView get(Context context, int defaultLayouId, int position, View convertView, ViewGroup viewGroup) {
        LQRViewHolderForAbsListView viewHolder;

        if (convertView == null) {
            viewHolder = new LQRViewHolderForAbsListView(context, defaultLayouId, viewGroup);
        } else {
            viewHolder = (LQRViewHolderForAbsListView) convertView.getTag();
        }

        return viewHolder;
    }

    /**
     * 根据id得到布局中的View(使用SparseArray保管，提高效率)
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 得到当前item对应的View
     */
    public View getConvertView() {
        return mConvertView;
    }

    /*================== 一切有可能的操作控件的方法 begin ==================*/

    /**
     * 设置TextView文字，并返回this
     */
    public LQRViewHolderForAbsListView setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置TextView的文字颜色，并返回this
     */
    public LQRViewHolderForAbsListView setTextColor(int viewId, int colorId) {
        TextView tv = getView(viewId);
        tv.setTextColor(mContext.getResources().getColor(colorId));
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public LQRViewHolderForAbsListView setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public LQRViewHolderForAbsListView setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置ImageView的图片，并返回this
     */
    public LQRViewHolderForAbsListView setImageFileResource(int viewId, String path) {
        ImageView iv = getView(viewId);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置背景颜色，并返回this
     */
    public LQRViewHolderForAbsListView setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(mContext.getResources().getColor(colorId));
        return this;
    }


    /**
     * 设置背景资源，并返回this
     */
    public LQRViewHolderForAbsListView setBackgrounResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置显隐，并返回this
     */
    public LQRViewHolderForAbsListView setViewVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * 设置是否可用，并返回this
     */
    public LQRViewHolderForAbsListView setEnabled(int viewId, boolean enabled) {
        View view = getView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    /**
     * 设置是否可获取焦点，并返回this
     */
    public LQRViewHolderForAbsListView setFocusable(int viewId, boolean focusable) {
        View view = getView(viewId);
        view.setFocusable(focusable);
        return this;
    }

    /*================== 一切有可能操作控件的方法 end ==================*/
}
