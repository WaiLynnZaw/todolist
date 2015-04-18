package com.wlz.getshitdone.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by WaiLynnZaw on 4/18/15.
 */
@DatabaseTable(tableName = "notes")
public class Notes {
    @DatabaseField (generatedId = true) public int id;
    @DatabaseField public int diary_id;
    @DatabaseField public String title;
    @DatabaseField public String content;
    @DatabaseField public String created_at;
    @DatabaseField public String updated_at;
}
