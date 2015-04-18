package com.wlz.getshitdone.ui;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.melnykov.fab.FloatingActionButton;
import com.wlz.getshitdone.R;
import com.wlz.getshitdone.Utils;
import com.wlz.getshitdone.adapter.GridViewAdapter;
import com.wlz.getshitdone.db.DiaryDao;
import com.wlz.getshitdone.model.Diary;

import java.sql.SQLException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import tr.xip.errorview.ErrorView;


public class MyNotebooks extends Fragment implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    @InjectView(R.id.notebooks_gridview)
    GridView notebook_gridview;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.error_view)
    ErrorView errorView;

    EditText title,content;
    ArrayList<Diary> diaryList;
    DiaryDao diaryDao;
    GridViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_notebooks,container,false);
        ButterKnife.inject(this,v);
        diaryDao = new DiaryDao(getActivity());
        diaryList = new ArrayList<>();
        getDiaries();
        adapter = new GridViewAdapter(getActivity(),diaryList);
        notebook_gridview.setAdapter(adapter);
        notebook_gridview.setOnItemLongClickListener(this);
        notebook_gridview.setOnItemClickListener(this);
        fab.attachToListView(notebook_gridview);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDiary();
            }
        });
        return v;
    }


    // CRUD Methods
    private void getDiaries(){
        try {
            diaryList.clear();
            if(diaryDao.getAll().size() == 0){
                errorView.setVisibility(View.VISIBLE);
                errorView.setImageResource(R.mipmap.ic_launcher);
                errorView.setTitle("You have no notebooks.");
                errorView.setSubtitle("");
                errorView.setTitleColor(Color.parseColor("#a3a3a3"));
                errorView.showRetryButton(false);
            }else {
                errorView.setVisibility(View.GONE);
                diaryList.addAll(diaryDao.getAll());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private void updateDiary(final Diary diary, final int id){
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .title("Update Diary")
                .customView(R.layout.activity_add_diary,true)
                .positiveText("Accept")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        diary.title = title.getText().toString();
                        diary.content = content.getText().toString();
                        diary.updated_at = Utils.getHttpDate();
                        try {
                            diaryDao.updateDiary(diary,id);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }).build();
        title = (EditText) dialog.getCustomView().findViewById(R.id.diary_title);
        content = (EditText) dialog.getCustomView().findViewById(R.id.diary_content);
        title.setText(diary.title);
        content.setText(diary.content);
        dialog.show();
    }

    private void deleteDiary(int position){
        try {
            diaryDao.deleteDiary(diaryList.get(position).id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getDiaries();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
        new MaterialDialog.Builder(getActivity())
                .title("Choose Action")
                .items(R.array.edit_diary)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                        switch (i) {
                            case 0:
                                updateDiary(diaryList.get(position), diaryList.get(position).id);
                                break;
                            case 1:
                                deleteDiary(position);
                                break;
                        }
                    }
                })
                .positiveText("Choose")
                .show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent it = new Intent(getActivity(),MyNotes.class);
        it.putExtra("id",diaryList.get(i).id);
        startActivity(it);
    }
}
