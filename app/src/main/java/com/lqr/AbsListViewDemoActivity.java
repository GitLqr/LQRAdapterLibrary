package com.lqr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListView;

import com.lqr.adapter.LQRAdapterForAbsListView;
import com.lqr.adapter.LQRViewHolderForAbsListView;

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
        mLv.setAdapter(new LQRAdapterForAbsListView<String>(this, mData, item_tv_list) {
            @Override
            public void convert(LQRViewHolderForAbsListView helper, String item, int position) {
                helper.setText(R.id.tv, item);
            }

        });

        //GridView
        mGv = (GridView) findViewById(R.id.gv);
        mGv.setAdapter(new LQRAdapterForAbsListView<String>(this, mData, R.layout.item_tv_list) {
            @Override
            public void convert(LQRViewHolderForAbsListView helper, String item, int position) {
                helper.setText(R.id.tv, item);
            }

        });
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("item " + i);
        }
    }
}
