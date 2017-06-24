package main.zzy.com.moon_news;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wq.photo.widget.PickConfig;

import java.util.ArrayList;

import Control.Control_NightMode;
import Control.Control_touxiang;
import Control.Login;
import DB.DataBase;
import Fragment.*;
import Tools.FilterImageView;

/**
 * 主Activity
 */
public class MainContent extends AppCompatActivity {
    private FilterApplication app;
    private DrawerLayout mDrawerLayout;
    private Toolbar toobar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Fragment news, find, talk, man;
    private LinearLayout LeftDraw;
    private  ImageView sun,man_pict,toobar_pict;
    private RelativeLayout home,favourites,color,history,shop,top,setting;
    private Control_NightMode nightMode;
    private TextView toobar_text,drawer_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);

        init();
        initButton();
        man_pict();
        ButtonClicked();
        InitFragment();
        initDrawerLayout();

    }



    /**
     * 这里要刷新。。。。。。
     */
    private void shuaxin() {

    }



    private void man_pict(){
    man_pict.setImageResource(R.mipmap.touxiang_boy);
    toobar_pict.setImageResource(R.mipmap.touxiang_boy);
    try {
        new Control_touxiang(man_pict, drawer_text);                  //控制头像
        new Control_touxiang(toobar_pict, toobar_text);
    }catch (Exception e){
       return;
    }
}


    private void init() {
        app = (FilterApplication) getApplication();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);
        LeftDraw= (LinearLayout) findViewById(R.id.Left_Drawer);
        toobar = (Toolbar) findViewById(R.id.Toolbar);
        toobar.setTitle("");
        setSupportActionBar(toobar);

    }

    private void InitFragment() {
        if (news == null)
            news = new Base_Main_Content__News();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.Main_Fragment_Layout, news);
        transaction.commit();
    }

    private void initDrawerLayout() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toobar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }





    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }





    /**
     * 四个按钮点击事件
     */


    public void news(View view) {
        toobar.setVisibility(toobar.VISIBLE);
        if(news==null)
            news=new Base_Main_Content__News();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.Main_Fragment_Layout,news);
        transaction.commit();
    }

    public void find(View view) {
        toobar.setVisibility(toobar.VISIBLE);
        if(find==null)
            find=new Main_Content_find();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.Main_Fragment_Layout,find);
        transaction.commit();
    }

    public void talk(View view) {
        toobar.setVisibility(toobar.VISIBLE);
        if(talk==null)
            talk=new Main_Content_Talk();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.Main_Fragment_Layout,talk);
        transaction.commit();
    }

    public void man(View view) {
        toobar.setVisibility(toobar.GONE);
        if(man==null)
            man=new Main_Content_Man();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.Main_Fragment_Layout,man);
        transaction.commit();
    }

    /**
     * 下面是繁琐的点击事件-----没有封装
     */

    private void initButton() {
        toobar_pict= (ImageView) findViewById(R.id.toobar_touxiang);
        sun= (ImageView) findViewById(R.id.small_drawer_pict);
        man_pict= (ImageView) findViewById(R.id.drawer_man_pict);
        home= (RelativeLayout) findViewById(R.id.DrawerHome);
        favourites= (RelativeLayout) findViewById(R.id.DrawerFavourites);
        color= (RelativeLayout) findViewById(R.id.DrawerColor);
        history= (RelativeLayout) findViewById(R.id.DrawerHistory);
        shop= (RelativeLayout) findViewById(R.id.DrawerShop);
        top= (RelativeLayout) findViewById(R.id.DrawerTop);
        setting= (RelativeLayout) findViewById(R.id.DrawerSetting);
        toobar_text= (TextView) findViewById(R.id.toobar_text);
        drawer_text=(TextView)findViewById(R.id.drawer_text);
    }

    private void ButtonClicked() {
        sun.setImageResource(R.mipmap.sun);
        /**
         * 设置滤镜 光线
         */
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nightMode=new Control_NightMode(app,sun,MainContent.this);
                shuaxin();
        }
        });


        man_pict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Login(app,MainContent.this);
                mDrawerLayout.closeDrawer(LeftDraw);

            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.Main_Fragment_Layout,news);
                transaction.commit();
                toobar.setVisibility(View.VISIBLE);
                mDrawerLayout.closeDrawer(LeftDraw);

            }
        });


        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainContent.this,"?",Toast.LENGTH_LONG).show();
                mDrawerLayout.closeDrawer(LeftDraw);
                MainContent.this.finish();
            }
        });


        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainContent.this,"?",Toast.LENGTH_LONG).show();
                mDrawerLayout.closeDrawer(LeftDraw);
                MainContent.this.finish();
            }
        });


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainContent.this,"?",Toast.LENGTH_LONG).show();
                mDrawerLayout.closeDrawer(LeftDraw);
                MainContent.this.finish();
            }
        });


        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainContent.this,"?",Toast.LENGTH_LONG).show();
                mDrawerLayout.closeDrawer(LeftDraw);
                MainContent.this.finish();
            }
        });


        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainContent.this,"?",Toast.LENGTH_LONG).show();
                mDrawerLayout.closeDrawer(LeftDraw);
                MainContent.this.finish();
            }
        });


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainContent.this,"?",Toast.LENGTH_LONG).show();
                mDrawerLayout.closeDrawer(LeftDraw);
                MainContent.this.finish();
            }
        });
    }





}
