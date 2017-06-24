package main.zzy.com.moon_news.com;

import android.app.Activity;
import android.graphics.Bitmap;

import main.zzy.com.moon_news.com.wq.photo.widget.PickConfig;
import main.zzy.com.moon_news.com.yalantis.ucrop.UCrop;

/**
 * Created by Administrator on 2016/9/14.
 * <p/>
 * com包头像的剪裁 ------------------------------------------------------
 */
public class touxiang {
    private Activity activity;
    private UCrop.Options options=null;
    public touxiang(Activity activity) {
        this.activity = activity;

    }
    public void choiceStart() {
        int chose_mode = PickConfig.MODE_SINGLE_PICK;
        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(60);  //压缩率
        new PickConfig.Builder(activity)
                .isneedcrop(true)
                .isneedcamera(true)
                .isSqureCrop(true)    //剪裁成方形----可以改修改圆形
                .setUropOptions(options)
                .maxPickSize(1)
                .spanCount(4)
                .pickMode(chose_mode).build();
    }
    public void choiceStart1() {
        int chose_mode = PickConfig.MODE_SINGLE_PICK;
        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(60);  //压缩率
        new PickConfig.Builder(activity)
                .isneedcrop(true)
                .isneedcamera(true)
                .isSqureCrop(true)    //剪裁成方形----可以改修改圆形
                .setUropOptions(options)
                .maxPickSize(1)
                .spanCount(4)

                .pickMode(chose_mode).build();

    }

        }
