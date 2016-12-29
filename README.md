# LQRAdapterLibrary
万能适配器（RecyclerView、ListView、GridView）

##***一、LQRAdapterForAbsListView***

###1、创建适配器
LQRAdapterForAbsListView&lt;数据类型&gt;（上下文，数据集合，item的布局引用）
###2、在convert方法中对item进行数据设置
###3、例子：
	private List<String> mData = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
        mData.add("item " + i);
    }

	//ListView
    mLv.setAdapter(new LQRAdapterForAbsListView<String>(this, mData, R.layout.item_tv_list) {
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
###4、helper的使用
LQRViewHolderForAbsListView中提供了许多常规用的控件操作，如设置文字、文字颜色、背景、显隐等，同时每个方法都是返回this，这意味着可以链式操作，方便快速开发。

##***二、LQRAdapterForRecyclerView***
###1、创建适配器
LQRAdapterForRecyclerView&lt;数据类型&gt;（上下文，数据集合，item的布局引用）
###2、在convert方法中对item进行数据设置
###3、例子：
	//RecyclerView
	mRv.setAdapter(new LQRAdapterForRecyclerView<String>(this, R.layout.item_tv_list, mData) {
        @Override
        public void convert(LQRViewHolderForRecyclerView helper, String item, int position) {
            helper.setText(R.id.tv, item);
        }
    });
###4、helper的使用
LQRViewHolderForRecyclerView中提供了许多常规用的控件操作，如设置文字、文字颜色、背景、显隐等，同时每个方法都是返回this，这意味着可以链式操作，方便快速开发。
###5、添加头部、尾部

	LQRAdapterForRecyclerView<String>  mAdapter = new LQRAdapterForRecyclerView(...);
	//必须使用HeaderAndFooterAdapter作为RecyclerView的适配器
	mRv.setAdapter(mAdapter.getHeaderAndFooterAdapter());

	//添加头部
	private void testAddHeaderView() {
        TextView tv = new TextView(this);
        ...
        tv.setText("heaer");
        mAdapter.addHeaderView(tv);
    }

	//添加尾部
    private void testAddFooterView() {
        TextView tv = new TextView(this);
        ...
        tv.setText("footer");
        mAdapter.addFooterView(tv);
    }
![image](screenshots/1.gif)
###6、其他
建议与LQRRecyclerView一起使用，不需要考虑LayoutManager和分割线的情况，开发效率大大提高。