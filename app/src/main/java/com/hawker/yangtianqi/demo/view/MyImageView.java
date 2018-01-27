package com.hawker.yangtianqi.demo.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by yangtianqi on 2018/1/27.
 */
public class MyImageView extends Activity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv = new ImageView(this);
        setContentView(iv);
    }
}
