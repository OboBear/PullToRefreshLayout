package com.me.obo.myapplication.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.me.obo.myapplication.R;

import java.util.List;

/**
 * @author obo
 * @date 2018/1/23
 */

public class MainListViewAdapter extends BaseAdapter {
    private List<MainViewModel> mMainViewModels;
    private LayoutInflater mLayoutInflater;
    public MainListViewAdapter(Context context, List<MainViewModel> mainViewModels) {
        mMainViewModels = mainViewModels;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mMainViewModels.size();
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
            convertView = mLayoutInflater.inflate(R.layout.item_main, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.tv_main_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(mMainViewModels.get(position).getItemName());
        return convertView;
    }


    private static class ViewHolder {
        TextView textView;
    }

}
