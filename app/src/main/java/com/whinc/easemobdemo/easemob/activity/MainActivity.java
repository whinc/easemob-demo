package com.whinc.easemobdemo.easemob.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.easemob.chat.EMConversation;
import com.easemob.easeui.EaseConstant;
import com.whinc.easemobdemo.BaseActivity;
import com.whinc.easemobdemo.R;
import com.whinc.easemobdemo.easemob.EMSdkManager;
import com.whinc.easemobdemo.easemob.widget.TitleBar;
import com.whinc.easemobdemo.easemob.fragment.ConversationListFragment;

public class MainActivity extends BaseActivity
        implements ConversationListFragment.EaseConversationListItemClickListener{
    private static final String TAG_CONVERSION_LIST_FRAGMENT = "ConversionListFragment";

    public static void start(@NonNull Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TitleBar titleBar = (TitleBar) findViewById(R.id.main_toolbar);
        titleBar.setCenterTitle(R.string.title_message);
        titleBar.inflateMenu(R.menu.menu_main);
        titleBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });

        // 初始化会话列表
        initConversationListFragment();
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
        } else if (id == R.id.action_friend) {
            Toast.makeText(this, "Unopened function!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initConversationListFragment() {
        ConversationListFragment fragment = findFragmentByTag(TAG_CONVERSION_LIST_FRAGMENT);
        if (fragment == null) {
            fragment = ConversationListFragment.newInstance();
            fragment.setConversationListItemClickListener(this);
            addFragment(fragment, R.id.fragment_container, TAG_CONVERSION_LIST_FRAGMENT);
        }
    }

    /**
     * 点击会话列表时的回调
     * @param conversation
     */
    @Override
    public void onListItemClicked(EMConversation conversation) {
        switch (conversation.getType()) {
            case Chat:
                ChatActivity.start(this, EaseConstant.CHATTYPE_SINGLE, conversation.getUserName());
                break;
            default:
                break;
        }
    }
}
