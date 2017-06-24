package DB;

import android.util.Log;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Bean.qq_user;

/**
 * Created by Administrator on 2016/9/1.
 */
public class DataBase {


    public  qq_user qqUser ;
    public List<qq_user> list_name = new ArrayList<>();
    private DbManager db;

    public qq_user dataQQ(Map<String, String> data) {
        qqUser = new qq_user();
        qqUser.setName(data.get("screen_name"));
        qqUser.setBitmap(data.get("profile_image_url"));
        qqUser.setCity(data.get("city"));
        qqUser.setGender(data.get("gender"));
        return qqUser;
    }


    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("MoonNews_user.db")
            .setDbVersion(1)
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    // 开启WAL, 对写入加速提升巨大
                    db.getDatabase().enableWriteAheadLogging();
                }
            })
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                }
            });



    /**
     * 存入qq
     *
     * @param dataQQ
     */
    public void saveQQ(qq_user dataQQ) {
            db = x.getDb(daoConfig);
            try {
                db.saveOrUpdate(dataQQ);
            } catch (DbException e) {
                e.printStackTrace();
            }

    }


    /**
     * 判断qq唯一标识
     * @param
     */
    public void setOpenId(Map<String, String> data){
        db = x.getDb(daoConfig);
        try {
            db.save(data.get("openid"));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


/**
 * 更新qq姓名  不能为空
 */
 public void update_qq_name(String name){
     db = x.getDb(daoConfig);

     try {
         db.update(qq_user.class,
                 WhereBuilder.b("open", "=",query_qq_name().get(0).getOpen()),
                 new KeyValue("name", name));
     } catch (DbException e) {
         e.printStackTrace();
     }

 }

    /**
     * 更新图片
     */
    public void update_qq_touxiang(String url){
        db = x.getDb(daoConfig);

        try {
            db.update(qq_user.class,
                    WhereBuilder.b("open", "=",query_qq_name().get(0).getOpen()),
                    new KeyValue("bitmap", url));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }



    /**
     * 更新qq姓别
     * @param gender
     */
    public void update_qq_gender(String gender){
        db = x.getDb(daoConfig);
        try {
            db.update(qq_user.class,
                    WhereBuilder.b("open", "=",query_qq_name().get(0).getOpen()),
                    new KeyValue("gender", gender));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新qq生日
     * @param birthday
     */
     public void update_qq_birthday(String birthday){
         db = x.getDb(daoConfig);
         try {
             db.update(qq_user.class,
                     WhereBuilder.b("open", "=",query_qq_name().get(0).getOpen()),
                     new KeyValue("birthday", birthday));
         } catch (DbException e) {
             e.printStackTrace();
         }
     }

/**
 * 更新qq签名
 */
  public void update_qq_qianming(String day){
      db = x.getDb(daoConfig);
      try {
          db.update(qq_user.class,
                  WhereBuilder.b("open", "=",query_qq_name().get(0).getOpen()),
                  new KeyValue("day", day));
      } catch (DbException e) {
          e.printStackTrace();
      }
  }


    /**
     * 查询qq的名字
     */
    public List<qq_user> query_qq_name() {
        db = x.getDb(daoConfig);
        try {
            list_name = db.selector(qq_user.class).orderBy("open", true).findAll();
           return list_name;
        } catch (DbException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除qq
     */
    public void delete_qq() {
        list_name.clear();
        try {
            if (query_qq_name().get(0).getOpen() != null) {
                db.delete(qq_user.class);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


}
