package com.example.windowshowdemo;

/**
 * @author zxl on 2018/6/28.
 *         discription:
 */

public class GalleryBean {
    private String mTitle;
    private boolean isChecked;

    public GalleryBean(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
