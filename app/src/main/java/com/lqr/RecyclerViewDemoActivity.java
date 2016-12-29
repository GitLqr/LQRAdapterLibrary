package com.lqr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.lqr.R.id.tv;

/**
 * @创建者 CSDN_LQR
 * @描述 RecyclerView使用LQRCommonAbsListViewViewHolder
 */
public class RecyclerViewDemoActivity extends AppCompatActivity {

    private List<String> mData;
    private RecyclerView mRv;
    private LQRAdapterForRecyclerView<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        initData();
        mRv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRv.setLayoutManager(llm);
        mAdapter = new LQRAdapterForRecyclerView<String>(this, R.layout.item_tv_list, mData) {
            @Override
            public void convert(LQRViewHolderForRecyclerView helper, String item, int position) {
                helper.setText(tv, item);
            }
        };
        mRv.setAdapter(mAdapter.getHeaderAndFooterAdapter());
        testAddHeaderView();

        testAddFooterView();
    }

    private void testAddHeaderView() {
        TextView tv = new TextView(this);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        tv.setText("heaer");
        mAdapter.addHeaderView(tv);
    }

    private void testAddFooterView() {
        TextView tv = new TextView(this);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tv.setText("footer");
        mAdapter.addFooterView(tv);
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("item " + i);
        }
    }
}
