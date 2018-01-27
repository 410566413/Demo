package com.hawker.yangtianqi.demo.service;

import android.os.Binder;

/**
 * Created by yangtianqi on 2018/1/26.
 */
public class EchoServiceBinder extends Binder {
    private EchoService echoService=null;
    public EchoService getService(){
        if(echoService==null){
            echoService = new EchoService();
        }
        return echoService;
    }


}
