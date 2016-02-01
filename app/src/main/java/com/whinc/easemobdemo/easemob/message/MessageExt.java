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
    NONE(0),
    PROLONGATION(1),
    PAIRWITH(2),
    HINT(3),
    SUIT(4),
    PRODUCT(5),
    STYLE(6),
    ;

    /* 消息扩展字段名字 */
    public static final String NICKNAME = "nickname";
    public static final String PORTRAIT = "portrait";
    public static final String ONLINE = "online";
    public static final String OFFLINE = "offline";
    public static final String COMFORT = "comfort";
    public static final String GLAMOUR = "glamour";
    public static final String FLAUNT = "flaunt";
    private static final String TAG = "MessageExt";
    private static final String TYPE = "type";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String PICTURE = "picture";
    private static final String CONTENT = "content";
    private static final String HIGH = "high";
    private static final String WHITE = "white";
    private static final String THIN = "thin";
    /* 每种消息只有其中部分字段有值 */
    private int type;           // 消息类型
    private long id;
    private String title;
    private String picture;
    private String content;     // 文字内容（描述、介绍）
    private String nickname;    // 消息发送者昵称
    private String portrait;    // 消息发送者头像地址
    private float online;       // 线上价格
    private float offline;      // 线下价格
    private float comfort;      // 舒适度
    private float glamour;      // 潮流值（魅力值）
    private float flaunt;       // 性价比
    private float high;         // 高
    private float white;        // 白
    private float thin;         // 瘦
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

    public float getHigh() {
        return high;
    }

    public float getWhite() {
        return white;
    }

    public float getThin() {
        return thin;
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
        nickname = message.getStringAttribute(NICKNAME, message.getUserName());
        portrait = message.getStringAttribute(PORTRAIT, "");

        id = Long.valueOf(message.getStringAttribute(ID, "0"));
        title = message.getStringAttribute(TITLE, "");
        picture = message.getStringAttribute(PICTURE, "");
        content = message.getStringAttribute(CONTENT, "");

        if (this == SUIT || this == PRODUCT) {
            online = Float.valueOf(message.getStringAttribute(ONLINE, "0"));
            offline = Float.valueOf(message.getStringAttribute(OFFLINE, "0"));
            glamour = Float.valueOf(message.getStringAttribute(GLAMOUR, "0"));
            if (this == SUIT) {
                comfort = Float.valueOf(message.getStringAttribute(COMFORT, "0"));
                flaunt = Float.valueOf(message.getStringAttribute(FLAUNT, "0"));
            } else if (this == PRODUCT) {
                high = Float.valueOf(message.getStringAttribute(HIGH, "0"));
                white = Float.valueOf(message.getStringAttribute(WHITE, "0"));
                thin = Float.valueOf(message.getStringAttribute(THIN, "0"));
            }
        }
    }

    @Override
    public String toString() {
        return String.format("{type:%s(%d), id:%d, title:%s, picture:%s, content:%s, nickname:%s, portrait:%s}",
                name(), type, id, title, picture, content, nickname, portrait);
    }
}
