package com.tarenwang.shopping_mall_demo.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.tarenwang.shopping_mall_demo.R;
import com.tarenwang.shopping_mall_demo.Utils;
import com.tarenwang.shopping_mall_demo.bean.MediaItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zhangYan on 2017/5/24.
 * 系统播放器
 * Andorid系统提供MediaPlayer和VideoView两个类去用作开发音视频方面的
 * MediaPlayer是负责和底层打交道，解码的是底层，封装了很多方法
 * VideoView 可以播放本地和网络的音视频
 * <p>
 * https://www.buzzingandroid.com/tools/android-layout-finder可以实例化布局
 */
public class SystemVideoPlayerActivity extends Activity implements View.OnClickListener {
    //视频进度的更新

    private static final int PROGRESS = 1;
    private VideoView mVideoView;
    private Uri uri;

    private Utils utils;
    /**
     * 监听电量的
     */
    private MyReceiver receiver;

    private LinearLayout llTop;
    private TextView tvName;
    private ImageView ivBattery;
    private TextView tvSystemTime;
    private Button btnVoice;
    private SeekBar seekbarVoice;
    private Button btnSwitchPlayer;
    private LinearLayout llBottom;
    private TextView tvCurrentTime;
    private SeekBar seekbarVideo;
    private TextView tvDuration;
    private Button btnExit;
    private Button btnVideoPre;
    private Button btnVideoStartPause;
    private Button btnNext;
    private Button btnVideoSwitchScreen;
    private Object data;
    /**
     * 传入进来的视频列表
     */
    private ArrayList<MediaItem> mediaItems;
    /**
     * 要播放列表中的具体位置
     */
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);
        utils = new Utils();
        init();
        setListener();
        getData();
        setData();
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        return super.onCreatePanelView(featureId);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void init() {
        llTop = (LinearLayout) findViewById(R.id.ll_top);
        tvName = (TextView) findViewById(R.id.tv_name);
        ivBattery = (ImageView) findViewById(R.id.iv_battery);
        tvSystemTime = (TextView) findViewById(R.id.tv_system_time);
        btnVoice = (Button) findViewById(R.id.btn_voice);

        seekbarVoice = (SeekBar) findViewById(R.id.seekbar_voice);
        btnSwitchPlayer = (Button) findViewById(R.id.btn_switch_player);
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);
        tvCurrentTime = (TextView) findViewById(R.id.tv_current_time);
        seekbarVideo = (SeekBar) findViewById(R.id.seekbar_video);
        tvDuration = (TextView) findViewById(R.id.tv_duration);
        btnExit = (Button) findViewById(R.id.btn_exit);
        btnVideoPre = (Button) findViewById(R.id.btn_video_pre);
        btnVideoStartPause = (Button) findViewById(R.id.btn_video_start_pause);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnVideoSwitchScreen = (Button) findViewById(R.id.btn_video_switch_screen);


        btnVoice.setOnClickListener(this);
        btnSwitchPlayer.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnVideoPre.setOnClickListener(this);
        btnVideoStartPause.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnVideoSwitchScreen.setOnClickListener(this);


        mVideoView = (VideoView) findViewById(R.id.video_view);


        //设置系统的控制面板
