package com.example.planner20;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class new_check_note extends AppCompatActivity {
    Button addCheck;
    CheckBox checkBoxItem;
    String checkBoxInput;
    LinearLayout linearLayoutCheckItem, checkLayout;
    EditText inputText;
    CheckBox checkbox;
    boolean addAgain;
    FloatingActionButton saveBtn;
    String title;
    EditText newNoteTitle;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_check_note);

        saveBtn = findViewById(R.id.saveBtn);
        addCheck = findViewById(R.id.addCheck);
        checkBoxItem = findViewById(R.id.checkboxItem);
        linearLayoutCheckItem = (LinearLayout) findViewById(R.id.linearLayoutCheckItem);
        inputText = (EditText) findViewById(R.id.inputText);
        checkLayout = (LinearLayout) findViewById(R.id.checkLayout);
        newNoteTitle = (EditText) findViewById(R.id.newCheckNoteTitle);

        addCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = inputText.getText().toString();
                if (addAgain){

                    checkbox = new CheckBox(new_check_note.this);
                    checkbox.setText(Name);
                    checkLayout.addView(checkbox);
                }
                addAgain = !addAgain;
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int imageResourceId = R.drawable.ic_check;
                title = newNoteTitle.getText().toString();
                if (title == null || title.length() == 0) {
                    Toast.makeText(new_check_note.this, "Enter title", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(new_check_note.this, MainActivity.class);
                    i.putExtra("title", title);
                    i.putExtra("imageNote", imageResourceId);
                    startActivity(i);
                }

            }
        });
    }

}