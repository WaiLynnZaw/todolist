package com.wlz.getshitdone.ui;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlz.getshitdone.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import tr.xip.errorview.ErrorView;

/**
 * Created by WaiLynnZaw on 4/19/15.
 */
public class VitaminsFragment extends Fragment {
    @InjectView(R.id.error_view)
    ErrorView errorView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vitamins,container,false);
        ButterKnife.inject(this,v);
        errorView.setImageResource(R.mipmap.ic_launcher);
        errorView.setTitle("Coming Soon!");
        errorView.setSubtitle("");
        errorView.setTitleColor(Color.parseColor("#a3a3a3"));
        errorView.showRetryButton(false);
        return v;
    }
}
