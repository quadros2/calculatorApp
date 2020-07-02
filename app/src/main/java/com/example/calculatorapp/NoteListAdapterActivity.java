package com.example.calculatorapp;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class NoteListAdapterActivity extends ArrayAdapter<Note> {

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

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

        EditText noteTitle = listItems.findViewById(R.id.noteTitle);
        EditText noteText = listItems.findViewById(R.id.noteText);
        Button deleteNote = listItems.findViewById(R.id.deleteNote);
        TextView dateText = listItems.findViewById(R.id.timeStamp);


        Note note = noteList.get(position);

        noteTitle.setText(note.getTitle());
        noteText.setText(note.getText());
        dateText.setText(note.getDate());

        deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid() + "'s notes")
                        .document(note.getDate()).delete();
                noteList.remove(position);
                notifyDataSetChanged();
            }
        });

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                DocumentReference documentReference;
                documentReference = firebaseFirestore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid() + "'s notes")
                        .document(note.getDate());
                String title = noteTitle.getText().toString();
                documentReference.update("title", title);
                noteTitle.setText(title);
            }
        });

        noteText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                DocumentReference documentReference;
                documentReference = firebaseFirestore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid() + "'s notes")
                        .document(note.getDate());
                String text = noteText.getText().toString();
                documentReference.update("text", text);
                noteText.setText(text);
            }
        });

        return listItems;
    }
}
