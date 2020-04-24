package com.example.notes;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    // Abstract class because Room library is going to implement for us.
    public abstract NoteDao noteDao();
}
