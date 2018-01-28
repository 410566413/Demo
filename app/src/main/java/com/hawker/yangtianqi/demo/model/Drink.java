package com.hawker.yangtianqi.demo.model;

import com.hawker.yangtianqi.demo.R;

/**
 * Created by yangtianqi on 2018/1/28.
 */
public class Drink {
    private String name;
    private String description;
    private int imageResourceId;

    public static final Drink[] drinks={
        new Drink("caffe","new type from english...", R.drawable.head1),
        new Drink("jupeter","lady from japan...", R.drawable.head2),
        new Drink("orange","make from orange...", R.drawable.head3)
    };

    private Drink(String name,String description,int imageResourceId){
        this.name= name;
        this.description=description;
        this.imageResourceId= imageResourceId;
    }

    public String toString(){
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
