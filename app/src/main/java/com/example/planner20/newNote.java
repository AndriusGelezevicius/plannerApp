package com.example.planner20;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class newNote extends AppCompatActivity {
    FloatingActionButton saveBtn;
    EditText newNoteTitle;
    EditText newNotetxt;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        saveBtn = findViewById(R.id.saveBtn);
        newNoteTitle = (EditText) findViewById(R.id.newNoteTitle);
        newNotetxt = (EditText) findViewById(R.id.newNotetxt);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int imageResourceId = R.drawable.ic_newnote;
                title = newNoteTitle.getText().toString();
                if (title == null || title.length() == 0) {
                    Toast.makeText(newNote.this, "Enter title", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(newNote.this, MainActivity.class);
                    i.putExtra("title", title);
//                    i.putExtra("imageNote", imageResourceId);
                    startActivity(i);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
    }
}