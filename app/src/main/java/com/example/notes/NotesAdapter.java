package com.example.notes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    public static class NoteViewHolder extends RecyclerView.ViewHolder{
        LinearLayout containerview;
        TextView textView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.note_row_text);

            // returns AssetManager in adapter class by name.getContext().getAsset()

            Typeface text_note = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/Lekton-Regular.ttf");
            textView.setTypeface(text_note);

            containerview = itemView.findViewById(R.id.note_row);


            containerview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Note current = (Note) containerview.getTag();

                    Intent intent = new Intent(view.getContext(),NoteActivity.class);
                    intent.putExtra("id",current.id);
                    intent.putExtra("contents",current.contents);

                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note current = notes.get(position);
        holder.textView.setText(current.contents);
        holder.containerview.setTag(current);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void reload(){
        notes = MainActivity.database.noteDao().getAllNotes();
        // That's going to tell Recyclerview that we've updated that underlying object,reload yourself
        notifyDataSetChanged();
    }


}
