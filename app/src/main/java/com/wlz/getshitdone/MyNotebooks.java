package com.wlz.getshitdone;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.melnykov.fab.FloatingActionButton;
import com.wlz.getshitdone.adapter.GridViewAdapter;
import com.wlz.getshitdone.db.DiaryDao;
import com.wlz.getshitdone.model.Diary;

import java.sql.SQLException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MyNotebooks extends Fragment implements AdapterView.OnItemLongClickListener {
    @InjectView(R.id.notebooks_gridview)
    GridView notebook_gridview;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    EditText title,content;
    ArrayList<Diary> diaryList;
    DiaryDao diaryDao;
    GridViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main,container,false);
        ButterKnife.inject(this,v);
        diaryDao = new DiaryDao(getActivity());
        diaryList = new ArrayList<>();

        getDiaries();

        adapter = new GridViewAdapter(getActivity(),diaryList);
        notebook_gridview.setAdapter(adapter);
        notebook_gridview.setOnItemLongClickListener(this);
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
                        getDiaries();
                        adapter.notifyDataSetChanged();
                    }
                }).build();
        title = (EditText) dialog.getCustomView().findViewById(R.id.diary_title);
        content = (EditText) dialog.getCustomView().findViewById(R.id.diary_content);
        dialog.show();
    }

    private void getDiaries(){
        try {
            diaryList.clear();
            diaryList.addAll(diaryDao.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
        new MaterialDialog.Builder(getActivity())
                .title("Choose Action")
                .items(R.array.edit_diary)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                        if (i == 1) {
                            try {
                                diaryDao.deleteDiary(diaryList.get(position).id);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            getDiaries();
                            adapter.notifyDataSetChanged();
                        }
                    }
                })
                .positiveText("Choose")
                .show();
        return true;
    }
}
