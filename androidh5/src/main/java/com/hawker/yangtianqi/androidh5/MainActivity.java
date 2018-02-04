package com.hawker.yangtianqi.androidh5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnJavaAndH5;
    private Button btnJsCallJava;
    private Button btnJsCallPhone;


    private void findViews() {
        //加载试图
        setContentView(R.layout.activity_main);
       // 初始化按钮
        btnJavaAndH5 = (Button)findViewById( R.id.btn_java_and_h5 );
        btnJsCallJava = (Button)findViewById( R.id.btn_js_call_java );
        btnJsCallPhone = (Button)findViewById( R.id.btn_js_call_phone );
        //设置点击事件
        btnJavaAndH5.setOnClickListener( this );
        btnJsCallJava.setOnClickListener( this );
        btnJsCallPhone.setOnClickListener( this );
    }


    @Override
    public void onClick(View v) {
        if ( v == btnJavaAndH5 ) {
            // Handle clicks for btnJavaAndH5
            Toast.makeText(MainActivity.this,"互相调用",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,JavaH5Activity.class);
            startActivity(intent);
        } else if ( v == btnJsCallJava ) {
            // Handle clicks for btnJsCallJava
            Toast.makeText(MainActivity.this,"h5 调android播放器",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,JsCallAndroidVideoActivity.class);
            startActivity(intent);
        } else if ( v == btnJsCallPhone ) {
            // Handle clicks for btnJsCallPhone
            Toast.makeText(MainActivity.this,"h5 调android电话",Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();

    }
}
