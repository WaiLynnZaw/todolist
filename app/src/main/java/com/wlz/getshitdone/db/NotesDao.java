package com.wlz.getshitdone.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.wlz.getshitdone.model.Notes;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by WaiLynnZaw on 4/18/15.
 */
public class NotesDao {
    public static final String EMPTY_REC = "just_empty";
    private Dao<Notes, Integer> notesDao;
    private ConnectionSource source;

    public NotesDao(Context ctx) {
        DatabaseManager dbManager = new DatabaseManager();
        DatabaseHelper dbHelper = dbManager.getHelper(ctx);
        try {
            notesDao = dbHelper.getNotesDao();
            source = dbHelper.getConnectionSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Notes notes) {
        try {
            return notesDao.create(notes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Notes> getAll() throws SQLException {
        return notesDao.queryBuilder().query();
    }

    public void deleteAll() throws SQLException {
        TableUtils.clearTable(source, Notes.class);
    }

    public void deleteNote(int id) throws SQLException{
        notesDao.deleteById(id);
    }

    public void updateNote(Notes notes,int id) throws SQLException {
        notesDao.createOrUpdate(notes);

    }
}
