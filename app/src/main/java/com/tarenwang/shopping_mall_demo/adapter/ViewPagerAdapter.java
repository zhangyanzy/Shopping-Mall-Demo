package com.tarenwang.shopping_mall_demo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/22.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private ArrayList<ImageView> mViewList;

    public ViewPagerAdapter(ArrayList<ImageView> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        position %= mViewList.size();
        if (position < 0) {
            position = mViewList.size() + position;
        }
        ImageView view = mViewList.get(position);
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);

        }
        return view;
    }
}
