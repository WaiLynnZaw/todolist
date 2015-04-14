package com.wlz.getshitdone;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.melnykov.fab.FloatingActionButton;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MyNotebooks extends Fragment {
    @InjectView(R.id.notebooks_gridview)
    GridView notebook_gridview;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main,container,false);
        ButterKnife.inject(this,v);
        fab.attachToListView(notebook_gridview);
        return v;
    }
}
