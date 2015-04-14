package com.wlz.getshitdone.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.wlz.getshitdone.model.Diary;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by WaiLynnZaw on 4/14/15.
 */
public class DiaryDao {
    public static final String EMPTY_REC = "just_empty";
    private Dao<Diary, Integer> diaryDao;
    private ConnectionSource source;

    public DiaryDao(Context ctx) {
        DatabaseManager dbManager = new DatabaseManager();
        DatabaseHelper dbHelper = dbManager.getHelper(ctx);
        try {
            diaryDao = dbHelper.getBuildTypeDao();
            source = dbHelper.getConnectionSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Diary diary) {
        try {
            return diaryDao.create(diary);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Diary> getAll() throws SQLException {
        return diaryDao.queryBuilder().query();
    }

    public void deleteAll() throws SQLException {
        TableUtils.clearTable(source, Diary.class);
    }
}
