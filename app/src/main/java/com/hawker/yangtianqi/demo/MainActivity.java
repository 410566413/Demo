package com.hawker.yangtianqi.demo;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hawker.yangtianqi.demo.service.BroadcastService;
import com.hawker.yangtianqi.demo.service.EchoService;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private  final static String TAG="MainActivity";
    private Button btnCust;
    private Button btnService;
    private Button btnBraod;
    private Button btnReadContact;
    private Button btnStartIntent;
    private Button btnLayoutIntent;
    private Button btnComponentIntent;
    private Button btnOtherCompIntent;

    //分享按钮
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCust=(Button)findViewById(R.id.btnCust);
        btnCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,CustActivity.class);
                intent.putExtra("msg","hello from Main to CustActivity");
                //startActivity(intent);
                startActivityForResult(intent,0);
            }
        });

        btnService=(Button)findViewById(R.id.btnService);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,ServiceActivity.class);
                startActivity(intent);
            }
        });

        btnBraod=(Button)findViewById(R.id.btnBraod);

        btnBraod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,BroadcastActivity.class);
                startActivity(intent);
            }
        });

        btnReadContact=(Button)findViewById(R.id.btnReadContact);
//        btnReadContact.setOnClickListener(this);

        btnStartIntent=(Button)findViewById(R.id.btnStartIntent);
        btnStartIntent.setOnClickListener(this);

        btnLayoutIntent=(Button)findViewById(R.id.btnLayoutIntent);
        btnLayoutIntent.setOnClickListener(this);

        btnComponentIntent=(Button)findViewById(R.id.btnComponentIntent);
        btnComponentIntent.setOnClickListener(this);

        btnOtherCompIntent=(Button)findViewById(R.id.btnOtherCompIntent);
        btnOtherCompIntent.setOnClickListener(this);

//        btnOtherCompIntent=(Button)findViewById(R.id.btnOtherCompIntent);
//        btnOtherCompIntent.setOnClickListener(this);
//        btnOtherCompIntent=(Button)findViewById(R.id.btnOtherCompIntent);
//        btnOtherCompIntent.setOnClickListener(this);
    }

    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent intent){
        String  result=intent.getStringExtra("result");
        Log.i("MainActivity","@@@@@@@@@@@@@@@@@@"+result);
        super.onActivityResult(requestCode,resultCode,intent);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnReadContact:
                readContact();
                break;
            case R.id.btnStartIntent:
                startIntent();
                break;
            case R.id.btnLayoutIntent:
                startLayout();
                break;
            case R.id.btnComponentIntent:
                startComponent();
                break;
            case R.id.btnOtherCompIntent:
                startOtherComponent();
                break;
            default:
                break;
        }
    }

    private void readContact(){
        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        while (c.moveToNext()){
            String displayName=c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.i(TAG,">>>>>>>>>>>>>>>>>>>>"+displayName);
        }
    }

    private void startIntent(){
        Log.i(TAG,">>>>>>>>>>>>>>>>>>>>tartIntent");
        Intent intent= new Intent(MainActivity.this,IntentActivity.class);
        startActivity(intent);
    }

    private void startLayout(){
        Log.i(TAG,">>>>>>>>>>>>>>>>>>>>startLayout");
        Intent intent= new Intent(MainActivity.this,LayoutActivity.class);
        startActivity(intent);
    }

    private void startComponent(){
        Log.i(TAG,">>>>>>>>>>>>>>>>>>>>startComponent");
        Intent intent= new Intent(MainActivity.this,ComponentActivity.class);
        startActivity(intent);
    }
    public void startOtherComponent(){
        Intent intent= new Intent(MainActivity.this,OtherCompActivity.class);
        startActivity(intent);
    }

    public void btnAdapterAppClick(View view){
        Intent intent= new Intent(MainActivity.this,StarTopActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载menu_main里的菜单数据
        getMenuInflater().inflate(R.menu.menu_main,menu);

        MenuItem menuItem = menu.findItem(R.id.menu_share);
        // 注意，这里需要加在初始化菜单数据之后。
        shareActionProvider=(ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        shareIntent("sdfs");
        return  true;
//        return super.onCreateOptionsMenu(menu);

//        //动态创建菜单，四个参数的含义。1，group的id,2,item的id,3,是否排序，4，将要显示的内容
//        menu.add(0,1,0,"菜单一");
//        menu.add(0,2,0,"菜单二");
//        menu.add(0,3,0,"菜单三");
//        menu.add(0,4,0,"菜单四");
//        return true;
    }

    private void shareIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(MainActivity.this,"设置",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_create_message:
                Toast.makeText(MainActivity.this,"消息",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MessageActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private long lastClickTime=0;

    @Override
    public  void onBackPressed(){
//        super.onBackPressed();
        if (lastClickTime<=0){
            Toast.makeText(this,"再按一次后退键",Toast.LENGTH_SHORT).show();
            lastClickTime = System.currentTimeMillis();
        }else {
            long currentClickTime= System.currentTimeMillis();
            if (currentClickTime-lastClickTime<2000){
                finish();
            }else {
                Toast.makeText(this,"再按一次后退键",Toast.LENGTH_SHORT).show();
                lastClickTime = currentClickTime;
            }
        }
    }

}
