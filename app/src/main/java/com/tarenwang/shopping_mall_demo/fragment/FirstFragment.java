package com.tarenwang.shopping_mall_demo.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tarenwang.shopping_mall_demo.R;
import com.tarenwang.shopping_mall_demo.adapter.FirstFragmentAdapter;
import com.tarenwang.shopping_mall_demo.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/21.
 */

public class FirstFragment extends BaseFragment  {

    private ListView mListView;
    private String[] datas;

    private FirstFragmentAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_first,null);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                String data = datas[position];
//                Toast.makeText(mContext,data,Toast.LENGTH_SHORT).show();


            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        //准备数据
        datas = new String[]{"Data-banding"};
        //设置适配器
        adapter = new FirstFragmentAdapter(mContext,datas);
        mListView.setAdapter(adapter);

    }
}
