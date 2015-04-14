package com.wlz.getshitdone.db;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Created by WaiLynnZaw on 1/31/15.
 */
public class DatabaseManager {
    private DatabaseHelper dbHelper = null;

    public DatabaseHelper getHelper(Context context) {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return dbHelper;
    }

    //releases the helper once usages has ended
    public void releaseHelper(DatabaseHelper helper) {
        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }
}
