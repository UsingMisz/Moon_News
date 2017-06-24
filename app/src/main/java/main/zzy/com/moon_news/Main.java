package main.zzy.com.moon_news;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boolean mFirst = isFirstEnter(Main.this, Main.this.getClass().getName()); //调用并返回值
        if (mFirst) {

            mHandler.sendEmptyMessage(SWITCH_GUIDACTIVITY); //如果是第一次登陆就给1001
        } else {
            mHandler.sendEmptyMessage(SWITCH_MAINACTIVITY);//如果不是就给1000
        }
    }

    //****************************************************************
// 判断应用是否初次加载，读取SharedPreferences中的guide_activity字段
//****************************************************************
    private static final String SHAREDPREFERENCES_NAME = "my_pref";
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";


    @SuppressLint("WorldReadableFiles")//??
    @SuppressWarnings("deprecation")//?
    private boolean isFirstEnter(Main context, String className) {
        if (context == null || className == null || "".equalsIgnoreCase(className))
            return false; //判断是否是第一次登陆如果不是就跳出
        String mResultStr = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE)
                .getString(KEY_GUIDE_ACTIVITY, "");//取得所有类名 如 com.my.MainActivity
        if (mResultStr.equalsIgnoreCase("false"))
            return false;
        else
            return true;
    }

    //*************************************************
// Handler:跳转至不同页面
//*************************************************
    private final static int SWITCH_MAINACTIVITY = 1000; //判断的两个int值
    private final static int SWITCH_GUIDACTIVITY = 1001;
    @SuppressLint("HandlerLeak")
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SWITCH_MAINACTIVITY: //如果判断的不是第一次就调到登陆页面
                    Intent mIntent = new Intent();
                    mIntent.setClass(Main.this, MainContent.class);
                    Main.this.startActivity(mIntent);
                    Main.this.finish();
                    break;
                case SWITCH_GUIDACTIVITY: //如果判断是第一次登陆
                    mIntent = new Intent();
                    mIntent.setClass(Main.this, First_Activity.class);
                    Main.this.startActivity(mIntent);
                    Main.this.finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

}






























