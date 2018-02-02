package com.hawker.yangtianqi.demo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hawker.yangtianqi.demo.R;

/**
 * Created by yangtianqi on 2018/1/31.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private static  final String DB_NAME="drink";
    private static  final int DB_VERSION=1;

    //调用超类构造函数初始化Sqllite
    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db,0,DB_VERSION);
    }

    private void updateDatabase(SQLiteDatabase db,int oldVersion,int newVersion) {
        //判断版本<1说明第一次创建数据库，
        if (oldVersion<1 && newVersion==1) {
            db.execSQL("create table drink (" +
                    "_id integer  primary key autoincrement," +
                    "name text," +
                    "description text," +
                    "image_resource_id integer," +
                    "favorite numberic)");

            insertDrink(db, "lily", "lily is a beauty", R.drawable.head2);
            insertDrink(db, "james", "james is a man", R.drawable.head1);
            insertDrink(db, "飘逸", "飘逸 的发型如图片", R.drawable.head3);
        }

        if(newVersion==2){
            db.execSQL("alter table drink add column favorite numberic;");
        }
    }

    public  static int insertDrink(SQLiteDatabase db,String name,String description,int ResourceId){
        //插入
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("description",description);
        contentValues.put("image_resource_id", ResourceId);
        return (int) db.insert("DRINK",null,contentValues);
    }


    public  static int updateDrink(SQLiteDatabase db,String name,String description,int ResourceId){
        //更新
        ContentValues updateValues = new ContentValues();
        updateValues.put("image_resource_id", R.drawable.head2);
        return db.update("DRINK",
                updateValues,
                "name=?",
                new String[]{"lily"}
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db,oldVersion,DB_VERSION);
    }
}
