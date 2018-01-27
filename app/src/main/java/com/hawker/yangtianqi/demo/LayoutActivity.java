package com.hawker.yangtianqi.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class LayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private LinearLayout linearLayout;
    private LinearLayout linearLayoutVertial;
    private LinearLayout linearLayoutHorizontal;
    private View.OnClickListener buttonClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            linearLayout = (LinearLayout)v.getParent();
            linearLayout.removeView(v);
        }
    };
    private Button btnShowLinearVetaical;
    private Button btnShowLinearHorizontal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);


        btnShowLinearHorizontal = (Button) findViewById(R.id.btnShowLinearHorizontal);
        btnShowLinearHorizontal.setOnClickListener(this);

        btnShowLinearVetaical = (Button) findViewById(R.id.btnShowLinearVetaical);
        btnShowLinearVetaical.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShowLinearHorizontal:
                showLinearHorizontal();
                break;
            case R.id.btnShowLinearVetaical:
                showLinearVertical();
                break;
            default:
                break;
        }
    }

    public void showLinearHorizontal(){
        setContentView(R.layout.layout_linear_horizontal);
        linearLayoutHorizontal = (LinearLayout) findViewById(R.id.layoutLinearHorzontal);
        for (int i=1;i<8;i++){
            button = new Button(this);
            button.setText("点击移除我：：："+i);
//            按钮大小布满一行
            linearLayoutHorizontal.addView(button);
            // 按钮大小根据文字大小
//            linearLayoutHorizontal.addView(button,-2,-2);

            button.setOnClickListener(buttonClickHandler);
        }
    }

    public void showLinearVertical(){
        setContentView(R.layout.layout_linear_vertical);
        linearLayoutVertial = (LinearLayout) findViewById(R.id.layoutLinearVertical);
        for (int i=1;i<8;i++){
            button = new Button(this);
            button.setText("点击移除我：：："+i);
//            按钮大小布满一行
//            linearLayoutVertial.addView(button);
            // 按钮大小根据文字大小
            linearLayoutVertial.addView(button,-2,-2);

            button.setOnClickListener(buttonClickHandler);
        }
    }


}
