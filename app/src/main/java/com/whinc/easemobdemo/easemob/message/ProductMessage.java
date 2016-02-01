package com.whinc.easemobdemo.easemob.message;

import com.easemob.chat.EMMessage;

/**
 * ${END}
 * <p/>
 * <p>Created by whinc on 2016/2/1.
 * Email:xiaohui_hubei@163.com</p>
 */
public class ProductMessage extends CommonMessage{

    private static final String HIGH = "high";
    private static final String WHITE = "white";
    private static final String THIN = "thin";
    public static final String ONLINE = "online";
    public static final String OFFLINE = "offline";
    public static final String GLAMOUR = "glamour";

    private float online;       // 线上价格
    private float offline;      // 线下价格

    public float getOnline() {
        return online;
    }

    public float getOffline() {
        return offline;
    }

    public float getGlamour() {
        return glamour;
    }

    public float getHigh() {
        return high;
    }

    public float getWhite() {
        return white;
    }

    public float getThin() {
        return thin;
    }

    private float glamour;      // 潮流值（魅力值）
    private float high;         // 高
    private float white;        // 白
    private float thin;         // 瘦

    public ProductMessage(EMMessage message) {
        super(message);
    }

    @Override
    protected void parse(EMMessage message) {
        super.parse(message);
        online = Float.valueOf(message.getStringAttribute(ONLINE, "0"));
        offline = Float.valueOf(message.getStringAttribute(OFFLINE, "0"));
        glamour = Float.valueOf(message.getStringAttribute(GLAMOUR, "0"));
        high = Float.valueOf(message.getStringAttribute(HIGH, "0"));
        white = Float.valueOf(message.getStringAttribute(WHITE, "0"));
        thin = Float.valueOf(message.getStringAttribute(THIN, "0"));
    }
}
