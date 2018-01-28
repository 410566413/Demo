package com.hawker.yangtianqi.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class StarTopActivity extends AppCompatActivity {
    private static final String TAG="StarTopActivity";
    private ListView listView;

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
    }
}
