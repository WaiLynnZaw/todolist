package com.wlz.getshitdone.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wlz.getshitdone.R;
import com.wlz.getshitdone.Utils;
import com.wlz.getshitdone.db.NotesDao;
import com.wlz.getshitdone.model.Notes;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WaiLynnZaw on 4/18/15.
 */
public class AddNotes extends ActionBarActivity {
    @InjectView(R.id.notes_title)
    EditText title;
    @InjectView(R.id.notes_content)
    EditText content;
    @InjectView(R.id.notes_accept)
    Button notes_accept;

    NotesDao notesDao;
    int diary_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        ButterKnife.inject(this);
        setActionBar();
        notesDao = new NotesDao(this);
        diary_id = getIntent().getIntExtra("id",0);
        notes_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });

    }
    private void addNote(){
        Notes notes = new Notes();
        notes.diary_id = diary_id;
        notes.title = title.getText().toString();
        notes.content = content.getText().toString();
        notes.created_at = Utils.getHttpDate();
        notes.updated_at = Utils.getHttpDate();
        notesDao.create(notes);
        finish();
    }
    private void setActionBar(){
        getSupportActionBar().setTitle("Add New Note");
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
}
