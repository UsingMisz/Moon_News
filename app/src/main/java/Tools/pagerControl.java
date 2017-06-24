package Tools;

import android.app.Activity;

import android.support.v4.view.ViewPager;

import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/8/27.
 */
public class pagerControl {

    public pagerControl() {

    }

    /**
     * 设置速度
     */
    public void setViewScrollSpeed(ViewPager viewPager, Activity activity) {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            pagerScroll scroller = new pagerScroll(activity);
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {

        } catch (IllegalArgumentException e) {

        } catch (IllegalAccessException e) {

        }
    }





}
