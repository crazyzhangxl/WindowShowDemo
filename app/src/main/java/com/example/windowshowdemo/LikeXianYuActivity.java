package com.example.windowshowdemo;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windowshowdemo.xianyu.PubActivity;

/**
 * 仿咸鱼底部菜单界面效果
 */
public class LikeXianYuActivity extends AppCompatActivity {
    private RadioButton mRbHome,mRbPond,mRbPerson,mRbMsg;
    private RadioGroup mRbGroup;
    private ImageView mIvAdd;
    private TextView mTVDes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_xian_yu);
        initViews();
        initListener();
        mRbHome.setChecked(true);
    }

    private void initListener() {
        mRbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String text ="";
                switch (checkedId){
                    case R.id.rb_home:
                        text ="闲鱼";
                        break;
                    case R.id.rb_me:
                        text ="我的";
                        break;
                    case R.id.rb_message:
                        text = "消息";
                        break;
                    case R.id.rb_pond:
                        text = "鱼塘";
                        break;
                    default:
                        break;
                }
                mTVDes.setText(text);
            }
        });

        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PubActivity.show(LikeXianYuActivity.this);
            }
        });

    }

    private void initViews() {
        mRbHome = findViewById(R.id.rb_home);
        mRbPond = findViewById(R.id.rb_pond);
        mRbMsg = findViewById(R.id.rb_message);
        mRbPerson = findViewById(R.id.rb_me);
        mIvAdd = findViewById(R.id.rbAdd);
        mRbGroup = findViewById(R.id.radioGroup);
        mTVDes = findViewById(R.id.tvDes);

        Drawable dbHome = getResources().getDrawable(R.drawable.selector_home);
        dbHome.setBounds(0, 0, 40, 40);
        mRbHome.setCompoundDrawables(null, dbHome, null, null);

        Drawable dbPond = getResources().getDrawable(R.drawable.selector_pond);
        dbPond.setBounds(0, 0, 40, 40);
        mRbPond.setCompoundDrawables(null, dbPond, null, null);

        Drawable dbMsg = getResources().getDrawable(R.drawable.selector_message);
        dbMsg.setBounds(0, 0, 40, 40);
        mRbMsg.setCompoundDrawables(null, dbMsg, null, null);

        Drawable dbMe = getResources().getDrawable(R.drawable.selector_person);
        dbMe.setBounds(0, 0, 40, 40);
        mRbPerson.setCompoundDrawables(null, dbMe, null, null);
    }
}
