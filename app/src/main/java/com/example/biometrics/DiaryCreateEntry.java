package com.example.biometrics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class DiaryCreateEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_create_entry);

        Toolbar toolbar = findViewById(R.id.tb_createDiary);
        toolbar.setNavigationOnClickListener(v->exit());

        Button button = findViewById(R.id.diaryInputEnter);
        button.setOnClickListener(v->{
            AlertDialog dialog =createEntryDialog();
            dialog.show();
        });
    }

    private AlertDialog createEntryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DiaryCreateEntry.this);
        builder.setMessage("Save Entry");

        builder.setPositiveButton("Yes", (dialog, which)->{
            TextInputEditText inputTitle = findViewById(R.id.diaryInputTitle);
            String title = inputTitle.getText().toString();

            TextInputEditText inputText = findViewById(R.id.diaryInputText);
            String text = inputText.getText().toString();
            Entry entry = Entry.createDiaryEntry(DiaryCreateEntry.this, title, text);
            DiaryEntryDbHelper dbHelper = new DiaryEntryDbHelper(DiaryCreateEntry.this);
            dbHelper.addEntry(entry);
            dbHelper.close();
            exit();
        });
            builder.setNegativeButton("Cancel", (dialog, which)->{

            });
            AlertDialog dialog = builder.create();
            return dialog;
    }

    private void exit() {
        Intent intent = new Intent(DiaryCreateEntry.this, DiaryActivity.class);
        startActivity(intent);
        DiaryCreateEntry.this.finish();
    }
}