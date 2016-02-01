package com.whinc.easemobdemo.easemob.widget.chatrow;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.whinc.easemobdemo.R;
import com.whinc.easemobdemo.easemob.message.MessageExt;
import com.whinc.tinylog.Log;

/**
 * 消息包含：标题、图片和文字摘要
 * Created by Administrator on 2016/1/22.
 */
public class ChatRow1 extends BaseChatRow {
    private static final String TAG = "ChatRowArticle";
    private TextView mTitleTxt;         // 文章标题
    private ImageView mThumbnailImg;    // 文章缩略图
    private TextView mDigestTxt;        // 文章摘要

    private final Context mContext;
    private final MessageExt mMessageExt;
    private final BaseAdapter mAdapter;
    private final int mPosition;

    public ChatRow1(Context context, MessageExt messageExt, int position, BaseAdapter adapter) {
        super(context, messageExt, position, adapter);
        mContext = context;
        mMessageExt = messageExt;
        mAdapter = adapter;
        mPosition = position;
    }

    @Override
    protected void onInflatView() {
        inflater.inflate(R.layout.custom_ease_row_received_article, this);
    }

    @Override
    protected void onFindViewById() {
        mTitleTxt = (TextView) findViewById(R.id.article_title_txt);
        mThumbnailImg = (ImageView) findViewById(R.id.article_picture_img);
        mDigestTxt = (TextView) findViewById(R.id.article_content_txt);
    }

    @Override
    protected void onUpdateView() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSetUpView() {
        mTitleTxt.setText(mMessageExt.getTitle());
        if (!TextUtils.isEmpty(mMessageExt.getPicture())) {
            Glide.with(mContext)
                    .load(mMessageExt.getPicture())
                    .placeholder(R.drawable.ic_launcher)
                    .into(mThumbnailImg);
            mThumbnailImg.setVisibility(View.VISIBLE);
        } else {
            mThumbnailImg.setVisibility(View.GONE);     // 没有略缩图就不显示
        }
        mDigestTxt.setText(mMessageExt.getContent());
    }

    /**
     * 消息点击时被调用
     */
    @Override
    protected void onBubbleClick() {
        Log.i(TAG, "Click message:" + mMessageExt.toString());
    }
}
