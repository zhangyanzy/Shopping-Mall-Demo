package com.tarenwang.shopping_mall_demo.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tarenwang.shopping_mall_demo.R;
import com.tarenwang.shopping_mall_demo.adapter.HomeFragmentAdapter;
import com.tarenwang.shopping_mall_demo.base.BaseFragment;
import com.tarenwang.shopping_mall_demo.databinding.FragmentHomeBinding;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/21.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private FragmentHomeBinding binding;

    private HomeFragmentAdapter adapter;
    private Banner banner;

    private GridView mGv;
    private TextView mSearch;
    private TextView mMessage;
    private ImageView mTop;
    private RecyclerView mRv;

    private List<Map<String, Object>> dataList;
    //图片
    private int[] icon = {R.mipmap.address_book, R.mipmap.calendar, R.mipmap.camera, R.mipmap.clock,
            R.mipmap.games_control, R.mipmap.messenger, R.mipmap.ringtone, R.mipmap.settings,
            R.mipmap.speech_balloon, R.mipmap.weather};
    //文字
    private String[] iconName = {"通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声", "设置", "语音", "天气"};
    //GridView适配器
    private SimpleAdapter simpleAdapter;



    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        mGv = (GridView) view.findViewById(R.id.gv_channel);
        mSearch = (TextView) view.findViewById(R.id.tv_search_home);
        mSearch.setOnClickListener(this);
        banner = (Banner) view.findViewById(R.id.banner);
        mMessage = (TextView) view.findViewById(R.id.tv_message_home);
        mMessage.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        list.add("http://img4.imgtn.bdimg.com/it/u=1849328229,2650485437&fm=214&gp=0.jpg");
        list.add("http://images.7723.cn/attachments/jietu/44953/jietub7eee801f9dafadf265e3ad5cd1d132c20141202BM3YYc.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=4100327403,4075914187&fm=214&gp=0.jpg");

        for (int i = 0; i < list.size(); i++) {
            String image = list.get(i);
        }

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR).setIndicatorGravity(BannerConfig.CENTER).setDelayTime(2000)
                .setImageLoader(new GlideImageLoader()).setImages(list).start();

        dataList = new ArrayList<Map<String, Object>>();
        simpleAdapter = new SimpleAdapter(getActivity(),
                getData(),
                R.layout.item,
                new String[]{"image", "text"},
                new int[]{R.id.item_image, R.id.item_textview});
        mGv.setAdapter(simpleAdapter);
        mGv.setOnItemClickListener(this);
        return view;

    }


    public List<Map<String, Object>> getData() {
        //用循环去添加图片和文件
        for (int i = 0; i < icon.length; i++) {
            //开启数据
            Map<String, Object> map = new HashMap<String, Object>();
            //已键值对的形式去放入图片和文字
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            //加入
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_message_home:
                Toast.makeText(getContext(), "message", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.top_home:
//                //mRv.scrollToPosition(0);
//                break;
            case R.id.tv_search_home:
                Toast.makeText(getContext(), "Search", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                Toast.makeText(getActivity(), "通讯录", Toast.LENGTH_SHORT).show();
                break;
            case 9:
                Toast.makeText(getActivity(), "天气", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, final Object path, final ImageView imageView) {
            Glide.with(context).load(path).placeholder(R.mipmap.ic_launcher).centerCrop().
                    error(R.mipmap.ic_launcher).crossFade().into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }


}
