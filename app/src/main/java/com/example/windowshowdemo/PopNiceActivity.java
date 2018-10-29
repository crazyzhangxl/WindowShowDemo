package com.example.windowshowdemo;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author crazyZhangxl on 2018-10-28 19:27:48.
 * Describe:
 */

public class PopNiceActivity extends AppCompatActivity implements ListPopWindow.OnPopItemSelectedListener {

    @BindView(R.id.picture_left_back)
    ImageView mPictureLeftBack;
    @BindView(R.id.picture_title)
    TextView mPictureTitle;
    @BindView(R.id.picture_right)
    TextView mPictureRight;
    @BindView(R.id.rl_picture_title)
    RelativeLayout mRlPictureTitle;
    private List<GalleryBean> mList = new ArrayList<>();
    private ListPopWindow mListPopWindow;
    private Unbinder mUnbinder;


    protected void init() {
        mList.add(new GalleryBean("第一条数据"));
        mList.add(new GalleryBean("第二条数据"));
        mList.add(new GalleryBean("第三条数据"));
        mList.add(new GalleryBean("第四条数据"));
        mList.add(new GalleryBean("第五条数据"));
        mList.add(new GalleryBean("第六条数据"));
        mList.add(new GalleryBean("第七条数据"));
        mList.add(new GalleryBean("第八条数据"));
        mList.add(new GalleryBean("第九条数据"));
        mList.add(new GalleryBean("第十条数据"));
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_nice);
        mUnbinder = ButterKnife.bind(this);
        init();
        mListPopWindow = new ListPopWindow(this,mList);
        mListPopWindow.setPictureTitleView(mPictureTitle);
        mListPopWindow.setOnItemSelectedListener(this);

        mPictureTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListPopWindow.isShowing()) {
                    mListPopWindow.dismiss();
                } else {
                    mListPopWindow.showAsDropDown(mRlPictureTitle);
                }
            }
        });
    }


    @Override
    public void popItemSelected(String title) {
        mPictureTitle.setText(title);
        mListPopWindow.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}
