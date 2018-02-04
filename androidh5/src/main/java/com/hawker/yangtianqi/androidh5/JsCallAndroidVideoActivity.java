package com.hawker.yangtianqi.androidh5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class JsCallAndroidVideoActivity extends Activity {
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_android_video);
        webView= (WebView) findViewById(R.id.js_webview);

        //加载webview并且配置项目
        WebSettings webSettings=webView.getSettings();
        //设置启用js
        webSettings.setJavaScriptEnabled(true);
        //调用内嵌客户端模式
        webView.setWebViewClient(new WebViewClient());
        // 暴露被js调用的方法
        webView.addJavascriptInterface(new AndroidCallJavascript(),"android");
        //调用浏览器访问--本地h5页面
        webView.loadUrl("file:///android_asset/RealNetJSCallJavaActivity.htm");

    }

    class AndroidCallJavascript{
        @JavascriptInterface
        public void playVideo(int itemid,String videourl,String itemtitle){
            Toast.makeText(JsCallAndroidVideoActivity.this,"来自h5页面的调用！！"+videourl,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setDataAndType(Uri.parse(videourl),"video/*");
            startActivity(intent);
        }
    }
}
