package com.whinc.easemobdemo.easemob.widget.chatrow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.BaseAdapter;

import com.easemob.chat.EMMessage;
import com.easemob.easeui.widget.chatrow.EaseChatRow;
import com.easemob.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.whinc.easemobdemo.easemob.message.MessageExt;

/**
 * Created by Administrator on 2016/1/21.
 */
public class CustomChatRowProvider implements EaseCustomChatRowProvider {
    private static final String TAG = "CustomChatRowProvider";

    private final Context mContext;

    public CustomChatRowProvider(@NonNull Context context) {
        mContext = context;
    }

    @Override
    public int getCustomChatRowTypeCount() {
        return MessageExt.values().length;
    }

    @Override
    public int getCustomChatRowType(EMMessage message) {
        return MessageExt.parse(message).getType();
    }

    @Override
    public EaseChatRow getCustomChatRow(EMMessage message, int position, BaseAdapter adapter) {
        EaseChatRow chatRow = null;
        MessageExt messageExt = MessageExt.parse(message);
        switch (messageExt) {
            case PROLONGATION:
            case PAIRWITH:
            case HINT:
                chatRow = new ChatRow1(mContext, messageExt, position, adapter);
                break;
            case SUIT:
                chatRow = new ChatRow2(mContext, messageExt, position, adapter);
                break;
            default:
                break;
        }
        return chatRow;
    }
}
