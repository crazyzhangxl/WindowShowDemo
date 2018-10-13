package com.example.windowshowdemo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.silencedut.router.Router;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author zxl on 2018/5/22.
 *         discription:弹出的喜欢的Fragment
 */

public class ShowEnjoyDialogFragment extends DialogFragment{
    private RecyclerView mRv;
    private TextView mTVSubmit;
    private TextView mTvFive;
    private List<String> mList = new ArrayList<>();
    private List<Integer> mSelected = new ArrayList<>();
    private Context mContext;
    private BaseQuickAdapter<String,BaseViewHolder> mBaseAdapter;
    private String mResult;
    public static String[] ENJOY_ARRAYS = {"聚会","高科技","运动健身","购物狂",
            "理财","影视","音乐","自驾","读书","画画","DIY","游戏","涨知识",
            "旅游","汽车","居家","cosplay","其他"};
    private static final String HOBBY_KEY_ENJOY = "hobby_key_enjoy";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.MyDialog);
        mContext = getActivity();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_dialog_show_enjoy);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = getActivity().getWindowManager().getDefaultDisplay().getHeight() * 2/ 3;
        window.setAttributes(lp);
        getDataFromBefore();
        initView(dialog);
        // 窗口初始化后 请求网络数据
        return dialog;
    }

    private void getDataFromBefore() {
        mList = Arrays.asList(ENJOY_ARRAYS);
        Bundle bundle = getArguments();
        if (bundle != null){
            ArrayList<String> enjoy = bundle.getStringArrayList(HOBBY_KEY_ENJOY);
            if (enjoy == null || enjoy.size() ==0){
                return;
            }
            for (String str:enjoy){
                mSelected.add(mList.indexOf(str));
            }
        }
    }

    private void initView(Dialog dialog) {
        mRv =  (RecyclerView)dialog.findViewById(R.id.rv);
        mTVSubmit = (TextView) dialog.findViewById(R.id.tvBtn);
        mTvFive =(TextView)dialog.findViewById(R.id.tvFive);
        setAdapter();
        setListener();

    }

    private void setListener() {
        mTVSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelected.size() == 0){
                    Toast.makeText(mContext, "客官请至少选择一个兴趣", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder sb = new StringBuilder();
                ArrayList<String> mArrays = new ArrayList<>();
                for (Integer integer:mSelected){
                    mArrays.add(mList.get(integer));
                    sb.append(mList.get(integer)).append(",");
                }
                mResult = sb.toString();
                Toast.makeText(mContext, mResult, Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

    }

    private void setAdapter() {
        if (mBaseAdapter == null) {
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),
                    3,
                    GridLayoutManager.VERTICAL,
                    false);
            mRv.setLayoutManager(layoutManager);
            mRv.addItemDecoration
                    (new GridSpacingItemDecoration(3, getResources().getDimensionPixelSize(R.dimen.common_dimension_10), true));
            mBaseAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_enjoy_text,mList) {
                @Override
                protected void convert(BaseViewHolder helper, String item) {
                    final int position = helper.getAdapterPosition();
                    final TextView tvEnjoy = helper.getView(R.id.tvEnjoy);
                    if (mSelected != null&&mSelected.size()!=0 && mSelected.contains((Integer)position)){
                        tvEnjoy.setTextColor(ContextCompat.getColor(mContext,R.color.red0));
                        tvEnjoy.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bg_enjoy_pink));
                    }
                    tvEnjoy.setText(item);
                    tvEnjoy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mSelected != null && mSelected.size()<=5){
                                if (mSelected.contains(position)){
                                    mSelected.remove((Integer)position);
                                    tvEnjoy.setTextColor(ContextCompat.getColor(mContext,R.color.black));
                                    tvEnjoy.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bg_enjoy_normal));
                                }else {
                                    if (mSelected.size() == 5) {
                                        Toast.makeText(mContext, "不超过5个", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    mSelected.add(position);
                                    tvEnjoy.setTextColor(ContextCompat.getColor(mContext,R.color.red0));
                                    tvEnjoy.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bg_enjoy_pink));
                                }
                            }
                            if (mSelected.size() == 5){
                                mTvFive.setVisibility(View.VISIBLE);
                            }else {
                                mTvFive.setVisibility(View.GONE);
                            }
                        }
                    });

                }
            };
        }
        if (mSelected.size() == 5) {
            mTvFive.setVisibility(View.VISIBLE);
        }
        mRv.setAdapter(mBaseAdapter);
    }
}
