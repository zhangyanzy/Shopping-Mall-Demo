package com.tarenwang.shopping_mall_demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/21.
 */

public class FirstFragmentAdapter extends BaseAdapter {

    private final Context mContent;
    private final String[] mDatas;

    public FirstFragmentAdapter(Context context, String[] datas) {
        this.mContent = context;
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(mContent);
        textView.setPadding(10,10,0,10);
        textView.setTextSize(20);
        textView.setText(mDatas[i]);
        return textView;
    }
}
