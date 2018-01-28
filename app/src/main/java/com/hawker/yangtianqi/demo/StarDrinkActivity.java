package com.hawker.yangtianqi.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hawker.yangtianqi.demo.model.Drink;

public class StarDrinkActivity extends AppCompatActivity {

    static  String EXTRA_DRINKNO="drinkNo" ;
    private ImageView imageView;
    private TextView textDrinkName;
    private TextView textDrinkDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_drink);

        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_DRINKNO,0);

       Drink drink =  Drink.drinks[id];

        imageView = (ImageView) findViewById(R.id.imageDrink);
        imageView.setImageResource(drink.getImageResourceId());

        textDrinkName= (TextView) findViewById(R.id.textDrinkName);
        textDrinkName.setText(String.valueOf(drink.getName()));

        textDrinkDesc= (TextView) findViewById(R.id.textDrinkDesc);
        textDrinkDesc.setText(String.valueOf(drink.getDescription()));






    }
}
