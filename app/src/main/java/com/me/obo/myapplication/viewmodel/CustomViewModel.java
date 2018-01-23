package com.me.obo.myapplication.viewmodel;

/**
 * @author obo
 * @date 2018/1/23
 */

public class CustomViewModel {
    private String mItemName;
    public CustomViewModel(String itemName) {
        this.mItemName = itemName;
    }
    public String getItemName() {
        return mItemName;
    }
}
