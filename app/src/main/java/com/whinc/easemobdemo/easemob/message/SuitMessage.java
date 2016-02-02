package com.whinc.easemobdemo.easemob.message;

import android.text.TextUtils;

import com.easemob.chat.EMMessage;

/**
 * ${END}
 * <p/>
 * <p>Created by whinc on 2016/2/1.
 * Email:xiaohui_hubei@163.com</p>
 */
public class SuitMessage extends CommonMessage2{
    public static final String COMFORT = "comfort";
    public static final String FLAUNT = "flaunt";
    public static final String KEYWORDS = "keywords";
    private float flaunt;       // 性价比
    private float comfort;      // 舒适度（匹配度）
    private String keywords;    // 关键字（空白字符分割）

    public SuitMessage(EMMessage message) {
        super(message);
    }

    public float getFlaunt() {
        return flaunt;
    }

    public float getComfort() {
        return comfort;
    }

    public String getKeywords() {
        return keywords;
    }

    public String[] getKeywordArray(){
        String[] result;
        if (!TextUtils.isEmpty(keywords)) {
            result = keywords.trim().split("\\s+");
        } else {
            result = new String[0];
        }
        return result;
    }

    @Override
    protected void parse(EMMessage message) {
        super.parse(message);
        comfort = Float.valueOf(message.getStringAttribute(COMFORT, "0"));
        flaunt = Float.valueOf(message.getStringAttribute(FLAUNT, "0"));
        keywords = message.getStringAttribute(KEYWORDS, "");
    }
}
