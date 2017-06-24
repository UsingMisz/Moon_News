package main.zzy.com.moon_news;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Adapter.First_Activity_Adapter;
import Tools.First_Activity_control;

/**
 * 一开始启动页面
 */
public class First_Activity extends AppCompatActivity {
private ViewPager viewPager;
    private static final String SHAREDPREFERENCES_NAME = "my_pref";
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();
    }

     private void init() {
         viewPager= (ViewPager) findViewById(R.id.Activity_first_ViewPager);
         viewPager.setOffscreenPageLimit(3);
         viewPager.setAdapter(new First_Activity_Adapter(getSupportFragmentManager()));
         First_Activity_control transformor= new First_Activity_control(this);
         viewPager.setPageTransformer(true,transformor);
         viewPager.setOnPageChangeListener(transformor);
                   setGuided();


    }
    private void setGuided() {
        SharedPreferences settings = getSharedPreferences(
                SHAREDPREFERENCES_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_GUIDE_ACTIVITY, "false");
        editor.commit();
    }



}
