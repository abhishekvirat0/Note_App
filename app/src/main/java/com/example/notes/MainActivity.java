package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static NotesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // code to hide action bar in 1st activity only
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        TextView title = (TextView) findViewById(R.id.title);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        title.setTypeface(font);

        database = Room
                .databaseBuilder(getApplicationContext(), NotesDatabase.class,"notes")
                .allowMainThreadQueries()
                .build();

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(this,2);
        adapter = new NotesAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        FloatingActionButton button = findViewById(R.id.add_note_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.noteDao().create();
                adapter.reload();
            }
        });

        // when app runs the first thing it's going to do is go to database and grab everything and display in Recyclerview.
       // adapter.reload();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return 0;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        }).attachToRecyclerView(recyclerView);

    }



    @Override
    protected void onResume() {
        super.onResume();
        // when app runs the first thing it's going to do is go to database and grab everything and display in Recyclerview.
        adapter.reload();
    }

}
