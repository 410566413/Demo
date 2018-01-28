package com.hawker.yangtianqi.demo.model;

import com.hawker.yangtianqi.demo.R;

/**
 * Created by yangtianqi on 2018/1/28.
 */
public class Food {
    private String name;
    private String description;
    private int imageResourceId;

    public static final Food[] foods={
        new Food("uncle","年轻女孩对比自己年龄大的男子\n“大叔”的男子长得帅气、大方\n比女孩大但不会大太多", R.drawable.head1),
        new Food("lily","lady from japan...\n萝莉大多数身材娇小，年龄较幼\n精神思维可以比较成熟", R.drawable.head2),
        new Food("小鲜肉","小鲜肉最早是中国粉丝对男性明星的称\n“小鲜肉”指年轻、帅气的男性\n年龄在18-30岁之间\n性格纯良，感情经历单纯。", R.drawable.head3)
    };

    private Food(String name, String description, int imageResourceId){
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
