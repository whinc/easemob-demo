package com.whinc.easemobdemo.easemob.widget.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;

import com.easemob.easeui.widget.chatrow.EaseChatRow;
import com.whinc.easemobdemo.easemob.message.MessageExt;

/**
 * Created by Administrator on 2016/1/22.
 */
public abstract class BaseChatRow extends EaseChatRow{

    public BaseChatRow(Context context, MessageExt messageExt, int position, BaseAdapter adapter) {
        super(context, messageExt.getMessage(), position, adapter);
    }
}
