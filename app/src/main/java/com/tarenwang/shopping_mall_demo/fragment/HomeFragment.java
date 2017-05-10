package com.tarenwang.shopping_mall_demo.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tarenwang.shopping_mall_demo.R;
import com.tarenwang.shopping_mall_demo.adapter.HomeFragmentAdapter;
import com.tarenwang.shopping_mall_demo.base.BaseFragment;
import com.tarenwang.shopping_mall_demo.bean.MediaItem;
import com.tarenwang.shopping_mall_demo.databinding.FragmentHomeBinding;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/3/21.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (mediaItems!=null&&mediaItems.size()>0){
//                //有数据  设置适配器
//            }else {
//                //没有数据 显示数据
//            }
//        }
//    };

    private FragmentHomeBinding binding;
    private HomeFragmentAdapter adapter;
    private Banner banner;

    private GridView mGv;
    private TextView mSearch;
    private TextView mMessage;
    private ListView mListView;
    private TextView mNoMedia;
    private ProgressBar mLoading;
    private ImageView mTop;
    private RecyclerView mRv;
    private List<String> list;

    private Context mContext ;

    private List<Map<String, Object>> dataList;
    //图片
    private int[] icon = {R.mipmap.address_book, R.mipmap.calendar, R.mipmap.camera, R.mipmap.clock,
            R.mipmap.games_control, R.mipmap.messenger, R.mipmap.ringtone, R.mipmap.settings,
            R.mipmap.speech_balloon, R.mipmap.weather};
    //文字
    private String[] iconName = {"通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声", "设置", "语音", "天气"};
    //GridView适配器
    private SimpleAdapter simpleAdapter;
    //视频信息集合
    private ArrayList<MediaItem> mediaItems;


    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        mGv = (GridView) view.findViewById(R.id.gv_channel);
        mSearch = (TextView) view.findViewById(R.id.tv_search_home);
        mSearch.setOnClickListener(this);
        banner = (Banner) view.findViewById(R.id.banner);
        mMessage = (TextView) view.findViewById(R.id.tv_message_home);
        mMessage.setOnClickListener(this);
        mListView = (ListView) view.findViewById(R.id.home_list_view);
        mNoMedia = (TextView) view.findViewById(R.id.no_media);
        mLoading = (ProgressBar) view.findViewById(R.id.home_loading);


        list = new ArrayList<>();
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

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        //加载本地视频
        //getDataFromLocal();
    }

    /**
     * 从本地SDCard中得到数据
     * 两种方法：1、根据后缀名遍历SDCARD中的文件
     * 2、从内容提供者去获取
     * 如果是6.0以上的系统需要加载运行时权限
     */
    private void getDataFromLocal() {
        mediaItems = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建出内容提供者
                ContentResolver resolver = mContext.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                //通过内容提供者去查询
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,//视频文件在SDCARD中的名称
                        MediaStore.Video.Media.DURATION,//视频的总时间
                        MediaStore.Video.Media.SIZE,//视频文件的大小
                        MediaStore.Video.Media.DATA,//视频的绝对地址
                        MediaStore.Video.Media.ARTIST,//歌曲的演唱者
                };
                Cursor cursor = resolver.query(uri, objs, null, null, null);

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        //这些是一个视频或者歌曲的信息 拿出来放在bean中(MediaItem)
                        MediaItem mediaItem = new MediaItem();

                        String name = cursor.getString(0);
                        mediaItem.setName(name);
                        long duration = cursor.getLong(1);
                        mediaItem.setDuration(duration);
                        long size = cursor.getLong(2);
                        mediaItem.setSize(size);
                        String data = cursor.getString(3);
                        mediaItem.setData(data);
                        String artist = cursor.getString(4);
                        mediaItem.setArtist(artist);

                        mediaItems.add(mediaItem);

                    }
                    //释放
                    cursor.close();
                }
                //
                // handler.sendEmptyMessage(0);

            }
        }).start();

    }

    /**
     * 普通点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_message_home:
                Toast.makeText(getContext(), "message", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_search_home:
                Toast.makeText(getContext(), "Search", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * GridView 所需要的点击事件
     *
     * @param adapterView
     * @param view
     * @param position
     * @param l
     */
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

    /**
     * Banner类所需要的图片加载
     */
    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, final Object path, final ImageView imageView) {

            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(path)) {
                    index = i;
                }
            }
            Glide.with(context).load(path).placeholder(R.mipmap.ic_launcher).centerCrop().
                    error(R.mipmap.ic_launcher).crossFade().into(imageView);
            final int finalIndex = index;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (finalIndex) {
                        case 0:
                            Toast.makeText(getActivity(), (String) path, Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(getActivity(), (String) path, Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getActivity(), (String) path, Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }
}
