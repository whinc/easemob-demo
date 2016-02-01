package com.whinc.easemobdemo.easemob.widget.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;

import com.easemob.easeui.widget.chatrow.EaseChatRow;
import com.whinc.easemobdemo.easemob.message.CustomMessage;

/**
 * Created by Administrator on 2016/1/22.
 */
public abstract class BaseChatRow extends EaseChatRow{

    public BaseChatRow(Context context, CustomMessage customMessage, int position, BaseAdapter adapter) {
        super(context, customMessage.getEMMessage(), position, adapter);
    }
}
