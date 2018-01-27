package com.hawker.yangtianqi.demo.view;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hawker.yangtianqi.demo.R;
import com.hawker.yangtianqi.demo.model.Person;

/**
 * Created by yangtianqi on 2018/1/27.
 */
public class ImageListViewAdapter extends BaseAdapter{

    private Context context = null;
    public ImageListViewAdapter(Context context){
        this.context = context;
    }

    public Context getContext(){
        return context;
    }

    private Person[] data=new Person[]{
            new Person("小样","男",22,R.drawable.head1),
            new Person("小花","女",18,R.drawable.head2)
    };

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
            ll = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_image_listview,null);
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



}
