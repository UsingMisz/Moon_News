package Tools;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import main.zzy.com.moon_news.MainContent;
import main.zzy.com.moon_news.R;

/**
 * Created by zzy on 2016/8/23.
 * 当页面发生改变时
 */
public class First_Activity_control implements ViewPager.PageTransformer, ViewPager.OnPageChangeListener {
    private Context context;
    private int pageIndex;
    private boolean pagexChanged = true;
    private ImageView pict1, pict2, pict3;

    public First_Activity_control(Context context) {
        this.context = context;
    }

    /**
     * 当页面转换的时候
     *
     * @param page
     * @param position
     */
    @Override
    public void transformPage(View page, float position) {
        pict1 = (ImageView) page.findViewById(R.id.first_picture);
        pict2 = (ImageView) page.findViewById(R.id.second_picture);
        pict3 = (ImageView) page.findViewById(R.id.third_picture);
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.first_alpha);

        int tag = (int) page.getTag();
        if (tag == pageIndex) {
            switch (pageIndex) {
                case 0:
                    pict1.startAnimation(anim);
                    break;
                case 1:
                    pict2.startAnimation(anim);
                    break;
                case 2:
                    pict3.startAnimation(anim);
                    break;
                default:
                    break;
            }
        }
        if (position == 0) {
            if (pagexChanged) {
                pagexChanged = false;
            }

        } else if (position == -1 || position == 1) {

        } else if (position > -1 && position < 1) {

        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            

    }

    @Override
    public void onPageSelected(int position) {
        pageIndex = position;
        pagexChanged = true;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
