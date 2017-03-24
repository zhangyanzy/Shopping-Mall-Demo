package com.tarenwang.shopping_mall_demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.tarenwang.shopping_mall_demo.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/3/21.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public HomeFragmentAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 广告滚动
     */
    private static final int BANNER = 0;
    /**
     * 分类选择
     */
    private static final int CHANNEL = 1;
    /**
     * 活动
     */
    private static final int ACT = 2;
    /**
     * 秒杀
     */
    private static final int KILL = 3;
    /**
     * 推荐
     */
    private static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    private static final int HOT = 5;

    /**
     * 默认类型
     */
    private int currentType = BANNER;


    /**
     * 创建ViewHodler
     *
     * @param parent
     * @param viewType 当前类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        }else if (viewType == CHANNEL){
            return new ChannelViewHolder(mContext,mLayoutInflater.inflate(R.layout.channel_item,null));
        }
        return null;
    }

    /**
     * 绑定ViewHolder数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerviewholder = (BannerViewHolder) holder;
            bannerviewholder.setData();
        }else if (getItemViewType(position) == CHANNEL){
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData();
        }
    }





    class ChannelViewHolder extends RecyclerView.ViewHolder{

        private Context mContext;
        private GridView mGV;

        public ChannelViewHolder(Context mContext,View itemView) {
            super(itemView);
            this.mContext = mContext;
            mGV = (GridView) itemView.findViewById(R.id.gv_channel);
        }

        public void setData() {

        }
    }







    class BannerViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private View itemView;
        private Banner banner;

        public BannerViewHolder(Context mContext, View ItemView) {
            super(ItemView);
            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData() {
            List<String> imageList = new ArrayList<>();
            //设置Banner数据
            for (int i = 0; i < 3; i++) {
                //String imagesUrl = R.mipmap.new_order_status_help;
                //imageList.add(imagesUrl);
            }

            banner.setImages(imageList);
        }
    }

    /**
     * 得到类型
     * 将被系统调用
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case KILL:
                currentType = KILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
            default:
                break;
        }
        return currentType;
    }

    /**
     * 总共有多少Item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 6;
    }
}
