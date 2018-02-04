package com.hawker.yangtianqi.demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hawker.yangtianqi.demo.service.DelayMessageIntentService;
import com.hawker.yangtianqi.demo.service.EchoService;
import com.hawker.yangtianqi.demo.service.EchoServiceBinder;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
    final static String TAG="ServiceActivity";


    private Button btnStartService;
    private Button btnStopService;
    private Button btnBind;
    private Button btnUnBind;
    private Button btnGetNum;
    private Button btnStartCallbackService;


    private EchoService echoService=null;
    private Intent serviceiIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        serviceiIntent= new Intent(this, EchoService.class);
        btnStartService=(Button)findViewById(R.id.btnStartService);
        btnStartService.setOnClickListener(this);
        btnStopService=(Button)findViewById(R.id.btnStopService);
        btnStopService.setOnClickListener(this);

        btnBind=(Button)findViewById(R.id.btnBind);
        btnBind.setOnClickListener(this);
        btnUnBind=(Button)findViewById(R.id.btnUnBind);
        btnUnBind.setOnClickListener(this);

        btnGetNum=(Button)findViewById(R.id.btnGetNum);
        btnGetNum.setOnClickListener(this);

        btnStartCallbackService=(Button)findViewById(R.id.btnStartCallbackService);
        btnStartCallbackService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartService:
                startService(serviceiIntent);
                break;
            case R.id.btnStopService:
                stopService(serviceiIntent);
                break;
            case R.id.btnBind:
                bindService(serviceiIntent,this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnBind:
                unbindService(this);
                echoService=null;
                break;
            case R.id.btnStartCallbackService:
                callbackService();
                break;
            case R.id.btnGetNum:
                if(echoService!=null){
                    Log.i(TAG,"当前服务数据为:::"+echoService.getNum());
                }
                break;
            default:
                break;

        }
    }

    private void callbackService() {
        Intent intent = new Intent(this,DelayMessageIntentService.class);
        intent.putExtra(DelayMessageIntentService.EXTRA_MESSAGE,getResources().getString(R.string.drawer_open));
        startService(intent);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        Log.i(TAG,"$$$$$$$$$$$$$$$$$$onServiceConnected");
        echoService = ((EchoServiceBinder)binder).getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.i(TAG,"************************onServiceDisconnected");
    }
}
