package com.me.obo.myapplication.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.me.obo.myapplication.databinding.ItemLayoutBinding;
import com.me.obo.myapplication.viewmodel.CustomViewModel;

import java.util.List;

/**
 * @author obo
 * @date 2018/1/23
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private List<CustomViewModel> mRecyclerViewModels;
    private LayoutInflater mLayoutInflater;
    public RecyclerViewAdapter(Context context, List<CustomViewModel> mainViewModels) {
        mRecyclerViewModels = mainViewModels;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLayoutBinding itemMainBinding = ItemLayoutBinding.inflate(mLayoutInflater, parent, false);
        return new RecyclerViewHolder(itemMainBinding.getRoot(), itemMainBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerViewHolder mainViewHolder = (RecyclerViewHolder) holder;
        mainViewHolder.setViewModel(mRecyclerViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewModels.size();
    }
}
