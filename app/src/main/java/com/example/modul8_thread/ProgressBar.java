package com.example.modul8_thread;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class ProgressBar extends AppCompatActivity {
    private ProgressDialog dialog;
    private int increment;
    private int maximum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText inc = findViewById(R.id.ETincrement);
                EditText max = findViewById(R.id.ETmaximum);
                increment =  Integer.parseInt(inc.getText().toString());
                maximum = Integer.parseInt(max.getText().toString());
                dialog = new ProgressDialog(ProgressBar.this);
                dialog.setCancelable(true);
                dialog.setMessage("Loading");
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setProgress(0);
                dialog.setMax(maximum);
                dialog.show();

                Thread background = new Thread (new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (dialog.getProgress() <= dialog.getMax()){
                                Thread.sleep(500);
                                progressHandler.sendMessage(progressHandler.obtainMessage());
                            }
                        }catch (java.lang.InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                });
                background.start();
            }
            private Handler progressHandler = new Handler() {
                public void handleMessage(Message msg){
                    dialog.incrementProgressBy(increment);
                    if(dialog.getProgress() == dialog.getMax()){
                        dialog.dismiss();
                    }
                }
            };
        });

    }
}