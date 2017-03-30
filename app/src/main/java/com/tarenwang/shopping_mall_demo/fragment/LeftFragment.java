package com.tarenwang.shopping_mall_demo.fragment;

import android.content.Context;
import android.view.View;

import com.tarenwang.shopping_mall_demo.R;
import com.tarenwang.shopping_mall_demo.base.BaseFragment;

/**
 * Created by zhangyan  on 2017/3/29.
 */

public class LeftFragment extends BaseFragment {

    private Context mContext;


    @Override
    protected View initView() {
        mContext = getActivity();
        View view = View.inflate(mContext, R.layout.fragment_left,null);

        return view;
    }
}
