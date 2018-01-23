package com.me.obo.myapplication.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.me.obo.myapplication.databinding.ItemRecyclerBinding;

/**
 * @author obo
 * @date 2018/1/23
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    ItemRecyclerBinding mItemRecyclerBinding;
    public RecyclerViewHolder(View itemView, ItemRecyclerBinding itemRecyclerBinding) {
        super(itemView);
        this.mItemRecyclerBinding = itemRecyclerBinding;
    }
    public void setViewModel(RecyclerViewModel recyclerViewModel) {
        this.mItemRecyclerBinding.setRecyclerViewModel(recyclerViewModel);
    }

}
