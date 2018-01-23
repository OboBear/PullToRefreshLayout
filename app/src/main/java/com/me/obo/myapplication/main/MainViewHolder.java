package com.me.obo.myapplication.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.me.obo.myapplication.databinding.ItemMainBinding;

/**
 * @author obo
 * @date 2018/1/23
 */

public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemMainBinding mItemMainBinding;
    private MainViewModel mMainViewModel;

    public MainViewHolder(View itemView, ItemMainBinding itemMainBinding) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.mItemMainBinding = itemMainBinding;
    }

    public void setMainModel(MainViewModel mainModel) {
        mItemMainBinding.setMainViewModel(mainModel);
        mMainViewModel = mainModel;
    }

    @Override
    public void onClick(View v) {
        v.getContext().startActivity(new Intent(v.getContext(), mMainViewModel.getTarget()));
    }
}
