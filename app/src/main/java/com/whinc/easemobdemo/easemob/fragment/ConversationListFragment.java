package com.whinc.easemobdemo.easemob.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMMessage;
import com.easemob.easeui.ui.EaseConversationListFragment;

/**
 * Created by Administrator on 2016/1/19.
 */
public class ConversationListFragment extends EaseConversationListFragment implements EMEventListener {
    private static final String TAG = "ConversationListFragment";

    public static ConversationListFragment newInstance() {
        Bundle args = new Bundle();
        
        ConversationListFragment fragment = new ConversationListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 注册事件监听
        EMChatManager.getInstance().registerEventListener(this);
    }

    @Override
    public void onDestroyView() {
        EMChatManager.getInstance().unregisterEventListener(this);

        super.onDestroyView();
    }

    @Override
    public void onEvent(EMNotifierEvent emNotifierEvent) {
        // 接收到新消息时刷新界面
        if (emNotifierEvent.getEvent() == EMNotifierEvent.Event.EventNewMessage) {
            refresh();
        }
    }
}
