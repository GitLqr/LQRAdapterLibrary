package com.lqr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * ListView和GridView通用的适配器
 */
public abstract class LQRAdapterForAbsListView<T> extends BaseAdapter {

    //上下文
    private Context mContext;
    //数据（一般数据都是集合）
    private List<T> mData;
    //item的布局id
    private int mDefaultLayouId;

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemTouchListener mOnItemTouchListener;

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public OnItemTouchListener getOnItemTouchListener() {
        return mOnItemTouchListener;
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        mOnItemTouchListener = onItemTouchListener;
    }

    public LQRAdapterForAbsListView(Context context, List<T> data, int defaultLayouId) {
        mContext = context;
        mData = data;
        mDefaultLayouId = defaultLayouId;
    }

    @Override
    public int getCount() {
        if (mData != null)
            return mData.size();
        return 0;
    }

    @Override
    public T getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        //得到item对应的ViewHolder
        LQRViewHolderForAbsListView viewHolder = getViewHolder(position, convertView, viewGroup);

        //对item进行数据设置
        convert(viewHolder, getItem(position), position);

        return viewHolder.getConvertView();
    }

    /**
     * 对item进行数据设置（交给开发人员）
     */
    public abstract void convert(LQRViewHolderForAbsListView helper, T item, int position);

    /*================== 数据操作相关 begin ==================*/

    /**
     * 获取数据集合
     */
    public List<T> getData() {
        return mData;
    }

    /**
     * 在集合头部添加新的数据集合（下拉从服务器获取最新的数据集合）
     */
    public void addNewData(List<T> data) {
        if (data != null) {
            mData.addAll(0, data);
            notifyDataSetChanged();
        }
    }

    /**
     * 在集合尾部添加更多数据集合（上拉从服务器获取更多的数据集合）
     */
    public void addMoreData(List<T> data) {
        if (data != null) {
            mData.addAll(mData.size(), data);
            notifyDataSetChanged();
        }
    }

    /**
     * 设置全新的数据集合，如果传入null，则清空数据列表（第一次从服务器加载数据，或者下拉刷新当前界面数据列表）
     */
    public void setData(List<T> data) {
        if (data != null) {
            mData = data;
        } else {
            mData.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据列表
     */
    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除指定索引数据条目
     */
    public void removeItem(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 删除指定数据条目
     */
    public void removeItem(T model) {
        mData.remove(model);
        notifyDataSetChanged();
    }

    /**
     * 在指定位置添加数据条目
     */
    public void addItem(int position, T model) {
        mData.add(position, model);
        notifyDataSetChanged();
    }

    /**
     * 在集合头部添加数据条目
     */
    public void addFirstItem(T model) {
        addItem(0, model);
    }

    /**
     * 在集合末尾添加数据条目
     */
    public void addLastItem(T model) {
        addItem(mData.size(), model);
    }

    /**
     * 替换指定索引的数据条目
     */
    public void setItem(int location, T newModel) {
        mData.set(location, newModel);
        notifyDataSetChanged();
    }

    /**
     * 替换指定数据条目
     */
    public void setItem(T oldModel, T newModel) {
        setItem(mData.indexOf(oldModel), newModel);
    }

    /**
     * 移动数据条目的位置
     */
    public void moveItem(int fromPosition, int toPosition) {
        mData.add(toPosition, mData.remove(fromPosition));
        notifyDataSetChanged();
    }

    /**
     * @return 获取第一个数据模型
     */
    public
    @Nullable
    T getFirstItem() {
        return getCount() > 0 ? getItem(0) : null;
    }

    /**
     * @return 获取最后一个数据模型
     */
    public
    @Nullable
    T getLastItem() {
        return getCount() > 0 ? getItem(getCount() - 1) : null;
    }
    /*================== 数据操作相关 end ==================*/

    /**
     * 得到item对应的ViewHoldler
     */
    private LQRViewHolderForAbsListView getViewHolder(int position, View convertView, ViewGroup viewGroup) {
        LQRViewHolderForAbsListView holder = LQRViewHolderForAbsListView.get(mContext, mDefaultLayouId, position, convertView, viewGroup);
        holder.setOnItemClickListener(mOnItemClickListener);
        holder.setOnItemLongClickListener(mOnItemLongClickListener);
        holder.setOnItemTouchListener(mOnItemTouchListener);
        return holder;
    }


}
