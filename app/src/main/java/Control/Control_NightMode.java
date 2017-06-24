package Control;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import main.zzy.com.moon_news.FilterApplication;
import main.zzy.com.moon_news.R;

/**
 * Created by Administrator on 2016/8/29.
 */
public class Control_NightMode extends ImageView{
    private FilterApplication app;
   private ImageView small_drawer_pict;
    public Control_NightMode(FilterApplication app,ImageView sun,Context context){
        super(context);
        this.app =app;
        small_drawer_pict=sun;
        init();
        control();
    }
    public Control_NightMode(Context context) {
        super(context);
    }


    /**
     * 判断白天与黑夜
     */
    private void init() {
        if(app.getValue().equals("day")) {
            app.setValue("night"); // 重新设置值
            small_drawer_pict.setImageResource(R.mipmap.drawer_moon);
        }else {
            app.setValue("day"); // 重新设置值
            small_drawer_pict.setImageResource(R.mipmap.sun);
        }
    }

    private void control() {

    }



}
