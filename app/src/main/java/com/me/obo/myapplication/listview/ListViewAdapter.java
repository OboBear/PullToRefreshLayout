package com.me.obo.myapplication.listview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.me.obo.myapplication.R;
import com.me.obo.myapplication.databinding.ItemLayoutBinding;
import com.me.obo.myapplication.viewmodel.CustomViewModel;

import java.util.List;

/**
 * @author obo
 * @date 2018/1/23
 */

public class ListViewAdapter extends BaseAdapter {
    private List<CustomViewModel> mRecyclerViewModels;
    private LayoutInflater mLayoutInflater;
    public ListViewAdapter(Context context,  List<CustomViewModel> mainViewModels) {
        mRecyclerViewModels = mainViewModels;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mRecyclerViewModels.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            ItemLayoutBinding itemLayoutBinding = ItemLayoutBinding.inflate(mLayoutInflater, parent, false);
            convertView = itemLayoutBinding.getRoot();
            viewHolder = new ViewHolder(itemLayoutBinding);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.getItemLayoutBinding().setRecyclerViewModel(mRecyclerViewModels.get(position));

        return convertView;
    }

    private static class ViewHolder {
        ItemLayoutBinding mItemLayoutBinding;
        public ViewHolder(ItemLayoutBinding itemLayoutBinding) {
            mItemLayoutBinding = itemLayoutBinding;
        }
        public ItemLayoutBinding getItemLayoutBinding() {
            return mItemLayoutBinding;
        }
    }
}
