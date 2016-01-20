package com.whinc.easemobdemo.easemob.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.easemob.chat.EMConversation;
import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseChatFragment;
import com.whinc.easemobdemo.BaseActivity;
import com.whinc.easemobdemo.R;
import com.whinc.easemobdemo.easemob.EMSdkManager;
import com.whinc.easemobdemo.easemob.fragment.ChatFragment;
import com.whinc.easemobdemo.easemob.fragment.ConversationListFragment;

public class MainActivity extends BaseActivity
        implements ConversationListFragment.EaseConversationListItemClickListener{
    private static final String TAG_CONVERSION_LIST_FRAGMENT = "ConversionListFragment";
    private static final String TAG_CHAT_FRAGMENT = "ChatFragment";

    private ConversationListFragment mConversationListFragment;
    private EaseChatFragment mChatFragment;

    public static void start(@NonNull Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        // 初始化会话列表
        startConversationListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            EMSdkManager.getInstance().logout();
            LoginActivity.start(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startConversationListFragment() {
        FragmentManager fm = getSupportFragmentManager();
        int id = R.id.conversation_fragment_container;
        mConversationListFragment = findFragmentByTag(TAG_CONVERSION_LIST_FRAGMENT);
        if (mConversationListFragment == null) {
            mConversationListFragment = ConversationListFragment.newInstance();
            mConversationListFragment.setConversationListItemClickListener(this);
            addFragment(mConversationListFragment, id, TAG_CONVERSION_LIST_FRAGMENT);
        }
        hideFragment(mChatFragment);
        showFragment(mConversationListFragment);
    }

    private void startChatFragment(int chatType, String userId) {
        FragmentManager fm = getSupportFragmentManager();
        int id = R.id.conversation_fragment_container;
        mChatFragment = findFragmentByTag(TAG_CHAT_FRAGMENT);
        if (mChatFragment == null) {
            mChatFragment = ChatFragment.newInstance(chatType, userId);
            addFragment(mChatFragment, id, TAG_CHAT_FRAGMENT);
        }

        showFragment(mChatFragment);
        hideFragment(mConversationListFragment);
    }

    /**
     * 点击会话列表时的回调
     * @param conversation
     */
    @Override
    public void onListItemClicked(EMConversation conversation) {
        switch (conversation.getType()) {
            case Chat:
                startChatFragment(EaseConstant.CHATTYPE_SINGLE, conversation.getUserName());
                break;
            default:
                break;
        }
    }
}
