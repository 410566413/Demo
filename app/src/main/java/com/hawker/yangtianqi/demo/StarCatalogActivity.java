package com.hawker.yangtianqi.demo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hawker.yangtianqi.demo.model.Drink;

public class StarCatalogActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        ArrayAdapter<Drink> arrayAdapter = new ArrayAdapter<Drink>(this,android.R.layout.simple_list_item_1,Drink.drinks);
        listView.setAdapter(arrayAdapter);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(StarCatalogActivity.this,StarDrinkActivity.class);
        intent.putExtra(StarDrinkActivity.EXTRA_DRINKNO,(int)id);
        startActivity(intent);
    }
}
