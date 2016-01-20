package com.whinc.easemobdemo;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2016/1/19.
 */
public class BaseActivity extends AppCompatActivity{

    protected <T extends View> T findView(@IdRes int id) {
        return (T)findViewById(id);
    }

    /**
     * 切换fragment
     */
    public void switchFragment(Fragment fragment, int id) {
        if (fragment == null) return;
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commitAllowingStateLoss();
    }

    /**
     * 添加fragment
     */
    public void addFragment(Fragment fragment, int containerViewId) {
        if (fragment == null) return;
        getSupportFragmentManager().beginTransaction().add(containerViewId, fragment).commitAllowingStateLoss();
    }

    public void addFragment(Fragment f, int id, String tag) {
        if (f == null) {
            return;
        }
        getSupportFragmentManager().beginTransaction().add(id, f, tag).commitAllowingStateLoss();
    }

    /**
     * 根据tag标签查找Fragment
     */
    public <T extends Fragment> T findFragmentByTag(String tag) {
        return (T) getSupportFragmentManager().findFragmentByTag(tag);
    }

    /**
     * 隐藏fragment
     */
    public void hideFragment(Fragment fragment) {
        if (fragment == null) return;
        if (!fragment.isHidden()) {
            getSupportFragmentManager().beginTransaction().hide(fragment).commitAllowingStateLoss();
        }
    }

    /**
     * 显示fragment
     */
    public void showFragment(Fragment fragment) {
        if (fragment == null) return;
        getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
    }

    /**
     * 删除fragment
     */
    public void removeFragment(Fragment fragment, int containerViewId) {
        if (fragment == null) return;
        getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment).commitAllowingStateLoss();
    }
}
