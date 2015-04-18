package com.wlz.getshitdone.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.wlz.getshitdone.R;
import com.wlz.getshitdone.adapter.ListViewAdapter;
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
public class MyNotes extends ActionBarActivity implements AdapterView.OnItemClickListener {
    @InjectView(R.id.notes_listView)
    ListView listView;
    @InjectView(R.id.error_view)
    ErrorView errorView;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    NotesDao notesDao;
    ArrayList<Notes> notesList;
    ListViewAdapter adapter;
    int diary_id = 0;

    @Override
    protected void onResume() {
        super.onResume();
        getNotes();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        ButterKnife.inject(this);
        setActionBar();
        diary_id = getIntent().getIntExtra("id",0);
        notesDao = new NotesDao(this);
        notesList = new ArrayList<>();
        getNotes();
        adapter = new ListViewAdapter(this,notesList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(),AddNotes.class);
                it.putExtra("id",diary_id);
                startActivity(it);
            }
        });
    }

    private void getNotes(){
            notesList.clear();
            if(notesDao.getAllById(diary_id).size() == 0){
                errorView.setVisibility(View.VISIBLE);
                errorView.setImageResource(R.mipmap.ic_launcher);
                errorView.setTitle("You have no notebooks.");
                errorView.setSubtitle("");
                errorView.setTitleColor(Color.parseColor("#a3a3a3"));
                errorView.showRetryButton(false);
            }else {
                errorView.setVisibility(View.GONE);
                notesList.addAll(notesDao.getAllById(diary_id));
            }
    }

    private void setActionBar(){
        getSupportActionBar().setTitle("My Notes");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent it = new Intent(getApplicationContext(),UpdateNotes.class);
        it.putExtra("data",notesList.get(i));
        startActivity(it);
    }
}
