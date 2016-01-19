package com.whinc.easemobdemo;

import android.app.Application;

import com.whinc.easemobdemo.easemob.EMSdkManager;

/**
 * Created by Administrator on 2016/1/19.
 */
public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        EMSdkManager.getInstance().init(this);
    }
}
