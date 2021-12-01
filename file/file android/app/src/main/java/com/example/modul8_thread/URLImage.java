package com.example.modul8_thread;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
public class URLImage extends AppCompatActivity {
    ImageView imageView;
    Bitmap loadedImage;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlimage);
        imageView = findViewById(R.id.imageView);
        downloadFile("https://images.unsplash.com/photo-1557778358-9fb87328a7db?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80");
    }
    public void downloadFile(String urlStr){
        progressDialog = progressDialog.show(this,"","Fetching Image...");
        final String url = urlStr;
        new Thread() {
            public void run(){
                try{
                    URL imageUrl = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                    conn.connect();
                    loadedImage = BitmapFactory.decodeStream(conn.getInputStream());

                } catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to load image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                messageHandler.sendEmptyMessage(0);
            }
        }.start();
    }
    private Handler messageHandler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            imageView.setImageBitmap(loadedImage);
            progressDialog.dismiss();
        }
    };
}