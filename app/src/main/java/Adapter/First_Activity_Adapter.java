package Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import Fragment.*;
import main.zzy.com.moon_news.R;

/**
 * Created by Administrator on 2016/8/23.
 */
public class First_Activity_Adapter extends FragmentPagerAdapter{
    private int[] layoutIds = {R.layout.activity_first_one, R.layout.activity_first_two, R.layout.activity_first_three};
    public First_Activity_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       Fragment fragment =new First_Fragment();
        Bundle bundle =new Bundle();
        bundle.putInt("layoutId",layoutIds[position]);
        bundle.putInt("pageIndex",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
