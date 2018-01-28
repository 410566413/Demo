package com.hawker.yangtianqi.demo;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hawker.yangtianqi.demo.model.Food;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodListFragment extends ListFragment {

    //添加片段监听器接口
    static interface  FoodListListener{
        void itemClicked(int id);
    }
    private FoodListListener foodListListener;

    public FoodListFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        String[] names = new String[Food.foods.length];
//        for(int i = 0;i<Food.foods.length;i++){
//            names[i]=Food.foods[i].getName();
//        }
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,names);
        //可以直接给适配器传入对象，前提是对象重写了toString方法
        ArrayAdapter<Food> arrayAdapter = new ArrayAdapter<Food>(inflater.getContext(),android.R.layout.simple_list_item_1,Food.foods);
        setListAdapter(arrayAdapter);
        //注意，这里是将数据调用父试图
        return  super.onCreateView(inflater,container,savedInstanceState);
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_food_list, container, false);
    }

    //重写方法，用于活动调用借口
    @Override
    public  void onAttach(Activity activity){
        super.onAttach(activity);
        this.foodListListener = (FoodListListener) activity;
    }

    //监听listview的单机事件
    @Override
    public void onListItemClick(ListView listView,View view,int position,long id){
        if (foodListListener != null){
            foodListListener.itemClicked((int)id);
        }
    }
}
