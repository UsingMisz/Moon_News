package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import main.zzy.com.moon_news.R;

/**
 * Created by Administrator on 2016/8/25.   这里的新闻是动态加载的 通过选取不同的fragment来设置
 */
public class Base_Main_Content__News extends Fragment {

    private Fragment toutiao,toutiao01,toutiao02,toutiao03,toutiao04;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list_tab = new ArrayList<>();
    private List<Fragment> list = new ArrayList<>();
    private LayoutInflater inflater;
    private FragmentPagerAdapter fragmentAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_content_news, container, false);
        init();
        addFragment();
        content();
        fragmentAdapter();
        return view;

    }


    private void init() {
        tabLayout = (TabLayout) view.findViewById(R.id.main_content_news_tab);
        viewPager = (ViewPager) view.findViewById(R.id.main_content_news_viewpager);
        inflater = LayoutInflater.from(getContext());

    }

    /**
     * 修改fragment的---需要自定义添加
     */
    private void addFragment() {
        toutiao = new Main_Content_News_Toutiao();
        toutiao01=new Main_Content_News_Toutiao();
        toutiao02=new Main_Content_News_Toutiao();
        toutiao03=new Main_Content_News_Toutiao();
        toutiao04=new Main_Content_News_Toutiao();

        list.add(toutiao);
        list.add(toutiao01);
        list.add(toutiao02);
        list.add(toutiao03);
        list.add(toutiao04);
        list_tab.add("BigNews");
        list_tab.add("Junshi");
        list_tab.add("Liiii");
        list_tab.add("Laaaa");
        list_tab.add("lulululu");
    }

    private void content() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addTab(tabLayout.newTab().setText(list_tab.get(0)));

    }


    private void fragmentAdapter() {
        fragmentAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return list_tab.get(position);
            }


        };

        viewPager.setCurrentItem(0);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);

    }

    @Override
    public void onDestroy() {
        list.clear();
        list_tab.clear();
        super.onDestroy();
    }
}

