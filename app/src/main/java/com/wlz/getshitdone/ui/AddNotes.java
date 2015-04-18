package com.wlz.getshitdone.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;

import com.wlz.getshitdone.R;

import butterknife.InjectView;

/**
 * Created by WaiLynnZaw on 4/18/15.
 */
public class AddNotes extends ActionBarActivity {
    @InjectView(R.id.diary_title)
    EditText title;
    @InjectView(R.id.diary_content)
    EditText content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
    }
}
