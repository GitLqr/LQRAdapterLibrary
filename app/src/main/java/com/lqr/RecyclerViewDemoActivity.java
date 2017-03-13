package com.lqr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolder;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.adapter.OnItemClickListener;
import com.lqr.adapter.OnItemLongClickListener;
import com.lqr.adapter.OnItemTouchListener;

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
        mAdapter = new LQRAdapterForRecyclerView<String>(this, mData, R.layout.item_tv_list) {
            @Override
            public void convert(LQRViewHolderForRecyclerView helper, String item, int position) {
                helper.setText(tv, item);
            }
        };
        mRv.setAdapter(mAdapter.getHeaderAndFooterAdapter());
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(LQRViewHolder helper, ViewGroup parent, View itemView, int position) {
                helper.setText(R.id.tv, "我被点击了");
            }
        });
        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(LQRViewHolder helper, ViewGroup parent, View itemView, int position) {
                helper.setText(R.id.tv, "我被长按了");
                return false;
            }
        });
        mAdapter.setOnItemTouchListener(new OnItemTouchListener() {
            @Override
            public boolean onItemTouch(LQRViewHolder helper, View childView, MotionEvent event, int position) {
                helper.setText(R.id.tv, "我被触摸了");
                return false;
            }
        });
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
