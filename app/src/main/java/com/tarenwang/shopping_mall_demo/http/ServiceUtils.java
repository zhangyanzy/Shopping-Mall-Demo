package com.tarenwang.shopping_mall_demo.http;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zhang_yan on 2017/4/6.
 */
//http://www.imooc.com/api/teacher?type=4&num=30
public interface ServiceUtils {
    @POST("/joke/content/list.from")
    Call<Joke> getJokeList(@Query("sort") String sort, @Query("page") int page, @Query("pagesize") int pagesize,
                       @Query("time") String time, @Query("Key") String key);

}
