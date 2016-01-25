package com.whinc.easemobdemo.easemob.utils;

import com.whinc.easemobdemo.R;

/**
 * Created by Administrator on 2016/1/21.
 */
public class UserInfoUtils {

    /**
     * 获取当前用户的昵称
     * @return
     */
    public static String getNickname() {
        return PreferenceUtils.getUsername();
    }

    /**
     * 获取当前用户的头像，如果没有则使用默认头像
     * @return
     */
    public static String getAvatar() {
        return String.valueOf(R.drawable.ic_user_avatar);
    }

    /**
     * 获取默认的用户头像
     * @return
     */
    public static String getDefaultAvatar() {
        return String.valueOf(R.drawable.ic_user_avatar2);
    }
}
