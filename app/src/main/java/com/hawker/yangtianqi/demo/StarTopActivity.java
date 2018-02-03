package com.hawker.yangtianqi.demo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.hawker.yangtianqi.demo.dao.DatabaseHelper;

public class StarTopActivity extends AppCompatActivity {
    private static final String TAG="StarTopActivity";
    private ListView listView;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_top);

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                if (position==0){
                    Intent intent = new Intent(StarTopActivity.this,StarCatalogActivity.class);
                    startActivity(intent);
                }else  if (position==1){
                    Intent intent = new Intent(StarTopActivity.this,FoodCatalogActivity.class);
                    startActivity(intent);
                }

            }
        };
        listView = (ListView) findViewById(R.id.listMenus);
        listView.setOnItemClickListener(onItemClickListener);

        ListView favoritelv= (ListView) findViewById(R.id.favoriteList);
        try {
            SQLiteOpenHelper helper = new DatabaseHelper(this);

            db = helper.getWritableDatabase();
//            db.needUpgrade(2);
            cursor = db.query("drink",
                    new String[]{"_id","name"},
                    "favorite=1",
                   null,null,null,null);
            //遍历游标
            CursorAdapter favoriteAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"name"},
                    new int[]{android.R.id.text1},
                    0
                    );
            favoritelv.setAdapter(favoriteAdapter);
//            cursor.close();
//            db.close();
        }catch (SQLException e){
            Toast toast = Toast.makeText(this,"database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public  void onRestart(){
        super.onRestart();
        SQLiteOpenHelper helper = new DatabaseHelper(this);

        db = helper.getWritableDatabase();
        Cursor newCursor = db.query("drink",
                new String[]{"_id","name"},
                "favorite =1 ",
                null,null,null,null);

        ListView listViewRestart = (ListView) findViewById(R.id.favoriteList);
        CursorAdapter adapterChange  = (CursorAdapter) listViewRestart.getAdapter();
        adapterChange.changeCursor(newCursor);
        listViewRestart.setAdapter(adapterChange);

    }
}
