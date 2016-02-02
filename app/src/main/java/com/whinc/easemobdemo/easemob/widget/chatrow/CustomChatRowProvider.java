package com.whinc.easemobdemo.easemob.widget.chatrow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.BaseAdapter;

import com.easemob.chat.EMMessage;
import com.easemob.easeui.widget.chatrow.EaseChatRow;
import com.easemob.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.whinc.easemobdemo.easemob.message.CustomMessage;
import com.whinc.easemobdemo.easemob.message.HintMessage;
import com.whinc.easemobdemo.easemob.message.MessageType;
import com.whinc.easemobdemo.easemob.message.PairWithMessage;
import com.whinc.easemobdemo.easemob.message.ProductMessage;
import com.whinc.easemobdemo.easemob.message.ProlongationMessage;
import com.whinc.easemobdemo.easemob.message.StyleMessage;
import com.whinc.easemobdemo.easemob.message.SuitMessage;

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
        return MessageType.values().length;
    }

    @Override
    public int getCustomChatRowType(EMMessage message) {
        return CustomMessage.parseMessageType(message).getTypeId();
    }

    @Override
    public EaseChatRow getCustomChatRow(EMMessage message, int position, BaseAdapter adapter) {
        EaseChatRow chatRow = null;
        MessageType messageType = CustomMessage.parseMessageType(message);
        switch (messageType) {
            case PROLONGATION:
                chatRow = new CommonChatRow(mContext, new ProlongationMessage(message), position, adapter);
                break;
            case PAIRWITH:
                chatRow = new CommonChatRow(mContext, new PairWithMessage(message), position, adapter);
                break;
            case HINT:
                chatRow = new CommonChatRow(mContext, new HintMessage(message), position, adapter);
                break;
            case STYLE:
                chatRow = new CommonChatRow(mContext, new StyleMessage(message), position, adapter);
                break;
            case SUIT:
                chatRow = new SuitChatRow(mContext, new SuitMessage(message), position, adapter);
                break;
            case PRODUCT:
                chatRow = new ProductChatRow(mContext, new ProductMessage(message), position, adapter);
                break;
            default:
                break;
        }
        return chatRow;
    }
}
