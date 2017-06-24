package main.zzy.com.moon_news;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.Config;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.Map;

import Tools.Login_control;

public class Login_Activity extends AppCompatActivity {
    private ImageView qq;
    private ImageView touxiang;
    private Login_control control;
     private  FilterApplication app;
    private TextView p_user,p_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        app = (FilterApplication) getApplication();
        init();
        Config.dialogSwitch = true;
        clicked();
    }

    private void init() {
        control = new Login_control(this, app);
        qq = (ImageView) findViewById(R.id.Icon_qq);
        touxiang = (ImageView) findViewById(R.id.login_touxiang);
        p_user= (TextView) findViewById(R.id.Login_user);
        p_password= (TextView) findViewById(R.id.login_password);
         huanhuang();
    }

    private void huanhuang() {
        p_user.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i==KeyEvent.KEYCODE_ENTER){

                    return true;
                }
                return false;
            }
        });
    }


    private void clicked() {
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                control.qq_clicked();

            }
        });
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login_Activity.this, "clicked", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        control.callback().onActivityResult(requestCode, resultCode, data);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }


}
