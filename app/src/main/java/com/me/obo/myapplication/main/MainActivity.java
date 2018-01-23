package com.me.obo.myapplication.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.me.obo.myapplication.R;
import com.me.obo.myapplication.listview.ListViewActivity;
import com.me.obo.myapplication.recyclerview.RecyclerViewAdapter;
import com.me.obo.myapplication.recyclerview.RecyclerViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView mListView;
    private List<MainViewModel> mMainViewModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.lv_main);
        mListView.setAdapter(new MainListViewAdapter(this, mMainViewModels = generateMainViewModels()));
        mListView.setOnItemClickListener(this);
    }

    private List<MainViewModel> generateMainViewModels() {
        List<MainViewModel> mainViewModels = new ArrayList<>();
        mainViewModels.add(new MainViewModel("ListViewActivity", ListViewActivity.class));
        mainViewModels.add(new MainViewModel("RecyclerViewActivity", RecyclerViewActivity.class));
        return mainViewModels;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, mMainViewModels.get(position).getTarget()));
    }
}
