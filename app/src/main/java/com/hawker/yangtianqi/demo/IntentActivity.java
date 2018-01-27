package com.hawker.yangtianqi.demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class IntentActivity extends AppCompatActivity {

    private Button btnImageIntent;
    private Button btnImageNormal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        btnImageNormal = (Button) findViewById(R.id.btnImageNormal);
        btnImageNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivity.this,CustActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnImageIntent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File("/mnt/sdcard/1.jpg");
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.fromFile(f),"image/*");
                startActivity(i);
            }
        });

        findViewById(R.id.btnPhoneIntent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("tel:10010"));
                startActivity(i);
            }
        });

        findViewById(R.id.btnWebIntent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.baidu.com"));
                startActivity(i);
            }
        });
    }
}
