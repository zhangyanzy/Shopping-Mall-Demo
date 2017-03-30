package com.tarenwang.shopping_mall_demo.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tarenwang.shopping_mall_demo.R;
import com.tarenwang.shopping_mall_demo.application.Constants;
import com.tarenwang.shopping_mall_demo.application.DensityUtil;
import com.tarenwang.shopping_mall_demo.application.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${zhangyan} on 2017/3/29.
 */
public class GuideActivity extends AppCompatActivity {

    private LinearLayout mPoint;
    private View mView;
    private ViewPager mViewPager;
    private Button mBtn;
    private List<ImageView> guids;
    private ViewPagerAdapter adapter;
    private int disPoints;//点与点之间的距离

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        init();
        initData();
        initEvent();
    }

    private void initEvent() {
        //监听布局完成 ，触发的结果
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //取消注册 界面变化而发生的回调结果
                mView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //计算点与点之间的距离
                disPoints = (mPoint.getChildAt(1).getLeft() - mPoint.getChildAt(0)
                        .getLeft());
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存设置状态
                Tools.setBoolean(getApplicationContext(), Constants.ISSETUP, true);
                //进入主界面
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //给viewpager添加页面改变状态
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //当前viewpager显示的页码
                //当viewpager滑动到最后一个页面到时候  显示button
                if (position == guids.size() - 1) {
                    mBtn.setVisibility(View.VISIBLE);
                } else {
                    mBtn.setVisibility(View.GONE);
                }
            }

            //在页面滑动过程当中触发的时间
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //positionOffset 移动的比例值
                //计算红点的左边距
                float leftMargin = disPoints * (position + positionOffset);

                //设置红点的左边距
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mView.getLayoutParams();
                layoutParams.leftMargin = Math.round(leftMargin);//对float类型四舍五入

                //重新设置布局
                mView.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        //图片的数据
        int[] pics = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
        //定义viewPager的容器
        guids = new ArrayList<>();
        //初始化容器中的数据
        for (int i = 0; i < pics.length; i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(pics[i]);
            //数据添加
            guids.add(imageView);

            //给点的容器LinearLayout初始化灰色的点
            View v_point = new View(getApplicationContext());
            v_point.setBackgroundResource(R.drawable.gray_point);
            int dip = 10;
            //设置灰色点的大小
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(getApplicationContext(), dip), DensityUtil.dip2px(getApplicationContext(), dip));// 注意单位是px 不是dp
            //第一个点不需要重复在一起
            if (i != 0) {
                params.leftMargin = 10;
            }
            //无缝隙在一起
            v_point.setLayoutParams(params);
            //设置点与点之间的空隙

            //添加灰色的点到线程布局中
            mPoint.addView(v_point);
        }
        //创建viewPager的适配器
        adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
    }

    public class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return guids.size();
        }

        //
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);//从viewpager移除
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //获取view
            View childe = guids.get(position);
            //添加view
            container.addView(childe);
            return childe;
        }
    }

    private void init() {
        mBtn = (Button) findViewById(R.id.guide_btn);
        mView = findViewById(R.id.guide_view);
        mPoint = (LinearLayout) findViewById(R.id.guide_point);
        mViewPager = (ViewPager) findViewById(R.id.guide_vp);
    }
}
