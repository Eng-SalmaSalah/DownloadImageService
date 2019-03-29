package com.salma.downloadimageservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageActivity extends AppCompatActivity {
ImageView flagImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Intent intent=getIntent();
        String imagePath = intent.getStringExtra("imageName");
        flagImage=findViewById(R.id.imgFlag);
        Bitmap bitmap=null;
        try {
           // read image from file
            bitmap = BitmapFactory.decodeStream(openFileInput(imagePath));
            flagImage.setImageBitmap(bitmap);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
