package com.tarenwang.shopping_mall_demo.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.tarenwang.shopping_mall_demo.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/21.
 */

public class SecondFragment extends BaseFragment {

    private TextView mTextView;

    @Override
    protected View initView() {
        mTextView = new TextView(mContext);
        mTextView.setTextSize(20);
        mTextView.setGravity(Gravity.CENTER);
        return mTextView;
    }

    @Override
    protected void initData() {
        super.initData();
        mTextView.setText("第二页面");
    }
}
