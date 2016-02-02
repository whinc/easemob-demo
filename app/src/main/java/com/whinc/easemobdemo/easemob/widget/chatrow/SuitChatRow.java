package com.whinc.easemobdemo.easemob.widget.chatrow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.whinc.easemobdemo.R;
import com.whinc.easemobdemo.easemob.message.SuitMessage;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * 搭配消息
 * <p/>
 * <p>Created by whinc on 2016/2/1.
 * Email:xiaohui_hubei@163.com</p>
 */
public class SuitChatRow extends BaseChatRow {
    private ImageView mThumbnailImg;
    private TextView mContentTxt;
    private TagFlowLayout mKeywordFlowLayout;
    private TextView mComfortTxt;

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
        mContentTxt = (TextView) findViewById(R.id.content_txt);
        mKeywordFlowLayout = (TagFlowLayout) findViewById(R.id.keyword_flowLayout);
        mComfortTxt = (TextView) findViewById(R.id.comfort_txt);
    }

    @Override
    protected void onUpdateView() {

    }

    @Override
    protected void onSetUpView() {
        Glide.with(getContext()).load(mMessage.getPicture()).into(mThumbnailImg);
        mContentTxt.setText(mMessage.getContent());

        String[] data = mMessage.getKeywordArray();
        mKeywordFlowLayout.setAdapter(new CustomTagAdapter(data));
        mKeywordFlowLayout.setVisibility(data.length == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onBubbleClick() {
        Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
    }

    static class CustomTagAdapter extends TagAdapter<String> {

        public CustomTagAdapter(String[] datas) {
            super(datas);
        }

        @Override
        public View getView(FlowLayout parent, int pos, String keyword) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.include_keyword_label, parent, false);
            TextView keywordTxt = (TextView) view.findViewById(R.id.text1);
            keywordTxt.setText(keyword);
            return view;
        }
    }
}
