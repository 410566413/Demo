package com.hawker.yangtianqi.demo;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

//        btnComponentIntent=(Button)findViewById(R.id.btnComponentIntent);
//        btnComponentIntent.setOnClickListener(this);
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
}
