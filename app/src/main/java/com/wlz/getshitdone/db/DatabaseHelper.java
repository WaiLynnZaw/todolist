package com.wlz.getshitdone.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.wlz.getshitdone.model.Diary;

import java.sql.SQLException;

/**
 * Created by WaiLynnZaw on 1/31/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "getshitdone.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Diary, Integer> diaryDao = null;
//    private Dao<Models, Integer> modelDao = null;
//    private Dao<Manufacturers,Integer> manufacturerDao = null;
//    private Dao<Locations,Integer> locationDao = null;
//    private Dao<Colors,Integer> colorDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Diary.class);
//            TableUtils.createTable(connectionSource, Models.class);
//            TableUtils.createTable(connectionSource, Manufacturers.class);
//            TableUtils.createTable(connectionSource, Locations.class);
//            TableUtils.createTable(connectionSource, Colors.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Diary.class,true);
//            TableUtils.dropTable(connectionSource,Models.class,true);
//            TableUtils.dropTable(connectionSource,Manufacturers.class,true);
//            TableUtils.dropTable(connectionSource,Locations.class,true);
//            TableUtils.dropTable(connectionSource,Colors.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Dao<Diary,Integer> getBuildTypeDao() throws SQLException {
        if (diaryDao == null) {
            diaryDao = getDao(Diary.class);
        }
        return diaryDao;
    }

//    public Dao<Models,Integer> getModelDao() throws SQLException {
//        if (modelDao == null) {
//            modelDao = getDao(Models.class);
//        }
//        return modelDao;
//    }
//
//    public Dao<Manufacturers,Integer> getManufacturerDao() throws SQLException {
//        if (manufacturerDao == null){
//            manufacturerDao = getDao(Manufacturers.class);
//        }
//        return manufacturerDao;
//    }
//
//    public Dao<Locations,Integer> getLocationDao() throws SQLException {
//        if (locationDao == null){
//            locationDao = getDao(Locations.class);
//        }
//        return locationDao;
//    }
//
//    public Dao<Colors,Integer> getColorDao() throws SQLException {
//        if (colorDao == null){
//            colorDao = getDao(Colors.class);
//        }
//        return colorDao;
//    }

    @Override
    public void close() {
        super.close();
        diaryDao = null;
//        modelDao = null;
//        manufacturerDao = null;
//        locationDao = null;
//        colorDao = null;
    }
}
