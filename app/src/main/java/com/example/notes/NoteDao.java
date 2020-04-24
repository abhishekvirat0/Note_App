package com.example.notes;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("INSERT INTO Note (contents) VALUES ('New note')")
    void create();

    @Query("SELECT * from Note")
    List<Note> getAllNotes();

    @Query("UPDATE Note SET contents=:contents WHERE id=:id")
    void save(String contents,int id);

    @Query("DELETE FROM Note WHERE id=:id")
    void delete(int id);
}
