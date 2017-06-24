package Tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Bean.qq_user;
import DB.DataBase;
import main.zzy.com.moon_news.FilterApplication;
import main.zzy.com.moon_news.MainContent;

/**
 * Created by Administrator on 2016/9/1.
 */
public class Login_control {
    private FilterApplication app;
    private UMShareAPI mShareAPI = null;
    private Activity activity;

    public Login_control(Activity activity, FilterApplication app) {
        this.activity = activity;
        this.app = app;
        mShareAPI = UMShareAPI.get(activity);

    }


    public void qq_clicked() {

        SHARE_MEDIA platform = null;
        platform = SHARE_MEDIA.QQ;
        mShareAPI.doOauthVerify(activity, platform, ShouAuthListener);

    }

    private void sp() {
        SharedPreferences sp = activity.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("isLogin", "unlogin");
        ed.commit();
    }

    public UMShareAPI callback() {
        return mShareAPI;
    }

    public void cancle_qq() {
        SHARE_MEDIA platform = null;
        platform = SHARE_MEDIA.QQ;
        mShareAPI.deleteOauth(activity, platform, umdelAuthListener);
    }


    /**
     * 专门删除取消登录的
     **/
    private UMAuthListener umdelAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(activity, "delete Authorize succeed", Toast.LENGTH_SHORT).show();
            app.setLogin("login");
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(activity, "delete Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(activity, "delete Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 专门管理授权的
     */
    private UMAuthListener ShouAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Log.i("ssssss",data.toString());
            new DataBase().setOpenId(data);
            mShareAPI.getPlatformInfo(activity, platform, umAuthListener);

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(activity, "网络已失踪", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(activity, "Authorize cancel", Toast.LENGTH_SHORT).show();
        }

    };

    /**
     * 获取及判定的 得到信息的
     **/
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(activity,data.toString(),Toast.LENGTH_LONG).show();
            DataBase db = new DataBase();
            qq_user dataQQ = db.dataQQ(data);
            db.saveQQ(dataQQ);

            try {

               Log.i("whocsdn",db.query_qq_name().get(0).getBitmap());
                if (!db.query_qq_name().get(0).getBitmap().equals("")) {
                    Toast.makeText(activity,db.query_qq_name().get(0).getBitmap(),Toast.LENGTH_LONG).show();
                    app.setLogin("true");
                    sp();
                    Intent intent = new Intent(activity, MainContent.class);
                    activity.startActivity(intent);
                    activity.finish();
                }
            }catch (Exception e){
                Toast.makeText(activity, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(activity, "网络已失踪", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(activity, "Authorize cancel", Toast.LENGTH_SHORT).show();
        }

    };


}
