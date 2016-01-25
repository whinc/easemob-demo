package com.whinc.easemobdemo.easemob.utils;

import android.text.TextUtils;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.whinc.easemobdemo.easemob.message.MessageExt;

import java.util.List;

/**
 * ${END}
 * <p>
 * <p>Created by whinc on 2016/1/25.
 * Email:xiaohui_hubei@163.com</p>
 */
public class EMSdkHelper {

    public static MessageExt getMessageExtByFromUserName(String username) {
        MessageExt messageExt = MessageExt.NONE;
        EMConversation conversation = EMChatManager.getInstance().getConversation(username);
        List<EMMessage> messages = conversation.getAllMessages();
        for (int i = messages.size()-1; i >= 0; --i) {
            EMMessage message = messages.get(i);
            String from = message.getFrom();
            if (!TextUtils.isEmpty(from) && from.equals(username)) {
                messageExt = MessageExt.parse(message);
                break;
            }
        }
        return messageExt;
    }
}
