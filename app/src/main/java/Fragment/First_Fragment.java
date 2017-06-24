package Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import main.zzy.com.moon_news.MainContent;
import main.zzy.com.moon_news.R;

/**
 * Created by Administrator on 2016/8/23.
 */
public class First_Fragment extends Fragment {
private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        final int layoutId = bundle.getInt("layoutId");
        int pageIndex = bundle.getInt("pageIndex");
        View view = inflater.inflate(layoutId, container, false);
        view.setTag(pageIndex);

            if(pageIndex==2) {
               Button button= (Button) view.findViewById(R.id.startActivity);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Intent intent=new Intent(getActivity(),MainContent.class);
                        startActivity(intent);



                    }
                });
            }


        return view;
    }
}
