package com.me.obo.myapplication.recyclerview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.me.obo.myapplication.R;
import com.me.obo.myapplication.databinding.ActivityRecyclerBinding;
import com.me.obo.myapplication.pullrefresh.PullToRefreshListener;
import com.me.obo.myapplication.viewmodel.CustomViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author obo
 * @date 2018/1/23
 */

public class RecyclerViewActivity extends AppCompatActivity implements PullToRefreshListener {
    private static final String TAG = "RecyclerViewActivity";
    ActivityRecyclerBinding mActivityRecyclerBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityRecyclerBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycler);
        mActivityRecyclerBinding.rvRecycler.setLayoutManager(new LinearLayoutManager(this));
        mActivityRecyclerBinding.rvRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mActivityRecyclerBinding.rvRecycler.setAdapter(new RecyclerViewAdapter(this, genernalViewModels()));
        mActivityRecyclerBinding.plRefreshLayout.setPullToRefreshListener(this);
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
                Log.i(TAG, "stopRefreshing");
                mActivityRecyclerBinding.plRefreshLayout.stopRefreshing();
            }
        }, 4000);

    }
}
