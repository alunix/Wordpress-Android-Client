package cn.com.httpbin.app.wpandroidclient.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.httpbin.app.wpandroidclient.R;

/**
 * Created by lu on 2015/12/29.
 */
public class PostsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_posts, container, false);
    }
}
