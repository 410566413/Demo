package com.hawker.yangtianqi.demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hawker.yangtianqi.demo.service.DelayMessageIntentService;
import com.hawker.yangtianqi.demo.service.EchoService;
import com.hawker.yangtianqi.demo.service.EchoServiceBinder;
import com.hawker.yangtianqi.demo.service.GpsService;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
    final static String TAG="ServiceActivity";


    private Button btnStartService;
    private Button btnStopService;
    private Button btnBind;
    private Button btnUnBind;
    private Button btnGetNum;
    private Button btnStartCallbackService;
    private Button btnStartGpsService;


    private EchoService echoService=null;
    private Intent serviceiIntent;
    private GpsService gpsService;
    private boolean isGpsBounded= false;//是否已经绑定gps服务

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            //连接时得到GpsService的引用
            GpsService.GpsBinder gpsbinder = (GpsService.GpsBinder) binder;
            gpsService = gpsbinder.getGps();
            isGpsBounded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isGpsBounded = false;
        }
    };

    @Override
    public void onStart(){
        //启动就绑定GpS服务
        super.onStart();
        Intent intent = new Intent(this,GpsService.class);
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop(){
        //解除服务绑定
        super.onStop();
        if (isGpsBounded){
            unbindService(serviceConnection);
            isGpsBounded = false;
        }
    }

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

        btnStartGpsService=(Button)findViewById(R.id.btnStartGpsService);
        btnStartGpsService.setOnClickListener(this);

    }

    private void watchMileage() {
        final TextView distanceView = (TextView) findViewById(R.id.textDistance);
        final Handler handle= new Handler();
        handle.post(new Runnable() {
            @Override
            public void run() {
                double dis= 0.0;
                if (gpsService !=null){
                    dis = gpsService.getMiles();
                }
                distanceView.setText(String.format("%1$.2f",dis));
                handle.postDelayed(this,1000);
            }
        });
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
            case R.id.btnStartGpsService:
                watchMileage();
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
