package com.lqr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.lqr.adapter.LQRAdapterForAbsListView;
import com.lqr.adapter.LQRViewHolder;
import com.lqr.adapter.LQRViewHolderForAbsListView;
import com.lqr.adapter.OnItemClickListener;
import com.lqr.adapter.OnItemTouchListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 ListView和GridView使用LQRAdapterForAbsListView
 */
public class AbsListViewDemoActivity extends AppCompatActivity {

    private ListView mLv;
    private List<String> mData;
    private GridView mGv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abslistview);
        initData();

        //ListView
        mLv = (ListView) findViewById(R.id.lv);
        int item_tv_list = R.layout.item_tv_list;
        LQRAdapterForAbsListView<String> lvAdapter = new LQRAdapterForAbsListView<String>(this, mData, item_tv_list) {
            @Override
            public void convert(LQRViewHolderForAbsListView helper, String item, int position) {
                helper.setText(R.id.tv, item);
            }
        };
        mLv.setAdapter(lvAdapter);
//        lvAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(LQRViewHolder helper, ViewGroup parent, View itemView, int position) {
//                helper.setText(R.id.tv, "我被点击了");
//            }
//        });
//        lvAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(LQRViewHolder helper, ViewGroup parent, View itemView, int position) {
//                helper.setText(R.id.tv, "我被长按了");
//                return false;
//            }
//        });
        lvAdapter.setOnItemTouchListener(new OnItemTouchListener() {
            @Override
            public boolean onItemTouch(LQRViewHolder helper, View childView, MotionEvent event, int position) {
                helper.setText(R.id.tv, "我被触摸了");
                return false;
            }
        });

        //GridView
        mGv = (GridView) findViewById(R.id.gv);
        LQRAdapterForAbsListView<String> gvAdapter = new LQRAdapterForAbsListView<String>(this, mData, R.layout.item_tv_list) {
            @Override
            public void convert(LQRViewHolderForAbsListView helper, String item, int position) {
                helper.setText(R.id.tv, item);
            }

        };
        mGv.setAdapter(gvAdapter);
        gvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(LQRViewHolder helper, ViewGroup parent, View itemView, int position) {
                helper.setText(R.id.tv, "我被点击了");
            }
        });
//        gvAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(LQRViewHolder helper, ViewGroup parent, View itemView, int position) {
//                helper.setText(R.id.tv, "我被长按了");
//                return false;
//            }
//        });
//        gvAdapter.setOnItemTouchListener(new OnItemTouchListener() {
//            @Override
//            public boolean onItemTouch(LQRViewHolder helper, View childView, MotionEvent event, int position) {
//                helper.setText(R.id.tv, "我被触摸了");
//                return false;
//            }
//        });
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("item " + i);
        }
    }
}
