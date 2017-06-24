package Dialog;


import android.app.Activity;

import android.support.v7.app.AlertDialog;

import android.view.View;

import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;





import java.util.LinkedHashMap;

import DB.DataBase;
import main.zzy.com.moon_news.R;
import main.zzy.com.moon_news.com.touxiang;


/**
 * Created by Administrator on 2016/9/8.
 */
public class Dialogs {
    public LinkedHashMap imasgemap = new LinkedHashMap();
    private Activity activity;
    private CalendarView calendar;
    public Dialogs(Activity activity) {
        this.activity = activity;
    }
    LinkedHashMap hashmap=getImageChoseMap();;
    private DataBase db = new DataBase();


    public LinkedHashMap getImageChoseMap() {
        return imasgemap;
    }

    public void sexDialog(final TextView sex) {

        final View view = View.inflate(activity, R.layout.dialog_sex, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setIcon(R.mipmap.qq_icon);
        builder.setTitle("性别");
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.show();
        dialog.show();
        view.findViewById(R.id.dialog_sex_boy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DataBase().update_qq_gender("男");
                  sex.setText("男");

                Toast.makeText(activity, "nan", Toast.LENGTH_SHORT).show();      //这些都统一设置到数据库并传入服务器中
                dialog.dismiss();

            }
        });

        view.findViewById(R.id.dialog_sex_girl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DataBase().update_qq_gender("女");
                sex.setText("女");
                Toast.makeText(activity, "女", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
    }



     public void setTouxiang(){
         final View view = View.inflate(activity, R.layout.dialog_picture, null);
         final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
         builder.setTitle("头像");
         builder.setView(view);
         builder.setCancelable(true);
         final AlertDialog dialog = builder.show();
         dialog.show();
         view.findViewById(R.id.test_paizhao).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {


                 new touxiang(activity).choiceStart1();

                   dialog.dismiss();
             }
         });
         view.findViewById(R.id.test_xuanze).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 new touxiang(activity).choiceStart();
                 //选择头像的
                   dialog.dismiss();

             }
         });

     }



    public void setBirthday(final TextView birthday) {
        final View view = View.inflate(activity, R.layout.dialog_birthday, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("生日");
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.show();
        dialog.show();
        calendar = (CalendarView) view.findViewById(R.id.dialog_birthday);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "年" + month + "月" + dayOfMonth + "日";
                new DataBase().update_qq_birthday(date);
                birthday.setText(date);
                Toast.makeText(activity, date, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }


    public void setName(final TextView name){

        final View view = View.inflate(activity, R.layout.dialog_name, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("姓名");
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.show();
        dialog.show();
        final EditText edit = (EditText) view.findViewById(R.id.dialog_name_text);
        view.findViewById(R.id.dialog_name_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name.setText(edit.getText());
                new DataBase().update_qq_name(name.getText().toString());
                dialog.dismiss();
            }
        });
    }




    public void setqianming(final TextView day) {
        final View view = View.inflate(activity, R.layout.dialog_qianming, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("个性签名");
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.show();
        dialog.show();
        final EditText edit = (EditText) view.findViewById(R.id.dialog_EditText);
        view.findViewById(R.id.dialog_qianming_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                day.setText(edit.getText());
                new DataBase().update_qq_qianming(edit.getText().toString());

                dialog.dismiss();
            }
        });

    }


    public void study() {
        final View view = View.inflate(activity, R.layout.dialog_qianming, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("个性签名");
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.show();
        dialog.show();
        final EditText edit = (EditText) view.findViewById(R.id.dialog_EditText);
        view.findViewById(R.id.dialog_qianming_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.getText();
                dialog.dismiss();
            }
        });
    }




}