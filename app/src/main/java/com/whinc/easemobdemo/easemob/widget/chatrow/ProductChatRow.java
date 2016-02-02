package com.whinc.easemobdemo.easemob.widget.chatrow;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.whinc.easemobdemo.R;
import com.whinc.easemobdemo.easemob.message.ProductMessage;
import com.whinc.widget.ratingbar.RatingBar;

/**
 * ${END}
 * <p>
 * <p>Created by whinc on 2016/2/1.
 * Email:xiaohui_hubei@163.com</p>
 */
public class ProductChatRow extends BaseChatRow{

    private final ProductMessage mMessage;

    private TextView mMedalTxt;
    private TextView mContentTxt;
    private ImageView mThumbnailImg;
    private TextView mGlamourTxt;
    private RatingBar mWhiteRating;
    private RatingBar mThinRating;
    private RatingBar mHeightRating;

    public ProductChatRow(Context context, ProductMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
        mMessage = message;
    }

    @Override
    protected void onInflatView() {
        inflater.inflate(R.layout.custom_ease_row_received_product, this, true);
    }

    @Override
    protected void onFindViewById() {
        mMedalTxt = (TextView) findViewById(R.id.medal_txt);
        mContentTxt = (TextView) findViewById(R.id.content_txt);
        mThumbnailImg = (ImageView) findViewById(R.id.thumbnail_img);
        mGlamourTxt = (TextView) findViewById(R.id.glamour_value_txt);
        mWhiteRating = (RatingBar) findViewById(R.id.white_rb);
        mThinRating = (RatingBar) findViewById(R.id.thin_rb);
        mHeightRating = (RatingBar) findViewById(R.id.height_rb);
    }

    @Override
    protected void onUpdateView() {

    }

    @Override
    protected void onSetUpView() {
        mMedalTxt.setText(getMedalTextRes());
        Glide.with(getContext())
                .load(mMessage.getPicture())
                .placeholder(R.drawable.ease_default_image)
                .into(mThumbnailImg);
        mContentTxt.setText(mMessage.getContent());
        mGlamourTxt.setText(String.format("%+.1f", mMessage.getGlamour()));
        mWhiteRating.setCount(getStar(mMessage.getWhite()));
        mThinRating.setCount(getStar(mMessage.getThin()));
        mHeightRating.setCount(getStar(mMessage.getHigh()));
    }

    private int getStar(float value) {
        int star = (int) Math.ceil(value);     // 向上取整
        int maxStar = getResources().getInteger(R.integer.max_star);
        int minStar = getResources().getInteger(R.integer.min_star);
        star = Math.max(minStar, Math.min(star, maxStar));
        return star;
    }

    // 获取显示在奖牌上的文字（取最高值）
    @StringRes
    private int getMedalTextRes() {
        int strRes = R.string.label_white;
        float max = mMessage.getWhite();
        if (mMessage.getThin() > max) {
            max = mMessage.getThin();
            strRes = R.string.label_thin;
        }
        if (mMessage.getHigh() > max) {
            max = mMessage.getHigh();
            strRes = R.string.label_height;
        }
        return strRes;
    }

    @Override
    protected void onBubbleClick() {

    }
}
