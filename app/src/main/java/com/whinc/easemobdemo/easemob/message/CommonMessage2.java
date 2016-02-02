package com.whinc.easemobdemo.easemob.message;

import com.easemob.chat.EMMessage;

/**
 * ${END}
 * <p/>
 * <p>Created by whinc on 2016/2/2.
 * Email:xiaohui_hubei@163.com</p>
 */
public abstract class CommonMessage2 extends CommonMessage{
    public static final String ONLINE = "online";
    public static final String OFFLINE = "offline";
    public static final String GLAMOUR = "glamour";
    private float online;       // 线上价格
    private float offline;      // 线下价格
    private float glamour;      // 潮流值（魅力值）

    public float getOnline() {
        return online;
    }

    public float getOffline() {
        return offline;
    }

    public float getGlamour() {
        return glamour;
    }

    public CommonMessage2(EMMessage message) {
        super(message);
    }

    @Override
    protected void parse(EMMessage message) {
        super.parse(message);
        online = Float.valueOf(message.getStringAttribute(ONLINE, "0"));
        offline = Float.valueOf(message.getStringAttribute(OFFLINE, "0"));
        glamour = Float.valueOf(message.getStringAttribute(GLAMOUR, "0"));
    }
}
