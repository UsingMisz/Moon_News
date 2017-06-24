package Control;

import android.content.Context;
import android.content.Intent;

import DB.DataBase;
import main.zzy.com.moon_news.FilterApplication;
import main.zzy.com.moon_news.Login_Activity;
import main.zzy.com.moon_news.Personal_Activity;
import main.zzy.com.moon_news.R;

/**
 * Created by Administrator on 2016/8/30.
 * 分辨是否有登录 跳转到登录还是个人中心
 */
public class Login {
    private FilterApplication app;
    private Context context;
     private DataBase db = new DataBase();
    public Login(FilterApplication app,Context context){
            this.app=app;
            this.context=context;
            init();
    }

    protected void init() {
        if(app.getLogin().equals("login")) {
            Intent intent=new Intent(context, Login_Activity.class);
            context.startActivity(intent);
        }
        else {
            Intent intent=new Intent(context, Personal_Activity.class);
            context.startActivity(intent);

        }
    }


}
