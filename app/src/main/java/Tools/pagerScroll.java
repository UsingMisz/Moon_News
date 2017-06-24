package Tools;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/8/27.   在这里设置自动滑动的时间
 */
public class pagerScroll extends Scroller {
    private int mduration = 1500;

    public pagerScroll(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public pagerScroll(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    public pagerScroll(Context context) {
        super(context);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mduration);
    }


}
