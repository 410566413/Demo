package com.hawker.yangtianqi.demo;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hawker.yangtianqi.demo.model.Food;

public class FoodCatalogActivity extends AppCompatActivity implements FoodListFragment.FoodListListener {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_catalog);

//        ArrayAdapter<Food> arrayAdapter = new ArrayAdapter<Food>(this,android.R.layout.simple_list_item_1,Food.foods);
//        listView = (ListView) findViewById(R.id.foodListView);
////        listView.setAdapter(arrayAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                FoodDetailFragment foodDetailFragment = (FoodDetailFragment) getFragmentManager().findFragmentById(R.id.foodDetailId);
//                foodDetailFragment.setFoodId(position);
//            }
//        });
    }

    @Override
    public void itemClicked(int id) {
        //
        View fragmentContainer = findViewById(R.id.frameContainer);
        if(fragmentContainer !=null){
            FoodDetailFragment foodDetailFragment = new FoodDetailFragment();
            //启动片段事物
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            foodDetailFragment.setFoodId(id);
            //替换片段
            fragmentTransaction.replace(R.id.frameContainer,foodDetailFragment);
            fragmentTransaction.addToBackStack(null);
            //设置新片段和老片段的过度效果
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
        }else {
            Intent intent = new Intent(this,FoodCatalogSmallActivity.class);
            intent.putExtra(FoodCatalogSmallActivity.EXTRA_FOODID,id);
            startActivity(intent);
        }

    }
}
