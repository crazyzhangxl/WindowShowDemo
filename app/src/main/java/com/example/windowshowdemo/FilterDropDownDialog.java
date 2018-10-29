package com.example.windowshowdemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author crazyZhangxl on 2018/10/29.
 * Describe:
 */
public class FilterDropDownDialog extends FrameLayout implements View.OnClickListener {
    private Animation mAnimationIn;
    private Animation mAnimationOut;
    private Animation mMaskAnimationIn;
    private Animation mMaskAnimationOut;
    private View mContentFrame;
    private View maskView;
    private boolean isShow;
    private TextView mTextView;
    private Drawable drawableUp, drawableDown;
    public FilterDropDownDialog(@NonNull Context context, FrameLayout frameLayout, TextView textView) {
        super(context);
        initView(context);
        mTextView = textView;
        // 将布局加入
        frameLayout.addView(this,new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    private void initView(Context mContext) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.filter_drop_down_layout, null);
        maskView = view.findViewById(R.id.mask);
        mContentFrame = view.findViewById(R.id.contentFm);
        maskView.setOnClickListener(this);
        mContentFrame.setOnClickListener(this);
        addView(view);
        drawableUp = ContextCompat.getDrawable(mContext, R.mipmap.ic_arrow_top);
        drawableDown = ContextCompat.getDrawable(mContext, R.mipmap.ic_arrow_bottom);
        mAnimationIn = AnimationUtils.loadAnimation(mContext,R.anim.dd_menu_in);
        mAnimationOut = AnimationUtils.loadAnimation(mContext,R.anim.dd_menu_out);
        mMaskAnimationIn = AnimationUtils.loadAnimation(mContext,R.anim.dd_mask_in);
        mMaskAnimationOut = AnimationUtils.loadAnimation(mContext,R.anim.dd_mask_out);
        setVisibility(GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mask:
                dismiss();
                break;
            case R.id.contentFm:
                Toast.makeText(getContext(), "点击内容", Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }
    }

    public void dismiss(){
        isShow = false;
        mContentFrame.setVisibility(GONE);
        mContentFrame.startAnimation(mAnimationOut);
        maskView.setVisibility(GONE);
        maskView.startAnimation(mMaskAnimationOut);
        UIUtils.modifyTextViewDrawable(mTextView, drawableDown, 2);
    }

    public void show(){
        isShow = true;
        setVisibility(VISIBLE);
        mContentFrame.setVisibility(VISIBLE);
        mContentFrame.startAnimation(mAnimationIn);
        maskView.setVisibility(VISIBLE);
        maskView.startAnimation(mMaskAnimationIn);
        UIUtils.modifyTextViewDrawable(mTextView, drawableUp, 2);
    }

    public boolean isHasShow(){
        return isShow;
    }
}
