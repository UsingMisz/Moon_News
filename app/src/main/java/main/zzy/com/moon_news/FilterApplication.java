package main.zzy.com.moon_news;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.umeng.socialize.PlatformConfig;

import org.xutils.x;

/**
 * Created by Administrator on 2016/8/29.
 */
public class FilterApplication extends Application {
    private static final String VALUE = "day";
    private static final String LOGIN="login";
    private String login;
    private String value;
    @Override
    public void onCreate()
    {
        super.onCreate();
        x.Ext.init(this); // 这一步之后, 我们就可以在任何地方使用x.app()来获取Application的实例了.
        x.Ext.setDebug(true); // 是否输出debug日志    xutils
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");   //登入qq的key和value
        setValue(VALUE);
        SharedPreferences sp= getSharedPreferences("Login", Context.MODE_PRIVATE);
            setLogin(LOGIN);
        if(sp.getString("isLogin","none").equals("unlogin")) {
             setLogin("other");
        }
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private void isLogin(){

    }

}
