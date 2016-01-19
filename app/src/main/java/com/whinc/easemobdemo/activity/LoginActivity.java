package com.whinc.easemobdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.whinc.easemobdemo.BaseActivity;
import com.whinc.easemobdemo.R;
import com.whinc.easemobdemo.easemob.EMSdkManager;
import com.whinc.easemobdemo.easemob.utils.PreferenceUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "LoginActivity";

    private EditText mUsernameEdt;
    private EditText mPasswordEdt;

    public static void start(@NonNull Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameEdt = findView(R.id.username_edt);
        mPasswordEdt = findView(R.id.pwd_edt);
        findViewById(R.id.login_btn).setOnClickListener(this);

        mUsernameEdt.setText(PreferenceUtils.getUsername());
        mPasswordEdt.setText(PreferenceUtils.getPassword());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                login(mUsernameEdt.getText().toString(), mPasswordEdt.getText().toString());
                break;
        }
    }

    private void login(final String username, final String password) {
        Toast.makeText(LoginActivity.this, "Login...", Toast.LENGTH_SHORT).show();
        EMSdkManager.getInstance().login(username, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "登陆聊天服务器成功！");
                PreferenceUtils.saveLoginInfo(username, password);
                MainActivity.start(LoginActivity.this);
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d(TAG, "登陆聊天服务器失败！");
            }
        });
    }
}
