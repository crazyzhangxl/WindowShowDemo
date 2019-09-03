package com.example.windowshowdemo.dialogfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.windowshowdemo.R;

/**
 * Created by apple on 2019-09-03.
 * description:版本升级的Fragment
 *
 */
public class UpdateFragment extends BaseDialogFragment {
    private ImageView mIvClose;

    @Override
    protected boolean isCancel() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setLocal(Local.CENTER);
        super.onCreate(savedInstanceState);
    }

    public static void showFragment(FragmentActivity activity){
        UpdateFragment updateFragment = new UpdateFragment();
        updateFragment.show(activity.getSupportFragmentManager());
    }

    @Override
    public int getLayoutRes() {
        return R.layout.lib_update_app_dialog;
    }

    @Override
    public void bindView(View v) {
        mIvClose = v.findViewById(R.id.iv_close);
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭
                dismissDialog();
            }
        });
    }
}
