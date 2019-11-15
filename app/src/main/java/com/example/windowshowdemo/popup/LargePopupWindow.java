package com.example.windowshowdemo.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;

import com.example.windowshowdemo.DisplayHelper;
import com.example.windowshowdemo.R;

/**
 * Created by apple on 2019-09-11.
 * description:适配全面屏解决版本过高，popupWindow的位置不在控件下方的问题
 *
 * 注意是可以设置pop窗体样式的，当然这是我们不设置其整体背景的情况下，也就是背景透明时；
 * 而当背景灰度再设置其动画，致使其灰度窗体效果不佳
 *
 * (任意位置弹窗效果)
 */
public class LargePopupWindow extends PopupWindow {
    private View mRootView;
    private Context context;
    private Animation animationIn, animationOut;
    private boolean isDismiss = false;

    public LargePopupWindow(Context context, View view) {
        // 设置宽高
        super(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //设置有焦点
        setContentView(view);
        setFocusable(true);
        //设置点击外部可消失
        setOutsideTouchable(true);
        // 设置窗口样式
        // 整体窗体样式附属了遮层，需要考虑设置..
        // this.setAnimationStyle(R.style.WindowStyle);
        setBackgroundDrawable(new ColorDrawable(Color.argb(123, 0, 0, 0)));
        update();
        this.context = context;
        this.mRootView = view;
        initViews();
    }

    private void initViews() {
        animationIn = AnimationUtils.loadAnimation(context, R.anim.photo_album_show);
        animationOut = AnimationUtils.loadAnimation(context, R.anim.photo_album_dismiss);
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void showAsDropDown(View anchor) {
        if(Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            // 适配全面屏....
            // 解决全面屏手机使用顶部弹窗带来的问题
            if (DisplayHelper.isNavigationBarExist((Activity) context)){
                int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
                setHeight(h);
            }else {
                //全面屏手机需要获取真实高度....
                int h = DisplayHelper.getRealScreenSize(context)[1] - rect.bottom;
                setHeight(h);
            }
        }
        super.showAsDropDown(anchor);
        mRootView.startAnimation(animationIn);
    }

    @Override
    public void dismiss() {
        if (isDismiss){
            return;
        }
        isDismiss = true;
        mRootView.startAnimation(animationOut);
        animationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isDismiss = false;
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                    dismiss4Pop();
                } else {
                    LargePopupWindow.super.dismiss();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 在android4.1.1和4.1.2版本关闭PopWindow
     */
    private void dismiss4Pop() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                LargePopupWindow.super.dismiss();
            }
        });
    }
}
