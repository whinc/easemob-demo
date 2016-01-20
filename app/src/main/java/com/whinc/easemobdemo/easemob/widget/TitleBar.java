package com.whinc.easemobdemo.easemob.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whinc.easemobdemo.R;

/**
 * Created by Administrator on 2016/1/20.
 */
public class TitleBar extends Toolbar{

    private TextView mCenterTitleTxt;

    public TitleBar(Context context) {
        super(context);
        init();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mCenterTitleTxt = new TextView(getContext(), null, R.attr.titleBarCenterTextStyle);
        addView(mCenterTitleTxt, new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
        ));


        if (isInEditMode()) {
            mCenterTitleTxt.setText("Center Title");
            setTitle("Title");
//            setSubtitle("Sub Title");
        }
    }

    public void setCenterTitle(String title) {
        mCenterTitleTxt.setText(title);
    }

    public void setCenterTitle(@StringRes int strRes) {
        mCenterTitleTxt.setText(strRes);
    }

    public void showBackIcon(boolean show) {
        if (show) {
            setNavigationIcon(R.drawable.ic_menu_back);
            setNavigationOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = getContext();
                    if (context instanceof Activity) {
                        ((Activity) context).finish();
                    }
                }
            });
        } else {
            setNavigationIcon(0);
            setNavigationOnClickListener(null);
        }
    }
}
