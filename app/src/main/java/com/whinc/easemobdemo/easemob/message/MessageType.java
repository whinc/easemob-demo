package com.whinc.easemobdemo.easemob.message;

/**
 * 自定义扩展消息
 *
 * <p>Created by whinc on 2016/1/21.
 * Email:xiaohui_hubei@163.com</p>
 */
public enum MessageType {
    NONE(0),
    PROLONGATION(1),
    PAIRWITH(2),
    HINT(3),
    SUIT(4),
    PRODUCT(5),
    STYLE(6);

    private int mTypeId;

    MessageType(int typeId) {
        mTypeId = typeId;
    }

    public int getTypeId() {
        return mTypeId;
    }
}
