package com.hawker.yangtianqi.demo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hawker.yangtianqi.demo.dao.DatabaseHelper;
import com.hawker.yangtianqi.demo.model.Drink;

public class StarDrinkActivity extends AppCompatActivity {

    static  String EXTRA_DRINKNO="drinkNo" ;
    private ImageView imageView;
    private TextView textDrinkName;
    private TextView textDrinkDesc;
    private SQLiteDatabase db;
    private  Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_drink);

        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_DRINKNO,0);
        //将类数据换成数据库数据
        //Drink drink =  Drink.drinks[id];
//        imageView = (ImageView) findViewById(R.id.imageDrink);
//        imageView.setImageResource(drink.getImageResourceId());
//
//        textDrinkName= (TextView) findViewById(R.id.textDrinkName);
//        textDrinkName.setText(String.valueOf(drink.getName()));
//
//        textDrinkDesc= (TextView) findViewById(R.id.textDrinkDesc);
//        textDrinkDesc.setText(String.valueOf(drink.getDescription()));

        try {
            SQLiteOpenHelper helper = new DatabaseHelper(this);
            db = helper.getWritableDatabase();

            cursor = db.query("drink",
                    new String[]{"name","description","image_resource_id","favorite"},
                    "_id =?",
                    new String[]{Integer.toString(id)}
                    ,null,null,null);
            //遍历游标
            if(cursor.moveToNext()){
                String nameTxt = cursor.getString(0);
                String descTxt = cursor.getString(1);
                int resId = cursor.getInt(2);
                Boolean isFavorite = (cursor.getInt(3)==1);

                imageView = (ImageView) findViewById(R.id.imageDrink);
                imageView.setImageResource(resId);

                textDrinkName= (TextView) findViewById(R.id.textDrinkName);
                textDrinkName.setText(String.valueOf(nameTxt));

                textDrinkDesc= (TextView) findViewById(R.id.textDrinkDesc);
                textDrinkDesc.setText(String.valueOf(descTxt));

                CheckBox checkBox = (CheckBox) findViewById(R.id.CbxFavorite);
                checkBox.setChecked(isFavorite);

            }
            cursor.close();
            db.close();
        }catch (SQLException e){
            Toast toast = Toast.makeText(this,"database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClickCbxFavorite(View view){
        int id = getIntent().getIntExtra(EXTRA_DRINKNO,0);
        CheckBox favorite= (CheckBox) findViewById(R.id.CbxFavorite);
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite",favorite.isChecked());
        SQLiteOpenHelper helper = new DatabaseHelper(this);
        try {
            SQLiteOpenHelper sqLiteOpenHelper = new DatabaseHelper(this);
            db = sqLiteOpenHelper.getWritableDatabase();
            db.update("drink",contentValues,"_id=?",new String[]{Integer.toString(id)});
            db.close();
        }catch (SQLException e){
            Toast toast = Toast.makeText(this,"database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
