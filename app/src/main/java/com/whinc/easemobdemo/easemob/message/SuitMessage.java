package com.whinc.easemobdemo.easemob.message;

import com.easemob.chat.EMMessage;

/**
 * ${END}
 * <p/>
 * <p>Created by whinc on 2016/2/1.
 * Email:xiaohui_hubei@163.com</p>
 */
public class SuitMessage extends CommonMessage{
    public static final String ONLINE = "online";
    public static final String OFFLINE = "offline";
    public static final String COMFORT = "comfort";
    public static final String GLAMOUR = "glamour";
    public static final String FLAUNT = "flaunt";

    public float getOnline() {
        return online;
    }

    public float getOffline() {
        return offline;
    }

    public float getComfort() {
        return comfort;
    }

    public float getGlamour() {
        return glamour;
    }

    public float getFlaunt() {
        return flaunt;
    }

    private float online;       // 线上价格
    private float offline;      // 线下价格
    private float comfort;      // 舒适度
    private float glamour;      // 潮流值（魅力值）
    private float flaunt;       // 性价比

    public SuitMessage(EMMessage message) {
        super(message);
    }

    @Override
    protected void parse(EMMessage message) {
        super.parse(message);
        online = Float.valueOf(message.getStringAttribute(ONLINE, "0"));
        offline = Float.valueOf(message.getStringAttribute(OFFLINE, "0"));
        glamour = Float.valueOf(message.getStringAttribute(GLAMOUR, "0"));
        comfort = Float.valueOf(message.getStringAttribute(COMFORT, "0"));
        flaunt = Float.valueOf(message.getStringAttribute(FLAUNT, "0"));
    }
}
