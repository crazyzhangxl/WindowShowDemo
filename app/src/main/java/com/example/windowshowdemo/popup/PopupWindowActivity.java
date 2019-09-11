package com.example.windowshowdemo.popup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.windowshowdemo.R;

/**
 *  popupwindow 弹窗窗学习...
 */
public class PopupWindowActivity extends AppCompatActivity {
    private View headView;
    private Button mBtnFirst;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        initViews();
        initListener();
    }

    private void initListener() {
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                }else {
                    mPopupWindow.showAsDropDown(headView);
                }
            }
        });
    }

    private void initViews() {
        headView = findViewById(R.id.head);
        initPopHead();
    }

    // 将pop置于头部下
    private void initPopHead(){
        View view = LayoutInflater.from(this).inflate(R.layout.pop_below_head, null, false);
        mPopupWindow = new LargePopupWindow(this,view);
    }
}
