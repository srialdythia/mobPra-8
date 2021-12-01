package com.example.modul8_thread;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
public class Tugas extends AppCompatActivity {
    int counter = 0;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnDaftar = findViewById(R.id.btnDaftar);
        EditText username = findViewById(R.id.ETuser);
        EditText password = findViewById(R.id.ETpassword);
        EditText status = findViewById(R.id.ETstatus);
        TextView tvHeader = findViewById(R.id.tvHeader);
        TextView tvUser = findViewById(R.id.tvUser);
        TextView tvPassword = findViewById(R.id.tvPassword);
        TextView tvStatus = findViewById(R.id.tvStatus);
        TextView tvPB = findViewById(R.id.tvProgressBar);
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.GONE);
        tvPB.setVisibility(View.GONE);

        DBHelper DB = new DBHelper(this);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String stat = status.getText().toString();
                if(user.equals("")||pass.equals("")||stat.equals("")){
                    Toast.makeText(Tugas.this, "Please enter all the fields",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuser = DB.checkUsername(user);
                    if(checkuser==false){
                        Boolean insert = DB.insertData(user,pass,stat);
                        if(insert==true){
                            Toast.makeText(Tugas.this, "Registered successfully",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Tugas.this, "Registration failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Tugas.this, "User already exists!",
                                Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(Tugas.this, "Please enter username and password",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass == true){
                        btnDaftar.setVisibility(View.GONE);
                        btnLogin.setVisibility(View.GONE);
                        username.setVisibility(View.GONE);
                        password.setVisibility(View.GONE);
                        status.setVisibility(View.GONE);
                        tvHeader.setVisibility(View.GONE);
                        tvUser.setVisibility(View.GONE);
                        tvPassword.setVisibility(View.GONE);
                        tvStatus.setVisibility(View.GONE);
                        tvPB.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.VISIBLE);
                        prog();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "User not found",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void prog(){
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);

                if(counter == 100){
                    t.cancel();
                    startActivity(new Intent(Tugas.this, Dashboard.class));
                }
            }
        };
        t.schedule(tt,0,100);
    }
}