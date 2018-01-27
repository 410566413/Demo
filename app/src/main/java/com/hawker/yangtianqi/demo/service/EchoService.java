package com.hawker.yangtianqi.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yangtianqi on 2018/1/26.
 */
public class EchoService extends Service {
    final static String TAG="EchoService";
    private final EchoServiceBinder echoServiceBinder=new EchoServiceBinder() ;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"@@@@@@@@@@@@@@onBind");
        return echoServiceBinder;
    }



    @Override
    public void onCreate(){
        Log.i(TAG,"%%%%%%%%%%%%%%%%%%%%%onCreate");
        startTimer();
        super.onCreate();
    }

    @Override
    public void onDestroy(){
        Log.i(TAG,"^^^^^^^^^^^^^^^^^^^^onDestroy");
        stopTimer();
        super.onDestroy();
    }

    private Timer timer=null;
    private TimerTask task = null;
    private static int i =0;

    public void startTimer(){
        if(timer==null){
            timer = new Timer();
            task=new TimerTask() {
                @Override
                public void run() {
                    i++;
                    Log.i(TAG,"++++::::"+i);
                }
            };
            timer.schedule(task,1000,1000);
        }
    }
    public void stopTimer(){
        if(timer!=null){
            task.cancel();
            timer.cancel();
            task =null;
            timer = null;
        }
    }

    public int getNum(){
        return i;
    }
}
