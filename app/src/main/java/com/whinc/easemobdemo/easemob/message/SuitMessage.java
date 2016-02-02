package com.whinc.easemobdemo.easemob.message;

import com.easemob.chat.EMMessage;

/**
 * ${END}
 * <p/>
 * <p>Created by whinc on 2016/2/1.
 * Email:xiaohui_hubei@163.com</p>
 */
public class SuitMessage extends CommonMessage2{
    public static final String GLAMOUR = "glamour";
    public static final String FLAUNT = "flaunt";

    public float getGlamour() {
        return glamour;
    }

    public float getFlaunt() {
        return flaunt;
    }

    private float glamour;      // 潮流值（魅力值）
    private float flaunt;       // 性价比

    public SuitMessage(EMMessage message) {
        super(message);
    }

    @Override
    protected void parse(EMMessage message) {
        super.parse(message);
        glamour = Float.valueOf(message.getStringAttribute(GLAMOUR, "0"));
        flaunt = Float.valueOf(message.getStringAttribute(FLAUNT, "0"));
    }
}
