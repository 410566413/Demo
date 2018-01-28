package com.hawker.yangtianqi.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class OtherCompActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private final static String TAG="OtherCompActivity";
    private Button btnSubmit;
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter<String>  arrayAdapter;
    private ToggleButton toggleButton;
    private  Spinner spinner;
    private ArrayAdapter<String> adapter;//创建一个数组适配器
    private List<String> list = new ArrayList<String>();//创建一个String类型的数组列表。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_comp);


        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        //初始化自动补全的数据
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);
        arrayAdapter.add("hello");
        arrayAdapter.add("hello to meet you");
        arrayAdapter.add("hello ,nice to meet you");
        arrayAdapter.add("glad to meet.");
        arrayAdapter.add("你好，很 高兴 见到你.");
        arrayAdapter.add("你好，很高兴见到你.");
        arrayAdapter.add("你好吗，希望再次见到你");
        arrayAdapter.add("你好吗，希望 再次 见到你");
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);

        //通过findViewById()来找到我们需要的RadioGroup
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //设置状态改变的事件
        radioGroup.setOnCheckedChangeListener(this);

        //初始化togglebutton
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        //设置监听器
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                    Toast.makeText(OtherCompActivity.this, "打开文本编辑", Toast.LENGTH_LONG).show();
                    findViewById(R.id.editText).setEnabled(true);
                }else{
                    Toast.makeText(OtherCompActivity.this, "关闭文本编辑", Toast.LENGTH_LONG).show();
                    findViewById(R.id.editText).setEnabled(false);
                }
            }
        });

        //通过findViewById()来找到我们需要的RadioGroup
        spinner = (Spinner) findViewById(R.id.spinner);
        // 添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项，即数据源
        list.add("男士");
        list.add("女士");
        //1.为下拉列表定义一个数组适配器，这个数组适配器就用到里前面定义的list。装的都是list所添加的内容
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);//样式为原安卓里面有的android.R.layout.simple_spinner_item，让这个数组适配器装list内容。
        //2.为适配器设置下拉菜单样式。adapter.setDropDownViewResource
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //3.以上声明完毕后，建立适配器,有关于sipnner这个控件的建立。用到myspinner
        spinner.setAdapter(adapter);
        //4.为下拉列表设置各种点击事件，以响应菜单中的文本item被选中了，用setOnItemSelectedListener
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                // 将所选mySpinner 的值带入myTextView 中
//                myTextView.setText("您选择的是：" + adapter.getItem(arg2));//文本说明
                String msg = adapter.getItem(arg2);
                Toast.makeText(OtherCompActivity.this, "选择了："+msg, Toast.LENGTH_LONG).show();
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                if(msg.equals("男士")){
                    imageView.setImageResource(R.drawable.head1);
                }else{
                    imageView.setImageResource(R.drawable.head2);
                }
                imageView.setContentDescription(msg);

            }

            public void onNothingSelected(AdapterView<?> arg0) {
//                myTextView.setText("Nothing");
                Toast.makeText(OtherCompActivity.this, "选择了：Nothing", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                getRaioValue();
                break;
//            case R.id.btnAddPerson:
//                addPerson();
//                break;
//            case R.id.btnAddPic:
//                addPic3();
//                break;
            default:
                break;
        }
    }


    public void  getRaioValue(){

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //根据不同ID 弹出不同的吐司
        switch (group.getCheckedRadioButtonId()){
            case  R.id.radioButton:
                Toast.makeText(this, "你点击了“喜欢”按钮", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.radioButton2:
                Toast.makeText(this, "你点击了“讨厌”按钮", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.radioButton3:
                Toast.makeText(this, "你点击了“一般般”按钮", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onSwitchClick(View view){
        boolean isChecked =((Switch)view).isChecked();
        if(isChecked){
            Toast.makeText(OtherCompActivity.this, "启用按钮", Toast.LENGTH_LONG).show();
            findViewById(R.id.toggleButton).setEnabled(true);
        }else{
            Toast.makeText(OtherCompActivity.this, "禁用按钮", Toast.LENGTH_LONG).show();
            findViewById(R.id.toggleButton).setEnabled(false);

        }
    }

    public void checkboxClick(View view){
        boolean isChecked =((CheckBox)view).isChecked();
        String msg= (String) ((CheckBox)view).getText();
        switch (view.getId()){
            case R.id.checkBox:
                Toast.makeText(OtherCompActivity.this, "选择了："+msg, Toast.LENGTH_LONG).show();
                break;
            case R.id.checkBox2:
                Toast.makeText(OtherCompActivity.this, "选择了："+msg, Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
