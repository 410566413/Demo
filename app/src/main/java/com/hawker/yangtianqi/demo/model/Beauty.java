package com.hawker.yangtianqi.demo.model;

import com.hawker.yangtianqi.demo.R;

/**
 * Created by yangtianqi on 2018/1/28.
 */
public class Beauty {
    private String name;
    private String description;
    private int imageResourceId;

    public static final Beauty[] beauties={
        new Beauty("眼镜","年轻女孩对比自己年龄大的男子\n“大叔”的男子长得帅气、大方\n比女孩大但不会大太多", R.drawable.head1),
        new Beauty("发卡","lady from japan...\n萝莉大多数身材娇小，年龄较幼\n精神思维可以比较成熟", R.drawable.head2),
        new Beauty("围巾","围巾是围在脖子上的长条形、三角形、方形等面料\n面料一般采用羊毛、棉、丝、莫代尔、人棉、腈纶、涤纶等\n通常于保暖，也可因美观、清洁或是宗教而穿戴。", R.drawable.head3)
    };

    private Beauty(String name, String description, int imageResourceId){
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
