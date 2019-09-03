package com.example.windowshowdemo.model;

import java.io.Serializable;

/**
 * Created by apple on 2019-09-03.
 * description: 喜欢的数据bean
 */
public class EnjoyBean implements Serializable {
    private int id;
    private String description;
    private boolean isSelected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public EnjoyBean(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
