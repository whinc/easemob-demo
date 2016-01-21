package com.whinc.easemobdemo.easemob;

import android.content.Context;
import android.support.annotation.NonNull;

import com.easemob.EMCallBack;

/**
 * Created by whinc on 2016/1/19.
 * 所有环信的API调用都在该接口中定义，项目中不直接调用环信的SDK，通过设置代理来检测每个环信接口的调用
 */
public interface EMSdk {
    Context getContext();
    void init(@NonNull Context context);
    void login(String username, String password, EMCallBack callBack);
    void logout();
    String getUserName();
}
