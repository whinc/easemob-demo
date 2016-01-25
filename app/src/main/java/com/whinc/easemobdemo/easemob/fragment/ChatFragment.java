package com.whinc.easemobdemo.easemob.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.easemob.EMNotifierEvent;
import com.easemob.chat.EMMessage;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseChatFragment;
import com.easemob.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.whinc.easemobdemo.easemob.EMSdkManager;
import com.whinc.easemobdemo.easemob.message.MessageExt;
import com.whinc.easemobdemo.easemob.utils.UserInfoUtils;
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
        EMSdkManager.getInstance().autoCheckAndConnect();   // 自动断线重连
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

    /**
     * 设置消息扩展字段（每个发送的消息都会经过该方法）
     * @param message
     */
    @Override
    public void onSetMessageAttributes(EMMessage message) {
        message.setAttribute(MessageExt.NICKNAME, UserInfoUtils.getNickname());
        // 尝试解析为资源ID（长整型），解析成功说明用户头像使用的是本地的默认头像，这种情况不应该发送用户的头像
        // 如果解析失败，可以认为用户有网络头像，改情况下发送用户的头像给对方
        try {
            Long.parseLong(UserInfoUtils.getAvatar());
        } catch (NumberFormatException e) {
            message.setAttribute(MessageExt.PORTRAIT, UserInfoUtils.getAvatar());
        }
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
