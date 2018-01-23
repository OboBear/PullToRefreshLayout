package com.me.obo.myapplication.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.me.obo.myapplication.databinding.ItemLayoutBinding;
import com.me.obo.myapplication.viewmodel.CustomViewModel;

/**
 * @author obo
 * @date 2018/1/23
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    ItemLayoutBinding mItemLayoutBinding;
    public RecyclerViewHolder(View itemView, ItemLayoutBinding itemLayoutBinding) {
        super(itemView);
        this.mItemLayoutBinding = itemLayoutBinding;
    }
    public void setViewModel(CustomViewModel recyclerViewModel) {
        this.mItemLayoutBinding.setRecyclerViewModel(recyclerViewModel);
    }

}
