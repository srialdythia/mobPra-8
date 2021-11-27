package com.example.modul8_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnHandlerThread,btnHttpConnection,btnUpdate,btnTugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHandlerThread = findViewById(R.id.btnHandlerThread);
        btnHttpConnection = findViewById(R.id.btnHttpConnection);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnTugas = findViewById(R.id.btnTugas);

        btnHandlerThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HandlerThread.class));
            }
        });
        btnHttpConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, URLImage.class));
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProgressBar.class));
            }
        });
        btnTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Tugas.class));
            }
        });
    }
}