package com.hawker.yangtianqi.demo.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.hawker.yangtianqi.demo.R;
import com.hawker.yangtianqi.demo.ServiceActivity;

public class DelayMessageIntentService extends IntentService {
    public static final String EXTRA_MESSAGE = "message";
    private Handler handler;
    public static  final int NOtIFICATION_ID=5445;

    public DelayMessageIntentService() {
        super("DelayMessageIntentService");
    }

    @Override
    public  int onStartCommand(Intent intent,int flags,int startId){
        handler = new Handler();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            synchronized (this){
                try {
                    wait(10000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            String text = intent.getStringExtra(EXTRA_MESSAGE);
            showText(text);

        }
    }

    private void showText(final String text) {
        Log.i("IntentService","====================================this is a msg:::"+ text);
        handler.post(new Runnable() {
            @Override
            public void run() {
                //用Toast进行回调界面通知
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();

                //用Notification进行回调界面通知
                Intent intent = new Intent(DelayMessageIntentService.this, ServiceActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(DelayMessageIntentService.this);
                stackBuilder.addParentStack(ServiceActivity.class);
                stackBuilder.addNextIntent(intent);
                PendingIntent  pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                Notification notification = new Notification.Builder(DelayMessageIntentService.this)
                        .setSmallIcon(R.drawable.head1)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(text)
                        .setAutoCancel(true)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setContentIntent(pendingIntent)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(NOtIFICATION_ID,notification);
            }
        });
    }


}
