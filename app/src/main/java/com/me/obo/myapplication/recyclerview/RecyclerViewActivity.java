package com.me.obo.myapplication.recyclerview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.me.obo.myapplication.R;
import com.me.obo.myapplication.databinding.ActivityRecyclerBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author obo
 * @date 2018/1/23
 */

public class RecyclerViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecyclerBinding activityRecyclerBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycler);
        activityRecyclerBinding.rvRecycler.setLayoutManager(new LinearLayoutManager(this));
        activityRecyclerBinding.rvRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        activityRecyclerBinding.rvRecycler.setAdapter(new RecyclerViewAdapter(this, genernalViewModels()));
    }

    private List<RecyclerViewModel> genernalViewModels(){
        List<RecyclerViewModel> recyclerViewModels = new ArrayList<>();
        for (int i = 0; i < 30; i ++) {
            recyclerViewModels.add(new RecyclerViewModel(i + ""));
        }

        return recyclerViewModels;
    }
}
