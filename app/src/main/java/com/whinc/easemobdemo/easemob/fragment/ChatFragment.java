package com.whinc.easemobdemo.easemob.fragment;

import android.os.Bundle;

import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseChatFragment;

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
}
