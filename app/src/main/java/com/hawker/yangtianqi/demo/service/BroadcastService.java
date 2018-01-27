package com.hawker.yangtianqi.demo.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by yangtianqi on 2018/1/26.
 */
public class BroadcastService extends BroadcastReceiver {
    private final  static String TAG="BroadcastService";
    public final  static String ACTION="com.hawker.yangtianqi.demo.service.intent.action.BroadcastService";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"%%%%%%%%%%%%%%%%onReceive:::"+intent.getStringExtra("msg"));
    }
}
