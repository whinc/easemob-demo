package com.whinc.easemobdemo.easemob.message;

import com.easemob.chat.EMMessage;
import com.whinc.tinylog.Log;

/**
 * ${END}
 * <p/>
 * <p>Created by whinc on 2016/2/1.
 * Email:xiaohui_hubei@163.com</p>
 */
public abstract class CustomMessage {
    private static final String TAG = "CustomMessage";
    public static final String TYPE = "type";
    public static final String NICKNAME = "nickname";
    public static final String PORTRAIT = "portrait";
    private String mNickName = "";             // 消息发送方的昵称
    private String mPortrait = "";             // 消息发送方的头像
    private EMMessage mEMMessage = null;       // 原始的消息
    private MessageType mType = MessageType.NONE;

    public static final CustomMessage EMPTY = new EmptyMessage();

    public CustomMessage(EMMessage message) {
        parse(message);
    }

    public EMMessage getEMMessage() {
        return mEMMessage;
    }

    public String getNickName() {
        return mNickName;
    }

    public String getPortrait() {
        return mPortrait;
    }

    protected void parse(EMMessage message) {
        if (message == null) return;

        String type = message.getStringAttribute(TYPE, MessageType.NONE.name()).trim().toUpperCase();
        try {
            mType = MessageType.valueOf(type);
            mNickName = message.getStringAttribute(NICKNAME, "");
            mPortrait = message.getStringAttribute(PORTRAIT, "");
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e);
        }
    }

    /**
     * 从文本了消息的扩展字段中解析出扩展消息的类型
     * @param message
     * @return
     */
    public static MessageType parseMessageType(EMMessage message) {
        MessageType messageType = MessageType.NONE;
        String type = message.getStringAttribute(TYPE, MessageType.NONE.name()).trim().toUpperCase();
        // 确定消息类型（由于之前有部分消息采用数字作为类型，这会导致异常发生，这里捕获这种异常）
        try {
            messageType = MessageType.valueOf(type);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e);
        }
        return messageType;
    }

    @Override
    public String toString() {
        return "type:" + mType.name() + "\n"
                + "nickname:" + mNickName + "\n"
                + "portrait:" + mPortrait + "\n";
    }

    /**
     * 从文本消息中解析出自定义消息对象
     * @param message
     * @param <T>
     * @return 返回 {@link CustomMessage} 的一个实例
     */
    public static <T extends CustomMessage> T parseMessage(EMMessage message) {
        MessageType type = parseMessageType(message);
        CustomMessage customMessage;
        switch (type) {
            case PROLONGATION:
                customMessage = new ProlongationMessage(message);
                break;
            case PAIRWITH:
                customMessage = new PairWithMessage(message);
                break;
            case HINT:
                customMessage = new HintMessage(message);
                break;
            case SUIT:
                customMessage = new SuitMessage(message);
                break;
            case PRODUCT:
                customMessage = new ProlongationMessage(message);
                break;
            case STYLE:
                customMessage = new StyleMessage(message);
                break;
            default:
                customMessage = new EmptyMessage();
                break;
        }
        return (T) customMessage;
    }

    private static class EmptyMessage extends CustomMessage{
        public EmptyMessage() {
            super(null);
        }
    }
}
