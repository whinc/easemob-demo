package com.whinc.easemobdemo.easemob.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.whinc.easemobdemo.easemob.EMSdkManager;

/**
 * Created by Administrator on 2016/1/19.
 */
public class PreferenceUtils {
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PWD = "pwd";

    public static void saveLoginInfo(String username, String pwd) {
        getSharePreferences().edit()
                .putString(KEY_USERNAME, username)
                .putString(KEY_PWD, pwd)
                .commit();
    }

    public static String getUsername() {
        return getSharePreferences().getString(KEY_USERNAME, null);
    }

    public static String getPassword() {
        return getSharePreferences().getString(KEY_PWD, null);
    }

    private static SharedPreferences getSharePreferences() {
        Context context = EMSdkManager.getInstance().getContext();
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }
}
