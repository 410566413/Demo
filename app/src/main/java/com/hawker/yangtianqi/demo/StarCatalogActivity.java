package com.hawker.yangtianqi.demo;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.hawker.yangtianqi.demo.dao.DatabaseHelper;
import com.hawker.yangtianqi.demo.model.Drink;

public class StarCatalogActivity extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        //替换数组适配器为数据库适配器
        //ArrayAdapter<Drink> arrayAdapter = new ArrayAdapter<Drink>(this,android.R.layout.simple_list_item_1,Drink.drinks);
        //listView.setAdapter(arrayAdapter);
        try {
            SQLiteOpenHelper helper = new DatabaseHelper(this);
            db = helper.getWritableDatabase();
            cursor = db.query("drink",
                    new String[]{"_id","name"},
                    null,null,null,null,null);
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_expandable_list_item_1,
                    cursor,
                    new String[]{"name"},
                    new int[]{android.R.id.text1});

            listView.setAdapter(cursorAdapter);
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this,"database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(StarCatalogActivity.this,StarDrinkActivity.class);
        intent.putExtra(StarDrinkActivity.EXTRA_DRINKNO,(int)id);
        startActivity(intent);
    }
}
