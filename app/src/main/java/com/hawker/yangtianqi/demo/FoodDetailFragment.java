package com.hawker.yangtianqi.demo;
import android.app.Fragment;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hawker.yangtianqi.demo.model.Food;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDetailFragment extends Fragment {
    private int foodId;

    public FoodDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState !=null){
            foodId = savedInstanceState.getInt("foodId");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_detail, container, false);
    }

    public void onStart(){
        super.onStart();
        View view = getView();
        if(view!=null){
            Food food = Food.foods[foodId];
            TextView name = (TextView) view.findViewById(R.id.textFoodName);
            name.setText(food.getName());
            TextView desc = (TextView) view.findViewById(R.id.textFoodDesc);
            desc.setText(food.getDescription());
            ImageView imageFood = (ImageView) view.findViewById(R.id.imageFoodId);
            imageFood.setImageResource(food.getImageResourceId());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        saveInstanceState.putInt("foodId",foodId);
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
