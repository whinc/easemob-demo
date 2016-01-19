package com.whinc.easemobdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.easemob.chat.EMConversation;
import com.whinc.easemobdemo.R;
import com.whinc.easemobdemo.easemob.EMSdkManager;
import com.whinc.easemobdemo.fragment.ConversationFragment;

public class MainActivity extends AppCompatActivity
        implements ConversationFragment.EaseConversationListItemClickListener{

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
        initConversationFragment();
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

    private void initConversationFragment() {
        FragmentManager fm = getSupportFragmentManager();
        int id = R.id.conversation_fragment_container;
        ConversationFragment conversationFragment = (ConversationFragment) fm.findFragmentById(id);
        if (conversationFragment == null) {
            conversationFragment = new ConversationFragment();
            conversationFragment.setConversationListItemClickListener(this);
            fm.beginTransaction().add(id, conversationFragment).commit();
        }
    }

    /**
     * 点击会话列表时的回调
     * @param conversation
     */
    @Override
    public void onListItemClicked(EMConversation conversation) {

    }
}
