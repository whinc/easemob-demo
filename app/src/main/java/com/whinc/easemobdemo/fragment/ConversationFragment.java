package com.whinc.easemobdemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.easemob.chat.EMMessage;
import com.easemob.easeui.ui.EaseConversationListFragment;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/1/19.
 */
public class ConversationFragment extends EaseConversationListFragment {
    private static final String TAG = "ConversationFragment";

    public static ConversationFragment newInstance() {
        Bundle args = new Bundle();
        
        ConversationFragment fragment = new ConversationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ConversationFragment() {
        hideTitleBar();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(EMMessage message) {
        Log.i(TAG, "new message:" + message);
        refresh();
    }
}
