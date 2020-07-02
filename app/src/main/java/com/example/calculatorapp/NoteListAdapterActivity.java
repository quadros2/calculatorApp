package com.example.calculatorapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NoteListAdapterActivity extends ArrayAdapter<Note> {

    private Activity appContext;
    private List<Note> noteList;

    public NoteListAdapterActivity(Activity appContext, List<Note> noteList) {
        super(appContext, R.layout.chunk_note, noteList);
        this.appContext = appContext;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = appContext.getLayoutInflater();
        View listItems = inflater.inflate(R.layout.chunk_note, null, true);

        TextView noteTitle = listItems.findViewById(R.id.noteTitle);
        TextView noteText = listItems.findViewById(R.id.noteText);
        Button viewNote = listItems.findViewById(R.id.viewNote);
        Button deleteNote = listItems.findViewById(R.id.deleteNote);

        Note note = noteList.get(position);

        noteTitle.setText(note.getTitle());
        noteText.setText(note.getDescription());

        viewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return listItems;
    }
}
