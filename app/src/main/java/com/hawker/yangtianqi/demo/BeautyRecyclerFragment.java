package com.hawker.yangtianqi.demo;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hawker.yangtianqi.demo.adapter.BeautyAdapter;
import com.hawker.yangtianqi.demo.model.Beauty;


/**
 * A simple {@link Fragment} subclass.
 */
public class BeautyRecyclerFragment extends Fragment {


    public BeautyRecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_beauty_recycler,container,false);
        String[] beautyNames=new String[Beauty.beauties.length];
        int[] beautyImages=new int[Beauty.beauties.length];
        for (int i=0;i<Beauty.beauties.length;i++){
            beautyNames[i]=Beauty.beauties[i].getName();
            beautyImages[i]=Beauty.beauties[i].getImageResourceId();
        }

        BeautyAdapter beautyAdapter = new BeautyAdapter(beautyNames,beautyImages);
        recyclerView.setAdapter(beautyAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        return recyclerView;

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_beauty_recycler, container, false);
    }

}
