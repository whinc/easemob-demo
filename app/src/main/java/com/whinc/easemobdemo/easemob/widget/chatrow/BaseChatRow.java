package com.whinc.easemobdemo.easemob.widget.chatrow;

import android.content.Context;
import android.text.TextUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easemob.easeui.widget.chatrow.EaseChatRow;
import com.whinc.easemobdemo.R;
import com.whinc.easemobdemo.easemob.message.CustomMessage;

/**
 * Created by Administrator on 2016/1/22.
 */
public abstract class BaseChatRow extends EaseChatRow{

    private final CustomMessage mCustomMessage;

    public BaseChatRow(Context context, CustomMessage customMessage, int position, BaseAdapter adapter) {
        super(context, customMessage.getEMMessage(), position, adapter);
        mCustomMessage = customMessage;
    }

    @Override
    protected void onSetupUserAvatar(ImageView userAvatarView) {
        String portrait = mCustomMessage.getPortrait();
        if (!TextUtils.isEmpty(portrait)) {
            Glide.with(getContext())
                    .load(portrait)
                    .placeholder(R.drawable.ease_default_avatar)
                    .into(userAvatarView);
        } else {
            userAvatarView.setImageResource(R.drawable.ease_default_avatar);
        }
    }

    @Override
    protected void onSetupNickName(TextView usernickView) {
        usernickView.setText(mCustomMessage.getNickName());
    }
}
