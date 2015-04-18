package com.wlz.getshitdone.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.wlz.getshitdone.R;
import com.wlz.getshitdone.db.NotesDao;
import com.wlz.getshitdone.model.Notes;

import java.sql.SQLException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import tr.xip.errorview.ErrorView;

/**
 * Created by WaiLynnZaw on 4/14/15.
 */
public class MyNotes extends ActionBarActivity {
    @InjectView(R.id.notes_listView)
    ListView listView;
    @InjectView(R.id.error_view)
    ErrorView errorView;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    NotesDao notesDao;
    ArrayList<Notes> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        ButterKnife.inject(this);
        notesDao = new NotesDao(this);
        notesList = new ArrayList<>();
        //listView.setAdapter(adapter);
    }

    private void getNotes(){
        try {
            notesList.clear();
            if(notesDao.getAll().size() == 0){
                errorView.setVisibility(View.VISIBLE);
                errorView.setImageResource(R.mipmap.ic_launcher);
                errorView.setTitle("You have no notebooks.");
                errorView.setSubtitle("");
                errorView.setTitleColor(Color.parseColor("#a3a3a3"));
                errorView.showRetryButton(false);
            }else {
                errorView.setVisibility(View.GONE);
                notesList.addAll(notesDao.getAll());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
