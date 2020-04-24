package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {


    private EditText editText;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.note_edit_text);
        String contents = getIntent().getStringExtra("contents");
        id = getIntent().getIntExtra("id",0);
        editText.setText(contents);

    }

//    when we leave activity
    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.database.noteDao().save(editText.getText().toString(),id);
    }
}
