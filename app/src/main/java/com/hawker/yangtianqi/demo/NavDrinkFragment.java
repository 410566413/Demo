package com.hawker.yangtianqi.demo;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavDrinkFragment extends ListFragment {


    public NavDrinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.option_drink)
        );

        setListAdapter(arrayAdapter);
        return super.onCreateView(inflater,container,savedInstanceState);
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_nav_drink, container, false);
    }

}
