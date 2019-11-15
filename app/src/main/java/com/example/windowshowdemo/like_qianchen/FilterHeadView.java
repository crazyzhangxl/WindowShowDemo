package com.example.windowshowdemo.like_qianchen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.windowshowdemo.R;

/**
 * Created by apple on 2019-09-30.
 * description:
 */
public class FilterHeadView extends LinearLayout {

    private Context mContext;
    private LinearLayout mHeadView;
    // frame内容布局
    private FrameLayout mFlContent;
    private View mViewMask;
    private RelativeLayout rlContent;
    private int mContentHeight;
    private boolean isHasShow = false;

    public FilterHeadView(@NonNull Context context) {
        this(context,null);
    }

    public FilterHeadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FilterHeadView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
        initViews();

    }

    private void initViews() {
        mFlContent.setVisibility(GONE);
    }

    /**
     * 进行初始化的操作
     */
    private void init() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_filter_view,this);
        mHeadView = rootView.findViewById(R.id.llHead);
        mFlContent = rootView.findViewById(R.id.flContent);
        mViewMask = rootView.findViewById(R.id.view_mask_bg);
        rlContent = rootView.findViewById(R.id.rlContent);
        initListener();
    }

    private void initListener(){
        // 头部head的点击事件...
        mHeadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        mViewMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
    }



    private void show(){
        if (isHasShow){
            return;
        }
        isHasShow = true;
        mFlContent.setVisibility(VISIBLE);
        if (mContentHeight == 0){
            // 设置动画
            rlContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mContentHeight = rlContent.getMeasuredHeight();
                    rlContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    // 测量需要一定时间
                    // 从顶部进入界面
                    ObjectAnimator outIn = ObjectAnimator.ofFloat(rlContent, "translationY", -mContentHeight, 0);
                    outIn.setDuration(400);
                    outIn.start();
                }
            });
        }else {
            ObjectAnimator outIn = ObjectAnimator.ofFloat(rlContent, "translationY", -mContentHeight, 0);
            outIn.setDuration(400);
            outIn.start();
        }

    }

    private void hide(){
        ObjectAnimator outOut = ObjectAnimator.ofFloat(rlContent, "translationY", 0, -mContentHeight);
        outOut.setDuration(400);
        outOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mFlContent.setVisibility(GONE);
                isHasShow = false;
            }
        });
        outOut.start();
    }


}
