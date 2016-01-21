package com.whinc.easemobdemo.easemob.fragment;

import android.os.Bundle;
import android.util.Log;

import com.easemob.EMNotifierEvent;
import com.easemob.chat.EMMessage;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseChatFragment;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/19.
 */
public class ChatFragment extends EaseChatFragment {
    public static ChatFragment newInstance(int chatType, String userId) {

        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, chatType);
        args.putString(EaseConstant.EXTRA_USER_ID, userId);

        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
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
}
