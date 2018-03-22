package com.hawker.yangtianqi.androidhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yangtianqi on 2018/3/21.
 * 工具类
 */
public class OkHttpUtil {
    private OkHttpClient client;
    //防止多线程访问不可见性
    private volatile static OkHttpUtil okHttpUtil;
    private final String TAG = OkHttpUtil.class.getSimpleName();
    private Handler handler;
    //提交json数据
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    //提交字符串
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/x-markdown;charset=utf-8");
    //初始化
    private OkHttpUtil(){
        client = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());

    }

    //使用单例模式获取对象
    public static  OkHttpUtil getInstance(){
        OkHttpUtil instance =null;
        if(okHttpUtil==null){
            synchronized (OkHttpUtil.class){
                if (instance==null){
                    instance = new OkHttpUtil();
                    okHttpUtil = instance;
                }
            }
        }
        return  instance;
    }

    /***
     * 同步请求数据,会阻塞UI线程
     * @param url
     * @return
     */
    public String syncGetByURl(String url){
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            //同步请求数据
            response = client.newCall(request).execute();
            if (response.isSuccessful()){
                return response.body().string();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 异步请求数据,返回结果为json字符串
     * @param url
     * @return
     */
    public void asyncGetByURl(String url,final CallBackString callBack){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response!=null && response.isSuccessful()){
                    onSuccessJsonStringMethod(response.body().string(),callBack);
                }
            }
        });
    }


    /***
     * 异步请求数据,返回结果为json对象
     * @param url
     * @return
     */
    public void asyncGetJSONObjectByURL(String url,final CallBackJSONObject callBack){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response!=null && response.isSuccessful()){
                    onSuccessJsonObjectMethod(response.body().string(),callBack);
                }
            }
        });
    }

    /***
     * 异步请求数据,返回结果为字节数组
     * @param url
     * @return
     */
    public void asyncGetObjectByURL(String url,final CallBackObject callBack){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response!=null && response.isSuccessful()){
                    onSuccessObjectMethod(response.body().bytes(),callBack);
                }
            }
        });
    }

    /***
     * 模拟表单发送数据
     * @param url
     * @return
     */
    public void sendComplexForm(String url, Map<String,String> params , final CallBackJSONObject callBack){
        //申明表单对象，包含以input开始的对象，以html表单为主
        FormBody.Builder form_builder = new FormBody.Builder();
        if (params !=null && !params.isEmpty()){
            for (Map.Entry<String,String> entry: params.entrySet()){
                form_builder.add(entry.getKey(),entry.getValue());
            }
        }
        RequestBody requestBody = form_builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response!=null && response.isSuccessful()){
                    onSuccessJsonObjectMethod(response.body().string(),callBack);
                }
            }
        });

    }

    /***
     * 异步请求数据,返回结果为图片对象
     * @param url
     * @return
     */
    public void asyncGetBitmapByURL(String url,final CallBackBitmap callBack){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response!=null && response.isSuccessful()){
                    byte[] data = response.body().bytes();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                    onSuccessBitmapMethod(bitmap,callBack);
                }
            }
        });
    }

    /***
     * 请求发挥的结果是json字符串
     * @param jsonValue
     * @param callBack
     */
    private void onSuccessJsonStringMethod(final String jsonValue, final CallBackString callBack){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null){
                    try {
                        callBack.onResponse(jsonValue);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /***
     * 请求返回结果是json对象
     * @param jsonValue
     * @param callBack
     */
    private void onSuccessJsonObjectMethod(final String jsonValue, final CallBackJSONObject callBack){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null){
                    try {
                        callBack.onResponse(new JSONObject(jsonValue));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /***
     * 请求返回结果是字节数组
     * @param data
     * @param callBack
     */
    private void onSuccessObjectMethod(final byte[] data, final CallBackObject callBack){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null){
                    try {
                        callBack.onResponse(data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    /***
     * 请求返回结果是bitmap
     * @param bitmap
     * @param callBack
     */
    private void onSuccessBitmapMethod(final Bitmap bitmap, final CallBackBitmap callBack){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null){
                    try {
                        callBack.onResponse(bitmap);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    interface CallBackString{
        void onResponse(String result);
    }

    interface CallBackJSONObject{
        void onResponse(JSONObject result);
    }

    interface CallBackObject{
        void onResponse(byte[] result);
    }

    interface CallBackBitmap{
        void onResponse(Bitmap result);
    }


}
