package com.lqr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 RecyclerView通用的适配器
 */
public abstract class LQRAdapterForRecyclerView<T> extends RecyclerView.Adapter<LQRViewHolderForRecyclerView> {

    private Context mContext;
    private int mDefaultLayoutId;
    private List<T> mData;
    private LQRHeaderAndFooterAdapter mHeaderAndFooterAdapter;

    public LQRAdapterForRecyclerView(Context context, int defaultLayoutId, List<T> data) {
        mContext = context;
        mDefaultLayoutId = defaultLayoutId;
        mData = data;
    }

    @Override
    public LQRViewHolderForRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LQRViewHolderForRecyclerView(mContext, View.inflate(mContext, mDefaultLayoutId, null));
    }

    @Override
    public void onBindViewHolder(LQRViewHolderForRecyclerView holder, int position) {
        convert(holder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mData != null)
            return mData.size();
        return 0;
    }

    public abstract void convert(LQRViewHolderForRecyclerView helper, T item, int position);

    /*================== 数据操作相关 begin ==================*/

    /**
     * 获取指定索引位置的数据模型
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return mData.get(position);
    }

    /**
     * 获取数据集合
     *
     * @return
     */
    public List<T> getData() {
        return mData;
    }

    /**
     * 数据局部刷新
     *
     * @param positionStart
     * @param itemCount
     */
    public final void notifyItemRangeInsertedWrapper(int positionStart, int itemCount) {
        if (mHeaderAndFooterAdapter == null) {
            notifyItemRangeInserted(positionStart, itemCount);
        } else {
            mHeaderAndFooterAdapter.notifyItemRangeInserted(mHeaderAndFooterAdapter.getHeadersCount() + positionStart, itemCount);
        }
    }

    /**
     * 在集合头部添加新的数据集合（下拉从服务器获取最新的数据集合）
     *
     * @param data
     */
    public void addNewData(List<T> data) {
        if (data != null) {
            mData.addAll(0, data);
            notifyItemRangeInsertedWrapper(0, data.size());
        }
    }

    /**
     * 在集合尾部添加更多数据集合（上拉从服务器获取更多的数据集合）
     *
     * @param data
     */
    public void addMoreData(List<T> data) {
        if (data != null) {
            mData.addAll(mData.size(), data);
            notifyItemRangeInsertedWrapper(mData.size(), data.size());
        }
    }

    /**
     * 数据全局刷新
     */
    public final void notifyDataSetChangedWrapper() {
        if (mHeaderAndFooterAdapter == null) {
            notifyDataSetChanged();
        } else {
            mHeaderAndFooterAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 设置全新的数据集合，如果传入null，则清空数据列表（第一次从服务器加载数据，或者下拉刷新当前界面数据列表）
     *
     * @param data
     */
    public void setData(List<T> data) {
        if (data != null) {
            if (mData == null) {
                mData = data;
            } else {
                mData.clear();
                mData.addAll(data);
            }
        } else {
            mData.clear();
        }
        notifyDataSetChangedWrapper();
    }

    /**
     * 清空数据列表
     */
    public void clearData() {
        mData.clear();
        notifyDataSetChangedWrapper();
    }

    /**
     * 数据移除刷新
     *
     * @param position
     */
    public final void notifyItemRemoveWrapper(int position) {
        if (mHeaderAndFooterAdapter == null) {
            notifyItemRemoved(position);
        } else {
            mHeaderAndFooterAdapter.notifyItemRemoved(mHeaderAndFooterAdapter.getHeadersCount() + position);
        }
    }

    /**
     * 删除指定索引数据条目
     *
     * @param position
     */
    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoveWrapper(position);
    }

    /**
     * 删除指定数据条目
     *
     * @param model
     */
    public void removeItem(T model) {
        removeItem(mData.indexOf(model));
    }

    /**
     * 数据添加刷新
     *
     * @param position
     */
    public final void notifyItemInsertedWrapper(int position) {
        if (mHeaderAndFooterAdapter == null) {
            notifyItemInserted(position);
        } else {
            mHeaderAndFooterAdapter.notifyItemInserted(mHeaderAndFooterAdapter.getHeadersCount() + position);
        }
    }

    /**
     * 在指定位置添加数据条目
     *
     * @param position
     * @param model
     */
    public void addItem(int position, T model) {
        mData.add(position, model);
        notifyItemInsertedWrapper(position);
    }

    /**
     * 在集合头部添加数据条目
     *
     * @param model
     */
    public void addFirstItem(T model) {
        addItem(0, model);
    }

    /**
     * 在集合末尾添加数据条目
     *
     * @param model
     */
    public void addLastItem(T model) {
        addItem(mData.size(), model);
    }

    /**
     * 数据变化刷新
     *
     * @param position
     */
    public final void notifyItemChangedWrapper(int position) {
        if (mHeaderAndFooterAdapter == null) {
            notifyItemChanged(position);
        } else {
            mHeaderAndFooterAdapter.notifyItemChanged(mHeaderAndFooterAdapter.getHeadersCount() + position);
        }
    }

    /**
     * 替换指定索引的数据条目
     *
     * @param position
     * @param newModel
     */
    public void setItem(int position, T newModel) {
        mData.set(position, newModel);
        notifyItemChangedWrapper(position);
    }

    /**
     * 替换指定数据条目
     *
     * @param oldModel
     * @param newModel
     */
    public void setItem(T oldModel, T newModel) {
        setItem(mData.indexOf(oldModel), newModel);
    }

    /**
     * 数据移动刷新
     *
     * @param fromPosition
     * @param toPosition
     */
    public final void notifyItemMoveWrapper(int fromPosition, int toPosition) {
        if (mHeaderAndFooterAdapter == null) {
            notifyItemMoved(fromPosition, toPosition);
        } else {
            mHeaderAndFooterAdapter.notifyItemMoved(mHeaderAndFooterAdapter.getHeadersCount() + fromPosition, mHeaderAndFooterAdapter.getHeadersCount() + toPosition);
        }
    }

    /**
     * 移动数据条目的位置
     *
     * @param fromPosition
     * @param toPosition
     */
    public void moveItem(int fromPosition, int toPosition) {
        notifyItemChangedWrapper(fromPosition);
        notifyItemChangedWrapper(toPosition);

        //要先执行上面的notifyItemChanged，然后再执行下面的moveItem事件

        mData.add(toPosition, mData.remove(fromPosition));
        notifyItemMoveWrapper(fromPosition, toPosition);
    }

    /**
     * 获取第一个数据模型
     *
     * @return
     */
    public T getFirstItem() {
        return getItemCount() > 0 ? getItem(0) : null;
    }

    /**
     * 得到最后一个数据模型
     *
     * @return
     */
    public T getLastItem() {
        return getItemCount() > 0 ? getItem(getItemCount() - 1) : null;
    }

    /*================== 数据操作相关 end ==================*/

    /*================== 头部、尾部部相关 begin ==================*/

    public void addHeaderView(View headerView) {
        getHeaderAndFooterAdapter().addHeaderView(headerView);
    }

    public void addFooterView(View footerView) {
        getHeaderAndFooterAdapter().addFooterView(footerView);
    }

    public int getHeadersCount() {
        return mHeaderAndFooterAdapter == null ? 0 : mHeaderAndFooterAdapter.getHeadersCount();
    }

    public int getFootersCount() {
        return mHeaderAndFooterAdapter == null ? 0 : mHeaderAndFooterAdapter.getFootersCount();
    }

    /**
     * 判断是否是头部或尾部
     *
     * @param viewHolder
     * @return
     */
    public boolean isHeaderOrFooter(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() < getHeadersCount() || viewHolder.getAdapterPosition() >= getHeadersCount() + getItemCount();
    }

    public LQRHeaderAndFooterAdapter getHeaderAndFooterAdapter() {
        if (mHeaderAndFooterAdapter == null) {
            synchronized (LQRHeaderAndFooterAdapter.class) {
                if (mHeaderAndFooterAdapter == null) {
                    mHeaderAndFooterAdapter = new LQRHeaderAndFooterAdapter(this);
                }
            }
        }
        return mHeaderAndFooterAdapter;
    }

    /*================== 头部、尾部相关 end ==================*/
}
