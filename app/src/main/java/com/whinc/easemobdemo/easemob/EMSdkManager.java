package com.whinc.easemobdemo.easemob;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.domain.EaseUser;
import com.whinc.easemobdemo.BuildConfig;
import com.whinc.easemobdemo.R;
import com.whinc.easemobdemo.easemob.utils.SystemUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/1/19.
 */
public class EMSdkManager implements EMSdk{
	private static final String TAG = "EMSdkManager";

    private static EMSdk sEMSdk = new EMSdkManager().newProxy();
	private Context mAppContext;
	private Handler mUiHandler = new Handler(Looper.getMainLooper());

    private String mUserName;

	public static EMSdk getInstance() {
		return sEMSdk;
	}

	private EMSdkManager() { }

    private EMSdk newProxy() {
        return (EMSdk) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class<?>[]{EMSdk.class},
                new EMSdkInvocationHandler(this));
    }

	/**
	 * 初始化环信SDK
	 * @param context
	 */
    @Override
    public void init(@NonNull Context context) {
		Context appContext = context.getApplicationContext();
		mAppContext = appContext;

		// 防止重复初始化
		int pid = android.os.Process.myPid();
		String processAppName = SystemUtils.getAppName(appContext, pid);
		if (processAppName == null ||!processAppName.equalsIgnoreCase(BuildConfig.APPLICATION_ID)) {
			Log.e(TAG, "enter the service process!");
			return;
		}

		// 关闭自动登陆
		EMChat.getInstance().setAutoLogin(false);

		// 初始化 SDK
        EMChat.getInstance().init(appContext);

		// 开启SDK日志输出
        EMChat.getInstance().setDebugMode(BuildConfig.DEBUG);

        // 注册事件监听
//        EMChatManager.getInstance().registerEventListener(this);

        // 初始化 EaseUI
        EaseUI.getInstance().init(appContext);

        // 设置用户头像的外观形状
        EaseUI.getInstance().setEaseUIAvatar(appContext, 0, Color.WHITE, 2, 1);

        // 设置用户头像和昵称，这些会显示在会话和聊天界面
        EaseUI.getInstance().setUserProfileProvider(createUserProfileProvider());
    }

    @Override
    public Context getContext() {
        return mAppContext;
    }

    private void checkInit() {
        if (mAppContext == null) {
            throw new IllegalStateException("Do you have call EMSdkManager.init()?");
        }
    }

    @Override
	public void login(final String username, String password, final EMCallBack callBack) {
		EMChatManager.getInstance().login(username, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                mUiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mUserName = username;
                        onLoginSuccess();
                        callBack.onSuccess();
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                callBack.onError(i, s);
            }

            @Override
            public void onProgress(int i, String s) {
                callBack.onProgress(i, s);
            }
        });
	}

    @Override
    public void logout() {
        EMChatManager.getInstance().logout();
    }

    @Override
    public String getUserName() {
        return mUserName;
    }

    private void onLoginSuccess() {
		EMGroupManager.getInstance().loadAllGroups();
		EMChatManager.getInstance().loadAllConversations();
	}

    private static class EMSdkInvocationHandler implements InvocationHandler {

        private EMSdkManager mEMSdkManager;

        public EMSdkInvocationHandler(EMSdkManager v) {
            mEMSdkManager = v;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 调用API接口前先检查是否执行了初始化操作
            if (!method.getName().equals("init")) {
                mEMSdkManager.checkInit();
            }
            return method.invoke(mEMSdkManager, args);
        }
    }

    // 创建用户信息提供者
    private EaseUI.EaseUserProfileProvider createUserProfileProvider() {
        return new EaseUI.EaseUserProfileProvider() {
            @Override
            public EaseUser getUser(String username) {
                EaseUser user = new EaseUser(username);
                user.setNick(username);
                if (username.equals(mUserName)) {   // 当前用户
                    user.setAvatar(String.valueOf(R.drawable.ic_user_avatar));
                } else {        // 对方用户
                    user.setAvatar(String.valueOf(R.drawable.ic_user_avatar2));
                }
                return user;
            }
        };
    }
}
