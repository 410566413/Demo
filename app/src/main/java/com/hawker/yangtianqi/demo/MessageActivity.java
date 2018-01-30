package com.hawker.yangtianqi.demo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;

public class MessageActivity extends Activity {

    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private   ActionBarDrawerToggle drawerToggle;
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        //动作条中启用向上按钮
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);


        //添加抽屉
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                //重新创建菜单
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
                invalidateOptionsMenu();
            }
        };
//        drawerLayout.setDrawerListener(drawerToggle);


        titles = getResources().getStringArray(R.array.options);
        drawerList = (ListView) findViewById(R.id.drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //设置适配器数据
        drawerList.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                titles
        ));

        //设置点击事件
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        if(savedInstanceState ==null){
            selectItem(0);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
//        menu.findItem(R.id.menu_share).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    //抽屉图标状态同步
//    @Override
//    public void onPostCreate(Bundle savedInstanceState){
//        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
//    }

    //配置变化传递
    @Override
    public void onConfigurationChanged(Configuration configuration){
        super.onConfigurationChanged(configuration);
        drawerToggle.onConfigurationChanged(configuration);
    }


    public  void selectItem(int position){
        Fragment fragment;
        switch (position){
            case 1:
                fragment = new NavDrinkFragment();
                break;
            case 2:
                fragment = new NavFoodFragment();
                break;
            default:
                fragment = new Fragment();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        String title;
        if(position ==0){
            title = getResources().getString(R.string.app_name);
        }else{
            title = titles[position];
        }


        getActionBar().setTitle(title);
        drawerLayout.closeDrawer(drawerList);
    }

//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.menu_main,menu);
//        MenuItem menuItem = menu.findItem(R.id.menu_share);
//        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.putExtra(intent.EXTRA_TEXT,"send message to intent");
//        shareActionProvider.setShareIntent(intent);
//        return super.onCreateOptionsMenu(menu);
//    }


}
