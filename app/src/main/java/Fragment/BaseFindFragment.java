package Fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import Adapter.Content_find_Adapter;
import Bean.find_cardview;
import Tools.pagerControl;
import main.zzy.com.moon_news.R;

/**
 * Created by Administrator on 2016/8/25.
 * 这一层是find的viewpager层
 */
public class BaseFindFragment extends Fragment {
    /**
     * time_pager
     */
    private ImageHandler handler = new ImageHandler(new WeakReference<BaseFindFragment>(this));
    protected List<ImageView> picture;
    private ViewPager viewPager;
    private ImageView[] imageViews;
    private ViewGroup viewCircle;
    private ImageView imageView;
    private View view;

    /**
     * card
     */
    private RecyclerView recy;
    protected List<find_cardview> list;
    protected find_cardview con;
    MyAdapter_CardView cardAdapter;
    private static final int NORMAL_ITEM = 0;
    private static final int GROUP_ITEM = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_content_find, container, false);
        cardDatas();
        init();
        content();
        contentCircle();
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);//默认在中间，使用户看不到边界
        //开始轮播效果
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
        return view;
    }


    private void init() {
        viewPager = (ViewPager) view.findViewById(R.id.content_find_viewPager);
        viewCircle = (ViewGroup) view.findViewById(R.id.content_find_viewCircle);
        new pagerControl().setViewScrollSpeed(viewPager, getActivity());
        recy = (RecyclerView) view.findViewById(R.id.card_view_recy);
        recy.setAdapter(cardAdapter = new MyAdapter_CardView());
        recy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    /**
     * 关于数据要单独封装这里先用里面的图片提供测试
     */
    protected void content() {
        picture = new ArrayList<>();
        ImageView image1 = new ImageView(getActivity());
        image1.setBackgroundResource(R.mipmap.content_man_bg01);
        ImageView image2 = new ImageView(getActivity());
        image2.setBackgroundResource(R.mipmap.content_man_bg01);
        ImageView image3 = new ImageView(getActivity());
        image3.setBackgroundResource(R.mipmap.content_man_bg01);
        ImageView image4 = new ImageView(getActivity());
        image4.setBackgroundResource(R.mipmap.content_man_bg01);

        picture.add(image1);
        picture.add(image2);
        picture.add(image3);
        picture.add(image4);

        Content_find_Adapter m = new Content_find_Adapter(picture);
        viewPager.setAdapter(m);//填充数据
        viewPager.addOnPageChangeListener(new mPagerChangedListener());

    }

    /**
     * card的数据封装 这里只是测试
     */
    protected void cardDatas() {

        list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            con = new find_cardview();
            con.setImage(R.mipmap.drawer_bg);
            list.add(con);
        }

    }

    private void contentCircle() {
        imageViews = new ImageView[picture.size()];
        for (int i = 0; i < picture.size(); i++) {
            imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 5));//设置宽和高
            imageView.setPadding(5, 5, 5, 5);
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.mipmap.checked);
            } else {
                imageViews[i].setBackgroundResource(R.mipmap.unchecked);
            }
            viewCircle.addView(imageViews[i]);
        }

    }

    private static class ImageHandler extends Handler {

        /**
         * 请求更新显示的View。
         */
        protected static final int MSG_UPDATE_IMAGE = 1;
        /**
         * 请求暂停轮播。
         */
        protected static final int MSG_KEEP_SILENT = 2;
        /**
         * 请求恢复轮播。
         */
        protected static final int MSG_BREAK_SILENT = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED = 4;

        //轮播间隔时间
        protected static final long MSG_DELAY = 3000;

        //使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
        private WeakReference<BaseFindFragment> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<BaseFindFragment> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            BaseFindFragment page = weakReference.get();
            if (page == null) {
                //Activity已经回收，无需再处理UI了
                return;
            }
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (page.handler.hasMessages(MSG_UPDATE_IMAGE)) {
                page.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    page.viewPager.setCurrentItem(currentItem);
                    //准备下次播放
                    page.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    page.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }


    class mPagerChangedListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
           /* what.getAndSet(position);*/

            for (int i = 0; i < picture.size(); i++) {
                if (i == (position + 1) % 4) {
                    imageViews[i].setBackgroundResource(R.mipmap.checked);
                } else {
                    imageViews[i].setBackgroundResource(R.mipmap.unchecked);
                }
            }
            handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, position, 0));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                    break;
                default:
                    break;
            }
        }
    }


    class MyAdapter_CardView extends RecyclerView.Adapter<BaseFindFragment.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == NORMAL_ITEM) {
                MyHolder myholder = new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.content_find_cardview, viewGroup, false));
                return myholder;
            } else {
                MyHolder myholder = new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.content_find_cardview, viewGroup, false));
                return myholder;
            }
        }


        @Override
        public void onBindViewHolder(MyHolder myHolder, int i) {
            find_cardview content = list.get(i);
            if (content == null) {
                return;
            }
            myHolder.image.setBackgroundResource(list.get(i).getImage());
        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return GROUP_ITEM;
            } else {
                return NORMAL_ITEM;
            }
        }

    }

    /**
     * card
     */
    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public MyHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.content_find_card_image);
            view.findViewById(R.id.content_find_card_image).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), getPosition() + "z ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}