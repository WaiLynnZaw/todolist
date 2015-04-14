package com.wlz.getshitdone.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * Created by WaiLynnZaw on 4/14/15.
 */
@DatabaseTable(tableName = "diary")
public class Diary {
    @DatabaseField (generatedId = true) public int id;
    @DatabaseField public String title;
    @DatabaseField public String content;
    @DatabaseField public String created_at;
    @DatabaseField public String updated_at;
}
