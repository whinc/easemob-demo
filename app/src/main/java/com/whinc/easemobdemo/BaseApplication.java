package com.whinc.easemobdemo;

import android.app.Application;

import com.whinc.easemobdemo.easemob.EMSdkManager;

/**
 * Created by Administrator on 2016/1/19.
 */
public class BaseApplication extends Application{

    public static boolean isAutoLogin() {
        return sAutoLogin;
    }

    public static void setAutoLogin(boolean autoLogin) {
        sAutoLogin = autoLogin;
    }

    private static boolean sAutoLogin = true;

    @Override
    public void onCreate() {
        super.onCreate();

        EMSdkManager.getInstance().init(this);
    }
}
