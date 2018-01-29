package com.hawker.yangtianqi.demo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class MessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        //动作条中启用向上按钮
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
