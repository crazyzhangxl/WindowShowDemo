package com.example.windowshowdemo;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.windowshowdemo.model.EnjoyBean;
import com.silencedut.router.Router;

import java.io.Serializable;
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
    private Context mContext;
    private BaseQuickAdapter<EnjoyBean,BaseViewHolder> mBaseAdapter;
    private List<EnjoyBean> mEnjoyBeans = new ArrayList<>();
    private static final String HOBBY_KEY_ENJOY = "hobby_key_enjoy";
    private EnsureListener mEnsureListener;
    public interface EnsureListener{
        void onEnsureData(List<EnjoyBean> mList);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置数据...
        _initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            List<EnjoyBean> enjoyBeans = (List<EnjoyBean>) arguments.getSerializable("list");
            if (enjoyBeans.size() == 0){
                return;
            }
            for (int i=0;i<mEnjoyBeans.size();i++){
                int id = mEnjoyBeans.get(i).getId();
                for (int j=0;j<enjoyBeans.size();j++){
                    if (enjoyBeans.get(j).getId() == id){
                        mEnjoyBeans.get(i).setSelected(true);
                        break;
                    }
                }
            }
        }
    }

    // 初始化数据
    private void _initData() {
        mEnjoyBeans.add(new EnjoyBean(0,"聚会"));
        mEnjoyBeans.add(new EnjoyBean(1,"高科技"));
        mEnjoyBeans.add(new EnjoyBean(2,"运动健身"));
        mEnjoyBeans.add(new EnjoyBean(3,"购物狂"));
        mEnjoyBeans.add(new EnjoyBean(4,"理财"));
        mEnjoyBeans.add(new EnjoyBean(5,"影视"));
        mEnjoyBeans.add(new EnjoyBean(6,"音乐"));
        mEnjoyBeans.add(new EnjoyBean(7,"自驾"));
        mEnjoyBeans.add(new EnjoyBean(8,"读书"));
        mEnjoyBeans.add(new EnjoyBean(9,"画画"));
        mEnjoyBeans.add(new EnjoyBean(10,"DIY"));
        mEnjoyBeans.add(new EnjoyBean(11,"游戏"));
        mEnjoyBeans.add(new EnjoyBean(12,"涨知识"));
        mEnjoyBeans.add(new EnjoyBean(13,"旅游"));
    }

    public static void showFragment(Activity activity,List<EnjoyBean> enjoys,EnsureListener ensureListener){
        ShowEnjoyDialogFragment enjoyDialogFragment = new ShowEnjoyDialogFragment();
        enjoyDialogFragment.setEnsureListener(ensureListener);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) enjoys);
        enjoyDialogFragment.setArguments(bundle);
        enjoyDialogFragment.show(activity.getFragmentManager(),"enjoy");
    }

    private void setEnsureListener(EnsureListener ensureListener){
        this.mEnsureListener = ensureListener;
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
        initView(dialog);
        // 窗口初始化后 请求网络数据
        return dialog;
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
                if (selectNum() == 0){
                    Toast.makeText(mContext, "客官请至少选择一个兴趣", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<EnjoyBean> mSelectList = new ArrayList<>();
                for (int i=0;i<mEnjoyBeans.size();i++){
                    if (mEnjoyBeans.get(i).isSelected()){
                        mSelectList.add(mEnjoyBeans.get(i));
                    }
                }
                if (mEnsureListener != null){
                    mEnsureListener.onEnsureData(mSelectList);
                }
                // 关闭
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
            mBaseAdapter = new BaseQuickAdapter<EnjoyBean, BaseViewHolder>(R.layout.item_enjoy_text,mEnjoyBeans) {
                @Override
                protected void convert(BaseViewHolder helper, EnjoyBean item) {
                    final TextView tvEnjoy = helper.getView(R.id.tvEnjoy);
                    if (item.isSelected()){
                        tvEnjoy.setTextColor(ContextCompat.getColor(mContext,R.color.red0));
                        tvEnjoy.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bg_enjoy_pink));
                    }else {
                        tvEnjoy.setTextColor(ContextCompat.getColor(mContext,R.color.black));
                        tvEnjoy.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bg_enjoy_normal));
                    }
                    tvEnjoy.setText(item.getDescription());
                }
            };

            // 设置点击事件
            mBaseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (selectNum() == 5){
                        Toast.makeText(mContext, "不超过5个", Toast.LENGTH_SHORT).show();
                        mTvFive.setVisibility(View.VISIBLE);
                    }else {
                        // 设置选中状态...
                        mTvFive.setVisibility(View.GONE);
                        mEnjoyBeans.get(position).setSelected(true);
                        mBaseAdapter.notifyItemChanged(position);
                    }
                }
            });
        }
        if (selectNum() == 5){
            mTvFive.setVisibility(View.VISIBLE);
        }
        mRv.setAdapter(mBaseAdapter);
    }

    private int selectNum(){
        int selectSum = 0;
        for (int i=0;i<mEnjoyBeans.size();i++){
            if (mEnjoyBeans.get(i).isSelected()){
                selectSum ++;
            }
        }
        return  selectSum;
    }
}
