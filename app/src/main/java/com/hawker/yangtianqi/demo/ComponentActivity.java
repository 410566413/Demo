package com.hawker.yangtianqi.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hawker.yangtianqi.demo.model.Person;
import com.hawker.yangtianqi.demo.view.ImageListViewAdapter;

public class ComponentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private final static String TAG="ComponentActivity";
    private ListView listView;
    private Button btnAddPerson;
    private Button btnAddText;
    private Button btnAddPic;
    private ArrayAdapter<String> arrayAdapterText;
    private ArrayAdapter<Person> arrayAdapter;
    private BaseAdapter baseAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
        listView = (ListView) findViewById(R.id.listView);

        btnAddPerson = (Button) findViewById(R.id.btnAddPerson);
        btnAddPerson.setOnClickListener(this);

        btnAddText = (Button) findViewById(R.id.btnAddText);
        btnAddText.setOnClickListener(this);

        btnAddPic = (Button) findViewById(R.id.btnAddPic);
        btnAddPic.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(parent != null){
            Log.i(TAG,"<<<<<<<<<<");
        }
        Person person = arrayAdapter.getItem(position);
        Toast.makeText(this, String.format("名字：%s 性别：%s 年龄：%d",person.getUserName(),person.getSex(),person.getAge()),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddText:
                addText();
                break;
            case R.id.btnAddPerson:
                addPerson();
                break;
            case R.id.btnAddPic:
                addPic3();
                break;
            default:
                break;
        }
    }

    public void addText(){
        arrayAdapterText= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapterText);
        arrayAdapterText.add("hello");
        arrayAdapterText.add("world");
    }

    public void addPerson(){

        arrayAdapter= new ArrayAdapter<Person>(this,android.R.layout.simple_list_item_1);

        listView.setAdapter(arrayAdapter);


        arrayAdapter.add(new Person("小米","男",22));
        arrayAdapter.add(new Person("小红","女",21));
        listView.setOnItemClickListener(this);
    }

    public void addPic(){
        final String[] data =new String[]{"zhangsan","lisi","wangwu","zhaoliu"};
        baseAdapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return data.length;
            }

            @Override
            public Object getItem(int position) {
                return data[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView =null;
                if(convertView!=null){
                    textView = (TextView) convertView;
                }else {
                    textView= new TextView(ComponentActivity.this);
                }
                textView.setText((String) getItem(position));
                textView.setTextSize(50);
                return textView;
            }
        };
        listView.setAdapter(baseAdapter);
    }

   private Person[] data=new Person[]{
            new Person("小米","男",22,R.drawable.head1),
            new Person("小花","女",18,R.drawable.head2)
    };
    public void addPic2(){


        baseAdapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return data.length;
            }

            @Override
            public Person getItem(int position) {
                return data[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout ll =null;
                if(convertView!=null){
                    ll = (LinearLayout) convertView;
                }else{
                    ll = (LinearLayout) LayoutInflater.from(ComponentActivity.this).inflate(R.layout.layout_image_listview,null);
                }
                ImageView icon = (ImageView) ll.findViewById(R.id.icon);
                TextView userName = (TextView) ll.findViewById(R.id.userName);
                TextView sex = (TextView) ll.findViewById(R.id.sex);

                Person person = getItem(position);
                icon.setImageResource(person.getIconId());
                userName.setText(person.getUserName());
                sex.setText(person.getSex());
                return ll;
            }
        };
        listView.setAdapter(baseAdapter);
    }

    public void addPic3(){
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ImageListViewAdapter(this));
    }

}
