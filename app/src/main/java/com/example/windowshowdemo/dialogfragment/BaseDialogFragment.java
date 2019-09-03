package com.example.windowshowdemo.dialogfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.windowshowdemo.R;

/**
 * Created by apple on 2019-09-03.
 * description:基础的dialogFragment 抽象类
 * 参考文章:
 *    dialogFragment内存泄漏问题:https://www.jianshu.com/p/f2d6e6bc4b77
 *    android版本更新项目: https://github.com/WVector/AppUpdate
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private Local local = Local.BOTTOM;
    private Dialog dialog;
    private static final float DEFAULT_DIM = 0.6f;
    private static final String TAG = "base_dialog";

    /**
     * 设置方向
     * 注意 该方法应该在 onCreate() super方法前调用
     * @param local
     */
    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (local == Local.BOTTOM){
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog);
        }else {
            setStyle(DialogFragment.STYLE_NO_TITLE,R.style.CenterDialog);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dialog = getDialog();
        if(dialog!=null){
            if(dialog.getWindow()!=null){
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            }
            dialog.setCanceledOnTouchOutside(isCancel());
            dialog.setCancelable(isCancel());
        }
        View v = inflater.inflate(getLayoutRes(), container, false);
        bindView(v);
        return v;
    }

    /**
     * 设置是否可以cancel
     * @return
     */
    protected abstract boolean isCancel();

    /**
     * 获取布局资源文件
     * @return              布局资源文件id值
     */
    @LayoutRes
    public abstract int getLayoutRes();

    /**
     * 绑定
     * @param v             view
     */
    public abstract void bindView(View v);

    /**
     * 开始展示
     */
    @Override
    public void onStart() {
        super.onStart();
        if(dialog==null){
            dialog = getDialog();
        }
        Window window = dialog.getWindow();
        if(window!=null){
            WindowManager.LayoutParams params = window.getAttributes();
            // 半透明背景的灰度
            params.dimAmount = getDimAmount();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            if (getHeight() > 0) {
                params.height = getHeight();
            } else {
                DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
                params.height = (int) (displayMetrics.heightPixels * getPercent());
            }
            if (local == Local.BOTTOM){
                params.gravity = Gravity.BOTTOM;
            }else {
                params.gravity = Gravity.CENTER;
            }
            window.setAttributes(params);
        }
    }

    /**
     * 设置相对高度
     * 子类重写即可
     * @return
     */
    public double getPercent(){
        return 0.8f;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }


    public int getHeight() {
        return -1;
    }

    public float getDimAmount() {
        return DEFAULT_DIM;
    }

    public void show(FragmentManager fragmentManager) {
        if(fragmentManager!=null){
            show(fragmentManager,getFragmentTag());
        }else {
            Log.e("show","需要设置setFragmentManager");
        }
    }

    public String getFragmentTag() {
        return TAG;
    }


    public void dismissDialog(){
        if(dialog!=null && dialog.isShowing()){
            dialog.dismiss();
            dialog = null;
        }
    }
}
