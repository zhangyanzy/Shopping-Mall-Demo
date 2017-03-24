package com.tarenwang.shopping_mall_demo.http;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/3/21.
 */

public interface Server {
    //"http://192.168.2.189:8080/atguigu/json/GAME_URL.json";
    @GET("")
    Call<String> getInfo();

}
