package com.tarenwang.shopping_mall_demo.fragment;

import android.app.Service;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tarenwang.shopping_mall_demo.R;
import com.tarenwang.shopping_mall_demo.base.BaseFragment;
import com.tarenwang.shopping_mall_demo.http.Joke;
import com.tarenwang.shopping_mall_demo.http.ServiceUtils;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/21.
 */

public class SecondFragment extends BaseFragment {
    //http://www.imooc.com/api/teacher?type=4&num=30

    private ListView mListView;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_second,null);

        return view;
    }

    @Override
    protected void initData() {
        super.initData();

    }
//    private void getJoke(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://japi.juhe.cn")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ServiceUtils service = retrofit.create(ServiceUtils.class);
//        Call<Joke> call = service.getJokeList()
//    }

}
