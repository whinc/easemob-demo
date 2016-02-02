package com.whinc.easemobdemo.easemob.widget.chatrow;

import android.content.Context;
import android.graphics.Paint;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.whinc.easemobdemo.R;
import com.whinc.easemobdemo.easemob.message.SuitMessage;

/**
 * 搭配消息
 * <p/>
 * <p>Created by whinc on 2016/2/1.
 * Email:xiaohui_hubei@163.com</p>
 */
public class SuitChatRow extends BaseChatRow {
    private ImageView mThumbnailImg;
    private TextView mIntroductionTxt;
    private TextView mOnlinePriceTxt;
    private TextView mOfflinePriceTxt;

    private final SuitMessage mMessage;

    public SuitChatRow(Context context, SuitMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
        mMessage = message;
    }

    @Override
    protected void onInflatView() {
        inflater.inflate(R.layout.custom_ease_row_received_suit, this);
    }

    @Override
    protected void onFindViewById() {
        mThumbnailImg = (ImageView) findViewById(R.id.thumbnail_img);
        mIntroductionTxt = (TextView) findViewById(R.id.content_txt);
        mOnlinePriceTxt = (TextView) findViewById(R.id.glamour_label_txt);
        mOfflinePriceTxt = (TextView) findViewById(R.id.offline_price_txt);
        mOfflinePriceTxt.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);    // 文字添加删除线
    }

    @Override
    protected void onUpdateView() {

    }

    @Override
    protected void onSetUpView() {
        Glide.with(getContext()).load(mMessage.getPicture()).into(mThumbnailImg);
        mIntroductionTxt.setText(mMessage.getContent());
        String onlinePrice = getResources().getString(R.string.params_online_price, mMessage.getOnline());
        mOnlinePriceTxt.setText(onlinePrice);
        String offlinePrice = getResources().getString(R.string.params_offline_price, mMessage.getOffline());
        mOfflinePriceTxt.setText(offlinePrice);
    }

    @Override
    protected void onBubbleClick() {
        Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
    }
}
