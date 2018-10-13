package com.example.windowshowdemo;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * @author zxl on 2018/07/25.
 *         discription: 这是一个对Dialog弹出框的总结学习
 *
 *         既有系统弹出框Dialog 也有 Fragment形式的 DialogFragment 还有普通的View的形式。 下面就一起来看看吧
 *         
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnDialog;
    private Button mBtnDgFm;
    private Button mBtnNormalShow;
    private TextView mTvHobby;
    private TextView mTvAddress;
    private TextView mTvChoose;
    private ScrollView mSvMenu;
    private RelativeLayout mRlMenu;
    private TextView mTvSaveToPhone;
    private TextView mTvOpenQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 设置沉浸式状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        initListener();
    }

    private void initListener() {
        mBtnDgFm.setOnClickListener(this);
        mBtnDialog.setOnClickListener(this);
        mBtnNormalShow.setOnClickListener(this);
        mTvSaveToPhone.setOnClickListener(this);
        mTvOpenQRCode.setOnClickListener(this);
        mRlMenu.setOnClickListener(this);

    }

    private void initView() {
        mBtnDialog = findViewById(R.id.btnDialog);
        mBtnDgFm = findViewById(R.id.btnDgFm);
        mBtnNormalShow = findViewById(R.id.normalShow);
        mTvHobby = findViewById(R.id.tvHobby);
        mTvAddress = findViewById(R.id.tvAddress);
        mTvChoose = findViewById(R.id.tvChoose);
        mRlMenu = findViewById(R.id.rlMenu);
        mSvMenu = findViewById(R.id.svMenu);
        mTvSaveToPhone = findViewById(R.id.tvSaveToPhone);
        mTvOpenQRCode = findViewById(R.id.openQrcode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDgFm:
                ShowEnjoyDialogFragment showEnjoyDialogFragment = new ShowEnjoyDialogFragment();
                Bundle bundle = new Bundle();
                // bundle.putStringArrayList(AppConst.HOBBY_KEY_ENJOY, mHobbyStrList);
                showEnjoyDialogFragment.setArguments(bundle);
                showEnjoyDialogFragment.show(getFragmentManager(), "showEnjoyDialogFragment");
                break;
            case R.id.btnDialog:
                showPurchaseView();
                break;
            case R.id.normalShow:
                showBottomMenu();
                break;
            case R.id.tvSaveToPhone:
                mTvChoose.setText("选择了保存到手机");
                hideBottomMenu();
                break;
            case R.id.openQrcode:
                mTvChoose.setText("选择了打开二维码");
                hideBottomMenu();
                break;
            case R.id.rlMenu:
                hideBottomMenu();
                break;
            default:
                break;
        }
    }

    private void showPurchaseView() {
        // 以特定的风格创建一个dialog
        Dialog dialog = new Dialog(this,R.style.MyDialog);
        // 加载dialog布局view
        View purchase = LayoutInflater.from(this).inflate(R.layout.dialog_purchase, null);
        // 设置外部点击 取消dialog
        dialog.setCancelable(true);
        // 获得窗体对象
        Window window = dialog.getWindow();
        // 设置窗体的对齐方式
        window.setGravity(Gravity.BOTTOM);
        // 设置窗体动画
        window.setWindowAnimations(R.style.AnimBottom);
        // 设置窗体的padding
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialog.setContentView(purchase);
        dialog.show();
    }

    private void showBottomMenu() {
        // 显示菜单
        mRlMenu.setVisibility(View.VISIBLE);
        // 设置动画
        TranslateAnimation ta =
                new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                        0,
                        Animation.RELATIVE_TO_SELF,
                        0, Animation.RELATIVE_TO_SELF,
                        1,
                        Animation.RELATIVE_TO_SELF,
                        0);
        ta.setDuration(200);
        mSvMenu.startAnimation(ta);
    }

    private void hideBottomMenu(){
        TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF,
                0, Animation.RELATIVE_TO_SELF, 1);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mRlMenu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ta.setDuration(200);
        mSvMenu.startAnimation(ta);
    }

    @Override
    public void onBackPressed() {
        if (mRlMenu.getVisibility() == View.VISIBLE){
            hideBottomMenu();
            return;
        }
        super.onBackPressed();
    }
}
