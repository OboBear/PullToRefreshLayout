package com.me.obo.myapplication.recyclerview;

/**
 * @author obo
 * @date 2018/1/23
 */

public class RecyclerViewModel {
    private String mItemName;
    public RecyclerViewModel(String itemName) {
        this.mItemName = itemName;
    }
    public String getItemName() {
        return mItemName;
    }
}
