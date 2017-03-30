package com.tarenwang.shopping_mall_demo.app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.tarenwang.shopping_mall_demo.R;
import com.tarenwang.shopping_mall_demo.application.Constants;
import com.tarenwang.shopping_mall_demo.application.Tools;

public class SplashActivity extends AppCompatActivity {

    private ImageView mSplashIv;
    private AnimationSet as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        startAnimation();
        initEvent();//初始化时间
    }

    private void startAnimation() {
        as = new AnimationSet(true);
//        //旋转动画
//        RotateAnimation ra = new RotateAnimation(0, 360,
//                Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.5f);
//        ra.setDuration(2000);
//        ra.setFillAfter(true);
//        as.addAnimation(ra);
        //渐变动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);
        aa.setFillAfter(true);
        as.addAnimation(aa);
        //播放动画
        mSplashIv.startAnimation(as);
    }

    private void initEvent() {
        //动画结束后进入主界面还是向导界面
        //1、监听动画播完的事件
        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //监听动画播完
            @Override
            public void onAnimationEnd(Animation animation) {
                //2、判断进入主界面还是引导
                if (Tools.getBoolean(getApplicationContext(), Constants.ISSETUP, false)) {
                    //true 设置过直接进入主界面
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    //false 没设置过，进入向导界面
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), GuideActivity.class);
                    startActivity(intent);
                    Log.i("----", "onAnimationEnd: " + "Welcome");
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    private void initView() {
        mSplashIv = (ImageView) findViewById(R.id.splash_iv);
    }
}
