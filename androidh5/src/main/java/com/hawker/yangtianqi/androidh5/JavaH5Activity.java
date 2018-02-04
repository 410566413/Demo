package com.hawker.yangtianqi.androidh5;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JavaH5Activity extends Activity implements View.OnClickListener {
    private EditText etName;
    private EditText etPwd;
    private Button btnSubmitJavaH5;
    private WebView webView;

    private void findViews() {
        setContentView(R.layout.activity_java_h5);
        etName = (EditText)findViewById( R.id.et_name );
        etPwd = (EditText)findViewById( R.id.et_pwd );
        btnSubmitJavaH5 = (Button)findViewById( R.id.btn_submitJavaH5 );

        btnSubmitJavaH5.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == btnSubmitJavaH5 ) {
            // Handle clicks for btnSubmitJavaH5
            login();
        }
    }

    private void login() {
        String name = etName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
            Toast.makeText(JavaH5Activity.this,"账号密码不能为空",Toast.LENGTH_SHORT).show();
        }else {

        }
        loadPage(name);
    }

    private void loadPage(String name) {
        webView.loadUrl("javascript:javaCallJs('"+name+"')");
        setContentView(webView);
    }

    class AndroidCallJavascript{
        @JavascriptInterface
        public void showToast(){
            Toast.makeText(JavaH5Activity.this,"来自h5页面的调用！！",Toast.LENGTH_SHORT).show();
        }
    }

    private void initWebview() {
        //加载webview并且配置项目
        webView= new WebView(this);
        WebSettings webSettings=webView.getSettings();
        //设置启用js
        webSettings.setJavaScriptEnabled(true);


        //调用内嵌客户端模式
        webView.setWebViewClient(new WebViewClient());

        //调用浏览器访问外部网址
        //webView.loadUrl("http://www.qq.com");

        //调用浏览器访问--本地h5页面
        webView.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");

       // 暴露被js调用的方法
        webView.addJavascriptInterface(new AndroidCallJavascript(),"Android");
//        setContentView(webView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载页面控件
        findViews();
        //初始化webview
        initWebview();
    }

}
