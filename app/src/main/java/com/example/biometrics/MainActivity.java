package com.example.biometrics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    BiometricAct biometricAct;
    Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricAct.biometricPrompt(MainActivity.this);
            }
        });

        biometricAct = new BiometricAct();
        if(biometricAct.checkCompatibility(this) == true){

        }else{
            alertDialog();
        }
    }

    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Fingerprint Diary");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Your device doesnt support fingerprint feature").setCancelable(false)
                .setPositiveButton("Exit",(dialog, id)->finish());
        AlertDialog alert = builder.create();
        alert.show();
    }
}