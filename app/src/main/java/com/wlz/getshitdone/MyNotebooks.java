package com.wlz.getshitdone;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.melnykov.fab.FloatingActionButton;
import com.wlz.getshitdone.db.DiaryDao;
import com.wlz.getshitdone.model.Diary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MyNotebooks extends Fragment {
    @InjectView(R.id.notebooks_gridview)
    GridView notebook_gridview;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    EditText title,content;
    List<Diary> diaryList;
    DiaryDao diaryDao;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main,container,false);
        ButterKnife.inject(this,v);
        diaryDao = new DiaryDao(getActivity());
        diaryList = new ArrayList<>();
        getDiaries();
        fab.attachToListView(notebook_gridview);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDiary();
            }
        });
        return v;
    }

    private void addDiary(){
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .title("Add New Diary")
                .customView(R.layout.activity_add_diary, true)
                .positiveText("Accept")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        Diary diary = new Diary();
                        diary.title = title.getText().toString();
                        diary.content = content.getText().toString();
                        diary.created_at = Utils.getHttpDate();
                        diary.updated_at = Utils.getHttpDate();
                        diaryDao.create(diary);

                    }
                }).build();
        title = (EditText) dialog.getCustomView().findViewById(R.id.diary_title);
        content = (EditText) dialog.getCustomView().findViewById(R.id.diary_content);
        dialog.show();
    }
    private void getDiaries(){
        try {
            diaryList = diaryDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
