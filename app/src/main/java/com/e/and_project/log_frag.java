package com.e.and_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class log_frag extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager; // 레이아웃 매니저 변수 // 리사이클러뷰는 레이아웃 종류가 많아서 변수로 원하는걸 받아서 정함
    ArrayList<log_MyItem> items=null;  // 넘겨줄 데이터 모음
    log_MyAdapter adapter;         // 리사이클러뷰 MyAdapter

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.log_frag,container,false);



        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        MainActivity activity = (MainActivity)getActivity();


        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);


        recyclerView = (RecyclerView) view.findViewById(R.id.listview);
        layoutManager = new LinearLayoutManager(getContext()); // 리스트형으로 설정
        recyclerView.setLayoutManager(layoutManager); //리사이클러뷰와 레이아웃 매니저를 연결
        items = new ArrayList<log_MyItem>();



        items.add(new log_MyItem("cham0", "cham1", "cham1","cham1","cham1","cham1","cham1","cham1","cham1","cham1"));

        adapter = new log_MyAdapter(items, getActivity().getResources());
        recyclerView.setAdapter(adapter);


        return view;
    }
}