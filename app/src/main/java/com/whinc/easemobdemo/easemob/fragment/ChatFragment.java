package com.whinc.easemobdemo.easemob.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.easemob.EMNotifierEvent;
import com.easemob.chat.EMMessage;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseChatFragment;
import com.easemob.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.whinc.easemobdemo.easemob.widget.chatrow.CustomChatRowProvider;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/19.
 */
public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentListener {
    public static ChatFragment newInstance(int chatType, String userId) {

        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, chatType);
        args.putString(EaseConstant.EXTRA_USER_ID, userId);

        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setChatFragmentListener(this);      // 设置会话监听器
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hideTitleBar();
    }

    @Override
    public void onEvent(EMNotifierEvent event) {
        super.onEvent(event);

        // 打印消息信息
        Log.i(TAG, "Event:" + event.getEvent().name());
        if (event.getEvent() == EMNotifierEvent.Event.EventNewMessage) {
            EMMessage message = (EMMessage) event.getData();
            Log.i(TAG, "message:" + message.toString());
            try {
                Field field = EMMessage.class.getDeclaredField("attributes");
                field.setAccessible(true);
                Map<String, Object> attributes = (Map<String, Object>) field.get(message);
                Log.i(TAG, "ext{");
                for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                    Log.i(TAG, entry.getKey() + ":" + entry.getValue());
                }
                Log.i(TAG, "}");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSetMessageAttributes(EMMessage message) {

    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return new CustomChatRowProvider(getContext());
//        return null;
    }
}
