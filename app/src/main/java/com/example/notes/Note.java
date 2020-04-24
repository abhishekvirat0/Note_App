package com.example.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//class represents a table in database
@Entity(tableName = "Note")
public class Note {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "contents")
    public String contents;
}
