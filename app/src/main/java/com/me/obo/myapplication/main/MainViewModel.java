package com.me.obo.myapplication.main;

import android.app.Activity;

/**
 * @author obo
 * @date 2018/1/23
 */

public class MainViewModel {
    private String mItemName;
    private Class mTarget;
    public MainViewModel(String itemName, Class target) {
        this.mItemName = itemName;
        this.mTarget = target;
    }

    public String getItemName() {
        return mItemName;
    }

    public Class getTarget() {
        return mTarget;
    }
}
