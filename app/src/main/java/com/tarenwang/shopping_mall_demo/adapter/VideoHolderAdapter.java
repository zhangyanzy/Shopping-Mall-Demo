package com.tarenwang.shopping_mall_demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tarenwang.shopping_mall_demo.R;
import com.tarenwang.shopping_mall_demo.Utils;
import com.tarenwang.shopping_mall_demo.bean.MediaItem;
import com.tarenwang.shopping_mall_demo.fragment.HomeFragment;

import java.util.ArrayList;

/**
 * Created by zhangYan on 2017/5/24.
 */

public class VideoHolderAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<MediaItem> mediaItems;
    private Utils utils;

    public VideoHolderAdapter(Context context, ArrayList<MediaItem> mediaItems) {
        this.context = context;
        this.mediaItems = mediaItems;
        utils = new Utils();
    }


    @Override
    public int getCount() {
        return mediaItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_video, null);
            viewHolder = new ViewHolder();
            viewHolder.mIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.iv_name);
            viewHolder.mTime = (TextView) convertView.findViewById(R.id.iv_time);
            viewHolder.mSize = (TextView) convertView.findViewById(R.id.iv_size);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MediaItem mediaItem = mediaItems.get(position);
        viewHolder.mName.setText(mediaItem.getName());
        viewHolder.mTime.setText(mediaItem.getData());
        viewHolder.mSize.setText(utils.stringForTime((int) mediaItem.getDuration()));
        return convertView;
    }

     class ViewHolder {
        private ImageView mIcon;
        private TextView mName;
        private TextView mTime;
        private TextView mSize;

    }

}
