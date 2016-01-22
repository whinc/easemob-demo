package com.whinc.easemobdemo;

import android.app.Application;

import com.whinc.easemobdemo.easemob.EMSdkManager;
import com.whinc.tinylog.Log;

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

        // 初始化 环信
        EMSdkManager.getInstance().init(this);

        Log.enablePrintLineInfo(true);
    }
}
