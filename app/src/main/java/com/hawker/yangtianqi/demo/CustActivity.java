package com.hawker.yangtianqi.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yangtianqi on 2018/1/26.
 */
public class CustActivity extends Activity {
    private Button btnClose;
    private TextView textView;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_cust);



        btnClose=(Button)findViewById(R.id.btnClose);
        textView = (TextView)findViewById(R.id.textView);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result","hello from custActivity to mainActivir");
                setResult(0,intent);
                finish();
            }
        });

        textView.setText(getIntent().getStringExtra("msg"));
    }
}
