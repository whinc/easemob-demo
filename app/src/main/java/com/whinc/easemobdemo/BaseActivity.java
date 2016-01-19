package com.whinc.easemobdemo;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2016/1/19.
 */
public class BaseActivity extends AppCompatActivity{

    protected <T extends View> T findView(@IdRes int id) {
        return (T)findViewById(id);
    }
}
