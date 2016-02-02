package com.whinc.easemobdemo.easemob.message;

import com.easemob.chat.EMMessage;

/**
 * ${END}
 * <p/>
 * <p>Created by whinc on 2016/2/1.
 * Email:xiaohui_hubei@163.com</p>
 */
public class ProductMessage extends CommonMessage2{

    private static final String HIGH = "high";
    private static final String WHITE = "white";
    private static final String THIN = "thin";
    private float high;         // 高
    private float white;        // 白
    private float thin;         // 瘦

    public ProductMessage(EMMessage message) {
        super(message);
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

    @Override
    protected void parse(EMMessage message) {
        super.parse(message);
        high = Float.valueOf(message.getStringAttribute(HIGH, "0"));
        white = Float.valueOf(message.getStringAttribute(WHITE, "0"));
        thin = Float.valueOf(message.getStringAttribute(THIN, "0"));
    }
}
