package com.wlz.getshitdone.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wlz.getshitdone.R;
import com.wlz.getshitdone.Utils;
import com.wlz.getshitdone.db.NotesDao;
import com.wlz.getshitdone.model.Notes;

import java.sql.SQLException;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WaiLynnZaw on 4/18/15.
 */
public class UpdateNotes extends ActionBarActivity {
    @InjectView(R.id.notes_title)
    EditText title;
    @InjectView(R.id.notes_content)
    EditText content;
    @InjectView(R.id.notes_accept)
    Button notes_accept;

    NotesDao notesDao;
    Notes current_notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        ButterKnife.inject(this);

        notesDao = new NotesDao(this);
        current_notes = new Notes();
        current_notes = (Notes) getIntent().getSerializableExtra("data");
        initialize();
        setActionBar();
        notes_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNote();
            }
        });

    }

    private void initialize(){
        title.setText(current_notes.title);
        content.setText(current_notes.content);
        notes_accept.setText("Update");
    }
    private void updateNote(){
        current_notes.title = title.getText().toString();
        current_notes.content = content.getText().toString();
        current_notes.updated_at = Utils.getHttpDate();
        try {
            notesDao.updateNote(current_notes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finish();
    }

    private void deleteDiary(){
        try {
            notesDao.deleteNote(current_notes.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finish();
    }

    private void setActionBar(){
        if(current_notes.title != null)getSupportActionBar().setTitle(current_notes.title);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        if(item.getItemId() == R.id.action_delete){
            deleteDiary();
        }
        return true;
    }
}