//        mVideoView.setMediaController(new MediaController(this));

        /**
         * 注册电量广播
         */
        receiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        //当电量发生变化的时候发送这个广播
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, intentFilter);
    }

    private void setData() {
        if (mediaItems != null && mediaItems.size() > 0) {
            MediaItem mediaItem = mediaItems.get(position);
            tvName.setText(mediaItem.getName());//设置视频名称
            mVideoView.setVideoPath(mediaItem.getData());
        } else if (uri != null) {
            tvName.setText(uri.toString());//设置视频名称
            mVideoView.setVideoURI(uri);
        } else {
            Toast.makeText(getApplicationContext(), "God,你没有传递数据", Toast.LENGTH_SHORT).show();
        }
        setButtonState();

    }

    private void getData() {
        //得到播放地址
        uri = getIntent().getData();
        mediaItems = (ArrayList<MediaItem>) getIntent().getSerializableExtra("videoList");
        position = getIntent().getIntExtra("position", 0);

    }


    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            setBattery(level);
        }

    }

    /**
     * 电量设置
     *
     * @param level
     */
    private void setBattery(int level) {
        if (level <= 0) {
            ivBattery.setImageResource(R.mipmap.ic_battery_0);
        } else if (level <= 10) {
            ivBattery.setImageResource(R.mipmap.ic_battery_10);
        } else if (level <= 20) {
            ivBattery.setImageResource(R.mipmap.ic_battery_20);
        } else if (level <= 40) {
            ivBattery.setImageResource(R.mipmap.ic_battery_40);
        } else if (level <= 60) {
            ivBattery.setImageResource(R.mipmap.ic_battery_60);
        } else if (level <= 80) {
            ivBattery.setImageResource(R.mipmap.ic_battery_80);
        } else if (level <= 100) {
            ivBattery.setImageResource(R.mipmap.ic_battery_100);
        } else {
            ivBattery.setImageResource(R.mipmap.ic_battery_100);
        }
    }

    private void setListener() {
        //准备好的监听
        mVideoView.setOnPreparedListener(new MyOnPreparedListener());
        //播放出错的监听
        mVideoView.setOnErrorListener(new MyOnErrorListener());
        //播放完成的监听
        mVideoView.setOnCompletionListener(new MyOnCompletionListener());
        //设置SeekBar状态变化的监听
        seekbarVideo.setOnSeekBarChangeListener(new VideoOnSeekBarChangeListener());
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void onClick(View v) {
        if (v == btnVoice) {
            // Handle clicks for btnVoice
        } else if (v == btnSwitchPlayer) {
            // Handle clicks for btnSwitchPlayer
        } else if (v == btnExit) {
            finish();
            // Handle clicks for btnExit
        } else if (v == btnVideoPre) {
            playPreVideo();
            // Handle clicks for btnVideoPre
        } else if (v == btnVideoStartPause) {
            if (mVideoView.isPlaying()) {
                //视频在播放
                mVideoView.pause();
                btnVideoStartPause.setBackgroundResource(R.drawable.btn_start_selector);
            } else {
                mVideoView.start();
                btnVideoStartPause.setBackgroundResource(R.drawable.btn_pause_selector);
            }
        } else if (v == btnNext) {
            playNextVideo();
            // Handle clicks for btnNext
        } else if (v == btnVideoSwitchScreen) {
            // Handle clicks for btnVideoSwitchScreen
        }
    }

    /**
     * 播放上一个视频
     */
    private void playPreVideo() {
        if (mediaItems != null && mediaItems.size() > 0) {
            //播放下一个
            position--;
            if (position >= 0) {
                MediaItem mediaItem = mediaItems.get(position);
                tvName.setText(mediaItem.getName());
                mVideoView.setVideoPath(mediaItem.getData());
                //设置按钮状态
                setButtonState();
            }
        } else if (uri != null) {
            //上一个和下一个按钮设置灰色并且不可以点击
            setButtonState();
        }

    }

    /**
     * 点击播放下一个视频点击事件
     */
    private void playNextVideo() {
        if (mediaItems != null&&mediaItems.size()>0) {
            //播放下一个
            position++;
            if (position < mediaItems.size()) {
                MediaItem mediaItem = mediaItems.get(position);
                tvName.setText(mediaItem.getName());
                mVideoView.setVideoPath(mediaItem.getData());
                //设置按钮状态
                setButtonState();
            }
        } else if (uri != null) {
            //上一个和下一个按钮设置灰色并且不可以点击
            setButtonState();
        }

    }

    /**
     * 设置按钮状态
     */
    private void setButtonState() {
        if (mediaItems != null && mediaItems.size() > 0) {
            if (mediaItems.size() == 1) {
                btnVideoPre.setBackgroundResource(R.drawable.btn_pre_gray);
                btnVideoPre.setEnabled(false);
                btnNext.setBackgroundResource(R.drawable.btn_next_gray);
                btnNext.setEnabled(false);
            } else if (mediaItems.size() == 2){
                if (position == 0) {
                    btnVideoPre.setBackgroundResource(R.drawable.btn_pre_gray);
                    btnVideoPre.setEnabled(false);
                } else if (position == mediaItems.size() - 1) {
                    btnNext.setBackgroundResource(R.drawable.btn_next_gray);
                    btnNext.setEnabled(false);
                } else {
                    btnVideoPre.setBackgroundResource(R.drawable.btn_video_pre_selector);
                    btnVideoPre.setEnabled(true);
                    btnNext.setBackgroundResource(R.drawable.btn_video_next_selector);
                    btnNext.setEnabled(true);
                }
            }else {

            }
        } else if (uri != null) {
            //两个按钮设置灰色
            btnVideoPre.setBackgroundResource(R.drawable.btn_pre_gray);
            btnVideoPre.setEnabled(false);
            btnNext.setBackgroundResource(R.drawable.btn_next_gray);
            btnNext.setEnabled(false);
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case PROGRESS:
                    //得到当前的播放进程
                    int currentPosition = mVideoView.getCurrentPosition();
                    //seekbar.setProgress(设置当前进度)
                    seekbarVideo.setProgress(currentPosition);
                    //更新播放进度文本
                    tvCurrentTime.setText(utils.stringForTime(currentPosition));
                    //设置系统时间
                    tvSystemTime.setText(getSystemTime());
                    //每秒更新一次
                    handler.removeMessages(PROGRESS);
                    handler.sendEmptyMessageDelayed(PROGRESS, 1000);
                    break;
            }
        }
    };

    /**
     * 得到系统时间
     *
     * @return
     */
    private String getSystemTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 关于SeekBar的更新
     * 1、视频的总时长和SeekBar的setMaxt(总时长)
     * <p>
     * 2、实例化handler，定时每秒刷新每秒得到播放进度，SeekBar.setProgress
     */

    //准备好的监听
    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            mVideoView.start();//开始播放
            //得到视频的总时长
            int duration = mVideoView.getDuration();
            seekbarVideo.setMax(duration);
            tvDuration.setText(utils.stringForTime(duration));
            handler.sendEmptyMessage(PROGRESS);
        }
    }

    //播放出错的监听
    class MyOnErrorListener implements MediaPlayer.OnErrorListener {

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            Toast.makeText(getApplicationContext(), "播放出错...", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //播放完成的监听
    class MyOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
//            Toast.makeText(getApplicationContext(), uri + "播放完成...", Toast.LENGTH_SHORT).show();
            playNextVideo();
        }
    }

    //SeekBar视频拖动的监听
    class VideoOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        /**
         * 当手指滑动SeekBar的时候,会引起SeekBar的变化  会回调这个方法
         *
         * @param seekBar
         * @param progress
         * @param fromUser 如果是用户引起的那就是true，不是用户引起的就是false
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                mVideoView.seekTo(progress);
            }

        }

        /**
         * 当手指触碰的时候回调这个方法
         *
         * @param seekBar
         */
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        /**
         * 当手指离开的时候回调这个方法
         *
         * @param seekBar
         */
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }

        super.onDestroy();
    }
}
