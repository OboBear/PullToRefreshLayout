package com.me.obo.myapplication.listview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.me.obo.myapplication.R;
import com.me.obo.myapplication.databinding.ActivityListviewBinding;
import com.me.obo.myapplication.pullrefresh.PullToRefreshListener;
import com.me.obo.myapplication.viewmodel.CustomViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author obo
 * @date 2018/1/23
 */

public class ListViewActivity extends AppCompatActivity implements PullToRefreshListener {
    ActivityListviewBinding mActivityListviewBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityListviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_listview);
        mActivityListviewBinding.lvList.setAdapter(new ListViewAdapter(this, genernalViewModels()));
        mActivityListviewBinding.plRefreshLayout.setPullToRefreshListener(this);
    }

    private List<CustomViewModel> genernalViewModels(){
        List<CustomViewModel> recyclerViewModels = new ArrayList<>();
        for (int i = 0; i < 30; i ++) {
            recyclerViewModels.add(new CustomViewModel(i + ""));
        }

        return recyclerViewModels;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mActivityListviewBinding.plRefreshLayout.stopRefreshing();
            }
        }, 4000);
    }
}
