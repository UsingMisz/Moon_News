package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import Control.Control_touxiang;
import Control.Login;
import main.zzy.com.moon_news.FilterApplication;
import main.zzy.com.moon_news.R;

/**
 * Created by Administrator on 2016/8/25.
 */
public class Main_Content_Man extends Fragment {
    private TextView man_text;
    private ImageView touxiang;
    private Control_touxiang control_touxiang;
    private RelativeLayout Favourites,Wallet,Shop,Message,History,Off_Line,Setting,feedback;
    private View view;
    private FilterApplication app;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_content_man,container,false);

        init();
        touxiang.setImageResource(R.mipmap.touxiang_boy);
        checkedButton();
        try {
            control_touxiang = new Control_touxiang(touxiang, man_text);
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;


    }



    /**
     * 麻烦的处理方式 处理Button
     */
    private void init() {
        app = (FilterApplication) getActivity().getApplication();
        touxiang = (ImageView) view.findViewById(R.id.content_man_touxiang);
        Wallet= (RelativeLayout) view.findViewById(R.id.man_wallet);
        Shop= (RelativeLayout) view.findViewById(R.id.man_Shop);
        Message= (RelativeLayout) view.findViewById(R.id.man_Message);
        History= (RelativeLayout) view.findViewById(R.id.man_History);
        Off_Line= (RelativeLayout) view.findViewById(R.id.man_OffLine);
        Setting= (RelativeLayout) view.findViewById(R.id.man_setting);
        feedback= (RelativeLayout) view.findViewById(R.id.man_feedback);
        Favourites= (RelativeLayout) view.findViewById(R.id.man_favourites);
        man_text= (TextView) view.findViewById(R.id.man_text);
    }

    /**
     * 没有用butterknife注解 比较乱
     */
    private void checkedButton() {

        Wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Login(app,getActivity());
                Toast.makeText(getActivity(),"?.-",Toast.LENGTH_LONG).show();
            }
        });
        Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"?.--",Toast.LENGTH_LONG).show();
            }
        });
        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"?.h",Toast.LENGTH_LONG).show();
            }
        });
        Off_Line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"?.o",Toast.LENGTH_LONG).show();
            }
        });
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"?.s",Toast.LENGTH_LONG).show();
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"?.",Toast.LENGTH_LONG).show();
            }
        });
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Login(app,getActivity());
            }
        });
        Favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"?.",Toast.LENGTH_LONG).show();
            }
        });

    }

}
