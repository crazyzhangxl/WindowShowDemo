# WindowShowDemo
1、简介

本文将介绍的是从底部弹出窗体以供用户进行交互的例子，本文将介绍使用Dialog,View和DialogFragment的方式分别来进行实现。也是仿制了58同城的弹出喜好的UI显示和交互。弹出框真的是很常见的，在安卓中有广泛的用途，故而有必要对其进行好好的梳理，后面使用起来也能够得心应手。<br>

[apk下载](https://github.com/crazyzhangxl/WindowShowDemo/blob/master/app/apk/app-debug.apk)<br>

### 运行效果<br>
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;![image](https://github.com/crazyzhangxl/WindowShowDemo/blob/master/app/screenshots/1.png)
&#160;&#160;&#160;&#160;&#160;&#160;![image](https://github.com/crazyzhangxl/WindowShowDemo/blob/master/app/screenshots/2.png)<br>
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
![image](https://github.com/crazyzhangxl/WindowShowDemo/blob/master/app/screenshots/4.png)<br>
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;![image](https://github.com/crazyzhangxl/WindowShowDemo/blob/master/app/screenshots/5.png)
&#160;&#160;&#160;&#160;&#160;&#160;![image](https://github.com/crazyzhangxl/WindowShowDemo/blob/master/app/screenshots/6.png)<br><br>
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;![image](https://github.com/crazyzhangxl/WindowShowDemo/blob/master/app/screenshots/xianyu.png)
&#160;&#160;&#160;&#160;&#160;&#160;![image](https://github.com/crazyzhangxl/WindowShowDemo/blob/master/app/screenshots/ios.png)
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;![image](https://github.com/crazyzhangxl/WindowShowDemo/blob/master/app/screenshots/xianyu2.gif)<br><br>
2.1 Dialog形式

注释写的还是很清晰的，如下。

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

下面给出style的设置。 

    <!-- 设置 弹出弹入的动画  由下往上 然后再返回去-->
    <style name="AnimBottom" parent="@android:style/Animation">
        <item name="android:windowEnterAnimation">@anim/push_bottom_in</item>
        <item name="android:windowExitAnimation">@anim/push_bottom_out</item>
    </style>

    <!--底部弹框 样式-->
    <style name="MyDialog" parent="@android:style/Theme.Dialog">
        <item name="android:windowFrame">@null</item>
        <item name="android:windowIsFloating">true</item>
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowNoTitle">true</item>
        <item name="android:background">@android:color/transparent</item>
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="android:backgroundDimEnabled">true</item>
        <item name="android:backgroundDimAmount">0.6</item>
    </style>

 R.layout.dialog_purchase.xml

 其实这里还是挺重要的：

1. 得为布局设置白色背景 否则为dialog背景色

2. 这种错落的效果 是使用的 clipChildren = "false" 属性，需要去了解

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:orientation="vertical"
    android:clipChildren="false"
    android:layout_height="wrap_content">
    <!-- 该布局承载着 超出父布局的控件的部分的显示-->
    <View
        android:layout_width="match_parent"
        android:layout_height="40dp"/>
    <LinearLayout
        android:background="#fff"
        android:id="@+id/rl_1"
        android:orientation="horizontal"
        android:layout_width="match_parent"
        android:layout_height="128dp">
        <android.support.v7.widget.CardView
            android:id="@+id/cd"
            android:layout_gravity="bottom"
            android:layout_marginLeft="10dp"
            android:foreground="?android:attr/selectableItemBackground"
            android:layout_width="120dp"
            android:layout_height="150dp"
            card_view:cardBackgroundColor="#FFFFFF"
            card_view:cardCornerRadius="4dp"
            card_view:cardElevation="2dp"
            card_view:cardUseCompatPadding="true">
            <ImageView
                android:src="@drawable/shop"
                android:scaleType="fitXY"
                android:layout_width="120dp"
                android:layout_height="match_parent"/>
        </android.support.v7.widget.CardView>
        <LinearLayout
            android:layout_marginTop="30dp"
            android:layout_marginLeft="5dp"
            android:orientation="vertical"
            android:layout_width="0dp"
            android:layout_weight="1"
            android:layout_height="wrap_content">
            <TextView
                android:text="￥109"
                android:textColor="#f00"
                android:textSize="22sp"
                android:id="@+id/tv_pay_deserve"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"/>
            <LinearLayout
                android:layout_marginTop="10dp"
                android:orientation="horizontal"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content">
                <TextView
                    android:id="@+id/tv_des"
                    android:textColor="#C000"
                    android:textSize="14sp"
                    android:text="对商品的简要的描述：这件商品好好啊，欢迎来购买"
                    android:lines="2"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"/>

            </LinearLayout>

        </LinearLayout>
        <ImageView
            android:id="@+id/iv_back"
            android:padding="10dp"
            android:layout_alignParentRight="true"
            android:src="@mipmap/buy_delete"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    </LinearLayout>
    <View
        android:background="#fff"
        android:layout_width="match_parent"
        android:layout_height="100dp"/>
    <LinearLayout
        android:background="#fff"
        android:layout_width="match_parent"
        android:layout_height="45dp">

        <Button
            android:id="@+id/add_cart_btn"
            android:layout_weight="1.0"
            android:layout_width="0dip"
            android:layout_height="match_parent"
            android:text="加入购物车"
            android:textColor="#fff"
            android:background="@drawable/button_yellow_selector"
            android:layout_alignParentLeft="true"
            />

        <Button
            android:id="@+id/buy_btn"
            android:layout_weight="1.0"
            android:layout_width="0dip"
            android:layout_height="match_parent"
            android:text="立即购买"
            android:textColor="#fff"
            android:background="@drawable/button_selector"
            android:layout_alignParentRight="true"
            />
    </LinearLayout>
</LinearLayout>

2.2 DialogFragment形式

主要是创建一个Java类，然后继承自DialogFragment方法即可，下面给出部分代码

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

 如何从一个活动跳转向DialogFragment且传值呢

ShowEnjoyDialogFragment showEnjoyDialogFragment = new ShowEnjoyDialogFragment();
Bundle bundle = new Bundle();
// bundle.putStringArrayList(AppConst.HOBBY_KEY_ENJOY, mHobbyStrList);
showEnjoyDialogFragment.setArguments(bundle);
showEnjoyDialogFragment.show(getFragmentManager(), "showEnjoyDialogFragment");

2.3普通的View形式

这里的思想主要是通过布局的显示与隐藏加上动画来实现特定的效果

<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.windowshowdemo.MainActivity">
    <!-- 内容布局 -->
    <LinearLayout
        android:fitsSystemWindows="true"
        android:background="@color/colorPrimary"
        android:id="@+id/llContent"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <include
            layout="@layout/toolbar"/>
        <LinearLayout
            android:background="#fff"
            android:orientation="vertical"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            <Button
                android:text="Dialog形式"
                android:id="@+id/btnDialog"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
            <Button
                android:text="DialogFragment形式"
                android:id="@+id/btnDgFm"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
            <Button
                android:text="普通布局形式"
                android:id="@+id/normalShow"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
            <TextView
                android:id="@+id/tvAddress"
                android:layout_marginTop="25dp"
                android:layout_gravity="center_horizontal"
                android:text="区域"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
            <TextView
                android:text="喜好"
                android:id="@+id/tvHobby"
                android:layout_marginTop="25dp"
                android:layout_gravity="center_horizontal"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
            <TextView
                android:text="选择"
                android:id="@+id/tvChoose"
                android:layout_marginTop="25dp"
                android:layout_gravity="center_horizontal"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />
        </LinearLayout>
    </LinearLayout>
    <!-- 弹出框布局 上部分为灰色-->
    <RelativeLayout
        android:id="@+id/rlMenu"
        android:background="#32000000"
        android:fitsSystemWindows="true"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:visibility="gone">
        <ScrollView
            android:id="@+id/svMenu"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:fillViewport="true"
            android:layout_alignParentBottom="true"
            android:scrollbars="none">
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="#ffffff"
                android:gravity="center"
                android:orientation="vertical">
                <TextView
                    android:id="@+id/tvSaveToPhone"
                    android:textSize="16sp"
                    android:gravity="center_vertical"
                    android:text="保存到手机"
                    android:textColor="#7e7e7e"
                    android:layout_marginLeft="20dp"
                    android:layout_width="match_parent"
                    android:layout_height="51dp" />
                <TextView
                    android:id="@+id/openQrcode"
                    android:textSize="16sp"
                    android:gravity="center_vertical"
                    android:text="打开二维码"
                    android:textColor="#7e7e7e"
                    android:layout_marginLeft="20dp"
                    android:layout_width="match_parent"
                    android:layout_height="51dp" />
            </LinearLayout>
        </ScrollView>
    </RelativeLayout>

</android.support.design.widget.CoordinatorLayout>

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

总结：

好了，以上主要是起一个笔记的作用，希望对读者有一定的帮助，写的不对的地方请多多指教，谢谢<br>

[博客地址](https://blog.csdn.net/crazyZhangxl/article/details/81195593)
