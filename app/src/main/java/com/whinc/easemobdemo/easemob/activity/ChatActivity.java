package com.whinc.easemobdemo.easemob.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.controller.EaseUI;
import com.whinc.easemobdemo.BaseActivity;
import com.whinc.easemobdemo.R;
import com.whinc.easemobdemo.easemob.fragment.ChatFragment;
import com.whinc.easemobdemo.easemob.widget.TitleBar;

public class ChatActivity extends BaseActivity{
    private static final String TAG = "ChatActivity";
    private static final String TAG_CHAT_FRAGMENT = "ChatFragment";
    private static final String EXTRA_CHAT_TYPE = "com.whinc.easemobdemo.easemob.activity.ChatActivity.EXTRA_CHAT_TYPE";
    private static final String EXTRA_USER_ID = "com.whinc.easemobdemo.easemob.activity.ChatActivity.EXTRA_USER_ID";

    public static void start(@NonNull Context context, int chatType, String userId) {
        Intent starter = new Intent(context, ChatActivity.class);
        starter.putExtra(EXTRA_CHAT_TYPE, chatType);
        starter.putExtra(EXTRA_USER_ID, userId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if (getIntent() == null) {
            throw new IllegalArgumentException();
        }
        String userId = getIntent().getStringExtra(EXTRA_USER_ID);
        int chatType = getIntent().getIntExtra(EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);

        // 根据用户昵称
        String nickName = EaseUI.getInstance().getUserProfileProvider().getUser(userId).getNick();

        TitleBar titleBar = (TitleBar) findViewById(R.id.chat_toolbar);
        titleBar.setCenterTitle(nickName);
        titleBar.setTitle(R.string.title_message);
        titleBar.showBackIcon(true);

        initChatFragment(chatType, userId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initChatFragment(int chatType, String userId) {
        ChatFragment fragment = findFragmentByTag(TAG_CHAT_FRAGMENT);
        if (fragment == null) {
            fragment = ChatFragment.newInstance(chatType, userId);
            addFragment(fragment, R.id.fragment_container, TAG_CHAT_FRAGMENT);
        }
    }
}
