package Control;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import DB.DataBase;
import http.DownLoadImage;

/**
 * Created by Administrator on 2016/9/2.  多写了一个接口,写乱了
 */
public class Control_touxiang  {
   private DataBase db;
    DownLoadImage down;
    public Control_touxiang(ImageView touxiang, TextView textView){
              init(touxiang,textView);
    }

    private void init(ImageView touxiang,TextView textView) {
         down=new DownLoadImage(touxiang,textView);
    }



   public void setSexText(TextView sex){
       db=new DataBase();
       try {
           sex.setText(db.query_qq_name().get(0).getGender());
       }catch (Exception e){

       }

   }





}
