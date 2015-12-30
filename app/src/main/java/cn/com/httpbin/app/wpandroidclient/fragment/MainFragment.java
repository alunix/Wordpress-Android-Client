package cn.com.httpbin.app.wpandroidclient.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.com.httpbin.app.wpandroidclient.R;

/**
 * Created by lu on 2015/12/29.
 */
public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        TextView tv = (TextView) view.findViewById(R.id.fm_main_tv);
        tv.setText("Hello, World");

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Nothing Found", Snackbar.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
