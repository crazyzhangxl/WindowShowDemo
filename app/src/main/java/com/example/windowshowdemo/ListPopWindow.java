package com.example.windowshowdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.decoration.RecycleViewDivider;
import com.luck.picture.lib.tools.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxl on 2018/6/28.
 *         discription: 自定义由上方弹出的窗体
 *          PopupWindow哦 哈哈
 */

public class ListPopWindow extends PopupWindow{
    private Context context;
    private View window;
    private Animation animationIn, animationOut;
    private boolean isDismiss = false;
    private LinearLayout id_ll_root;
    private BaseQuickAdapter<GalleryBean,BaseViewHolder> adapter;
    private List<GalleryBean> mList = new ArrayList<>();
    /* 用于修改上下箭头的 很牛皮啊!! 这里以后可以拿来用非常不错
    * 
    * */
    private TextView picture_title;
    private Drawable drawableUp, drawableDown;
    private RecyclerView recyclerView;
    private final RequestOptions mOptions;
    private OnPopItemSelectedListener mOnPopItemSelectedListener;
    public ListPopWindow(Context context, List<GalleryBean> mList) {
        this.context = context;
        this.mList = mList;
        window = LayoutInflater.from(context).inflate(R.layout.pop_my_show, null);
        this.setContentView(window);
        this.setWidth(UIUtils.getScreenWidth(context));
        this.setHeight(UIUtils.getScreenHeight(context));
        this.setAnimationStyle(R.style.WindowStyle);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable(new ColorDrawable(Color.argb(123, 0, 0, 0)));
        /* 获得图片*/
        drawableUp = ContextCompat.getDrawable(context, R.mipmap.ic_arrow_top);
        drawableDown = ContextCompat.getDrawable(context, R.mipmap.ic_arrow_bottom);
        animationIn = AnimationUtils.loadAnimation(context, R.anim.photo_album_show);
        animationOut = AnimationUtils.loadAnimation(context, R.anim.photo_album_dismiss);
        mOptions = new RequestOptions()
                .placeholder(com.luck.picture.lib.R.drawable.ic_placeholder)
                .centerCrop()
                .sizeMultiplier(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(160, 160);
        initView();
    }

    public void setPictureTitleView(TextView picture_title) {
        this.picture_title = picture_title;
    }

    private void initView() {
        id_ll_root = (LinearLayout) window.findViewById(R.id.llPopRoot);
        recyclerView = window.findViewById(R.id.rvList);
        /* 设置列表的高度 ----- */
        recyclerView.getLayoutParams().height = (int) (ScreenUtils.getScreenHeight(context) * 0.6);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                context, LinearLayoutManager.HORIZONTAL, 0, ContextCompat.getColor(context, R.color.colorTransparent)));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BaseQuickAdapter<GalleryBean, BaseViewHolder>(R.layout.item_pic_pop_show,mList) {
            @Override
            protected void convert(BaseViewHolder helper, GalleryBean item) {
                helper.setText(R.id.tvTitleName,item.getTitle());

                helper.itemView.setSelected(item.isChecked());

                ImageView view = helper.getView(R.id.first_image);
                Glide.with(context)
                        .asBitmap()
                        .load("")
                        .apply(mOptions)
                        .into(new BitmapImageViewTarget(view) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.
                                                create(mContext.getResources(), resource);
                                circularBitmapDrawable.setCornerRadius(8);
                                view.setImageDrawable(circularBitmapDrawable);
                            }
                        });
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (GalleryBean bean:mList){
                    bean.setChecked(false);
                }
                mList.get(position).setChecked(true);
                adapter.notifyDataSetChanged();
                if (mOnPopItemSelectedListener != null){
                    mOnPopItemSelectedListener.popItemSelected(mList.get(position).getTitle());
                }
            }
        });


        recyclerView.setAdapter(adapter);
        id_ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListPopWindow.this.dismiss();
            }
        });
    }

    @Override
    public void showAsDropDown(View anchor) {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                Rect rect = new Rect();
                anchor.getGlobalVisibleRect(rect);
                int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
                setHeight(h);
            }
            super.showAsDropDown(anchor);
            isDismiss = false;
            id_ll_root.startAnimation(animationIn);
            UIUtils.modifyTextViewDrawable(picture_title, drawableUp, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        if (isDismiss) {
            return;
        }
        UIUtils.modifyTextViewDrawable(picture_title, drawableDown, 2);
        isDismiss = true;
        id_ll_root.startAnimation(animationOut);
        dismiss();
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
                    ListPopWindow.super.dismiss();
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
                ListPopWindow.super.dismiss();
            }
        });
    }

    public interface OnPopItemSelectedListener{
        void popItemSelected(String title);
    }

    public void setOnItemSelectedListener(OnPopItemSelectedListener onItemSelectedListener){
        this.mOnPopItemSelectedListener = onItemSelectedListener;
    }
}
