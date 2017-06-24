package main.zzy.com.moon_news;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.util.ArrayList;

import Control.Control_touxiang;
import DB.DataBase;
import Dialog.Dialogs;
import Tools.Login_control;
import http.DownLoadImage;
import main.zzy.com.moon_news.com.wq.photo.widget.PickConfig;

public class Personal_Activity extends AppCompatActivity {
    private FilterApplication app;
    private ImageView image;
    private Control_touxiang control_touxiang;
    private TextView name, sex, birthday, day;
    private Toolbar toolbar;
    private RelativeLayout sex1, birthady1, personal_day, personal_picture, name1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_);
        app = (FilterApplication) getApplication();
        init();
        content();
        show();
        checked();
    }


    private void init() {
        image = (ImageView) findViewById(R.id.personal_icon);
        toolbar = (Toolbar) findViewById(R.id.personal_toolbar);
        name = (TextView) findViewById(R.id.dialog_text_name);
        name1 = (RelativeLayout) findViewById(R.id.personal_name);
        sex = (TextView) findViewById(R.id.personal_sex_text);
        sex1 = (RelativeLayout) findViewById(R.id.personal_sex);
        birthady1 = (RelativeLayout) findViewById(R.id.personal_birthday);
        personal_day = (RelativeLayout) findViewById(R.id.personal_day);
        birthday = (TextView) findViewById(R.id.dialog_text_birthday);
        day = (TextView) findViewById(R.id.dialog_text_day);
        personal_picture = (RelativeLayout) findViewById(R.id.personal_picture);
    }

    private void content() {
        control_touxiang = new Control_touxiang(image, name);
        control_touxiang.setSexText(sex);
        toolbar.setTitle("个人中心");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void show() {

        try {
            birthday.setText(new DataBase().query_qq_name().get(0).getBirthday());
            day.setText(new DataBase().query_qq_name().get(0).getDay());

        } catch (Exception e) {

        }
    }


    private void checked() {
        sex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dialogs(Personal_Activity.this).sexDialog(sex);

            }
        });
        birthady1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dialogs(Personal_Activity.this).setBirthday(birthday);
            }
        });
        personal_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dialogs(Personal_Activity.this).setqianming(day);
            }
        });
        personal_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dialogs(Personal_Activity.this).setTouxiang();
            }
        });
        name1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dialogs(Personal_Activity.this).setName(name);
            }
        });

    }


    /**
     * 退出登录
     *
     * @param view
     */
    public void personal_button(View view) {
        SharedPreferences sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("isLogin", "login");
        ed.commit();

        new Login_control(this, app).cancle_qq();

        new DataBase().delete_qq();
        new DownLoadImage().clearCache();
        Intent intent = new Intent(Personal_Activity.this, MainContent.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    int chosemode = PickConfig.MODE_SINGLE_PICK;
    public static final int REQUEST_CODE_CAMERA = 2001;
    File currentfile;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (resultCode == RESULT_OK && requestCode == PickConfig.PICK_REQUEST_CODE) {
            //在data中返回 选择的图片列表
            ArrayList<String> paths = data.getStringArrayListExtra("data");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, paths);
           *//* listview.setAdapter(adapter);*//*

            Log.i("where is",paths.get(0).toString());
        }*/
    }





}
