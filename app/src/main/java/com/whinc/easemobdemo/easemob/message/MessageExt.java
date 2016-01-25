package com.whinc.easemobdemo.easemob.message;

import android.support.annotation.NonNull;

import com.easemob.chat.EMMessage;
import com.easemob.exceptions.EaseMobException;
import com.whinc.tinylog.Log;

/**
 * 自定义扩展消息
 *
 * <p>Created by whinc on 2016/1/21.
 * Email:xiaohui_hubei@163.com</p>
 */
public enum MessageExt {
    NONE(0), PROLONGATION(1), PAIRWITH(2), HINT(3);

    private static final String TAG = "MessageExt";
    private static final String TYPE = "type";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String PICTURE = "picture";
    private static final String CONTENT = "content";
    private static final String NICKNAME = "nickname";
    private static final String PORTRAIT = "portrait";

    private int type;
    private long id;
    private String title;
    private String picture;
    private String content;
    private String nickname;
    private String portrait;
    private EMMessage mMessage;
    MessageExt(int type) {
        this.type = type;
    }

    /**
     * 根据消息中的类型字段解析出对应消息类型的整型值
     * @param message
     * @return
     */
    public static int parseType(EMMessage message) {
        MessageExt messageExt = NONE;
        String type = message.getStringAttribute(TYPE, NONE.name()).trim().toUpperCase();
        try {
            messageExt = MessageExt.valueOf(type);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "", e);
        }
        return messageExt.getType();
    }

    /**
     * 根据消息中的类型字段解析出对应的消息类型
     * @param message
     * @return
     */
    @NonNull
    public static MessageExt parse(EMMessage message) {
        MessageExt messageExt = NONE;
        if (message != null) {
            // 获取消息类型的json字符串
            String type = message.getStringAttribute(TYPE, NONE.name()).trim().toUpperCase();
            // 确定消息类型
            messageExt = MessageExt.valueOf(type);
            // 初始化消息内容
            messageExt.setMessage(message);
        }
        return messageExt;
    }

    public static MessageExt valueOf(int type) {
        MessageExt result = null;
        for (MessageExt t :MessageExt.values()) {
            if (t.getType() == type) {
                result = t;
                break;
            }
        }
        return result;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPortrait() {
        return portrait;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPicture() {
        return picture;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return this.type;
    }

    public EMMessage getMessage() {
        return mMessage;
    }

    private void setMessage(EMMessage message) {
        mMessage = message;
        id = Long.parseLong(message.getStringAttribute(ID, "0"));
        title = message.getStringAttribute(TITLE, "");
        picture = message.getStringAttribute(PICTURE, "");
        content = message.getStringAttribute(CONTENT, "");
        nickname = message.getStringAttribute(NICKNAME, message.getUserName());
        portrait = message.getStringAttribute(PORTRAIT, "");
    }

    @Override
    public String toString() {
        return String.format("{type:%s(%d), id:%d, title:%s, picture:%s, content:%s, nickname:%s, portrait:%s}",
                name(), type, id, title, picture, content, nickname, portrait);
    }
}
