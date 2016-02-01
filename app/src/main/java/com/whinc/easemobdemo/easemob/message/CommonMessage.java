package com.whinc.easemobdemo.easemob.message;

import com.easemob.chat.EMMessage;

import java.lang.reflect.Field;

/**
 * ${END}
 * <p/>
 * <p>Created by whinc on 2016/2/1.
 * Email:xiaohui_hubei@163.com</p>
 */
public abstract class CommonMessage extends CustomMessage {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String PICTURE = "picture";
    private long mId;
    private String mTitle;
    private String mContent;
    private String mPicture;

    public CommonMessage(EMMessage message) {
        super(message);
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public String getPicture() {
        return mPicture;
    }

    @Override
    protected void parse(EMMessage message) {
        super.parse(message);
        mId = Long.valueOf(message.getStringAttribute(ID, "0"));
        mTitle = message.getStringAttribute(TITLE, "");
        mContent = message.getStringAttribute(CONTENT, "");
        mPicture = message.getStringAttribute(PICTURE, "");
    }

    @Override
    public String toString() {
        return super.toString()
                + "id:" + mId + "\n"
                + "title:" + mTitle + "\n"
                + "content:" + mContent + "\n"
                + "picture:" + mPicture + "\n";
    }

    public static CommonMessage createTestMessage(MessageType type, long id) {
        CommonMessage message = new CommonMessage(null) { };
        try {
            Field field = CustomMessage.class.getDeclaredField("mType");
            field.setAccessible(true);
            field.set(message, type);
            Field field2 = CommonMessage.class.getDeclaredField("mId");
            field2.setAccessible(true);
            field2.set(message, id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return message;
    }
}
