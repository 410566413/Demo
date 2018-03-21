package com.hawker.yangtianqi.androidhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;
    private final  static int SUCCESS_STATUS=1;
    private final  static int FAIL_STATUS=0;
    private final  static String TAG=MainActivity.class.getSimpleName();
    private String image_path = "http://www.baidu.com/img/bd_logo1.png";

    private OkHttpClient client;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            super.handleMessage(message);
            switch (message.what){
                case SUCCESS_STATUS:
                    byte[] result = (byte[]) message.obj;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(result,0,result.length);
                    Bitmap bitmap_new = new PicCrop().transform(bitmap);
                    imageView.setImageBitmap(bitmap_new);
                    break;
                case  FAIL_STATUS:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btnDownPic);
        imageView = (ImageView) findViewById(R.id.imageView);
        client = new OkHttpClient();
        // 使用get请求
        final Request request = new Request.Builder().get().url(image_path).build();

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,"网络请求失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = handler.obtainMessage();
                        if (response.isSuccessful()){
                            message.what=SUCCESS_STATUS;
                            message.obj = response.body().bytes();
                            handler.sendMessage(message);
                        }else {
                            handler.sendEmptyMessage(FAIL_STATUS);
                        }
                        Toast.makeText(MainActivity.this,"图片下载成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
