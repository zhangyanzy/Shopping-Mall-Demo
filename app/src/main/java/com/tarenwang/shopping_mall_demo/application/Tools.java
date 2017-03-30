package com.tarenwang.shopping_mall_demo.application;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhangyan on 2017/3/28.
 */

public class Tools {
    /**
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(Constants.CONFIGFILE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(Constants.ISSETUP, value).commit();
    }

    /**
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constants.CONFIGFILE, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

}
