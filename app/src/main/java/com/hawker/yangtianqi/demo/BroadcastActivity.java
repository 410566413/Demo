package com.hawker.yangtianqi.demo;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hawker.yangtianqi.demo.service.BroadcastService;

public class BroadcastActivity extends AppCompatActivity {
    private Button btnBroadcast;
    private Button btnBroadReg;
    private Button btnUnBroadReg;
    private final BroadcastService broadcastService = new BroadcastService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        btnBroadcast=(Button)findViewById(R.id.btnBroadcast);
        btnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(BroadcastActivity.this, BroadcastService.class);
                Intent intent= new Intent(BroadcastService.ACTION);
                intent.putExtra("msg","hello from broadcast..");
                sendBroadcast(intent);
            }
        });

        btnBroadReg=(Button)findViewById(R.id.btnBroadReg);
        btnBroadReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReceiver(broadcastService,new IntentFilter(BroadcastService.ACTION));
            }
        });

        btnUnBroadReg=(Button)findViewById(R.id.btnUnBroadReg);
        btnUnBroadReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterReceiver(broadcastService);
            }
        });

    }

}
