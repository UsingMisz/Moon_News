package http;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import DB.DataBase;
import main.zzy.com.moon_news.R;

/**
 * Created by Administrator on 2016/9/2.
 */
public class DownLoadImage {
    DataBase db = new DataBase();
    ImageOptions imageOptions;
    ImageView touxiang;
    TextView touxiang_text;
 public DownLoadImage(){}


    public DownLoadImage(ImageView touxiang,TextView touxiang_text) {
        this.touxiang = touxiang;
        this.touxiang_text=touxiang_text;
        init();
    }

    private void init() {
        initSmallPict();
        loadSmallImg(db.query_qq_name().get(0).getBitmap());

    }



    private void initSmallPict() {
        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(50), DensityUtil.dip2px(50))
                .setRadius(DensityUtil.dip2px(50))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.touxiang_girl)
                .build();



    }
      public void clearCache(){
          x.image().clearCacheFiles();
          x.image().clearMemCache();
      }


    /**
     * 从网络上下载小图片
     *
     * @param url
     */
    protected void loadSmallImg(String url) {
        try {
            Log.i("ishaveurl?????????????",url+"?");
            x.image().bind(touxiang, url, imageOptions);
            if (db.query_qq_name() != null) {
                touxiang_text.setText(db.query_qq_name().get(0).getName());
            }
        } catch (Exception e) {
                //网络访问错误

        }


    }


}







