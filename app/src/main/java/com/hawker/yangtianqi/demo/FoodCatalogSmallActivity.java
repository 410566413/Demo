package com.hawker.yangtianqi.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FoodCatalogSmallActivity extends AppCompatActivity {
    public   static final String EXTRA_FOODID="foodId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_catalog_small);

        FoodDetailFragment foodDetailFragment = (FoodDetailFragment) getFragmentManager().findFragmentById(R.id.foodCatalogSmall);
        int foodId = (int) getIntent().getExtras().get(EXTRA_FOODID);
        foodDetailFragment.setFoodId(foodId);

    }
}
