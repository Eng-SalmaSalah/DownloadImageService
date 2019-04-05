package com.salma.downloadimageservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageActivity extends AppCompatActivity {
    ImageView flagImage;
    String action;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        flagImage = findViewById(R.id.imgFlag);
        Intent intent = getIntent();
        //to allow sharing photos in my app
        action = intent.getAction();
        type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleReceivedImage(intent);
            }
        }

        //end of sharing photos in my app
        else {
            String imagePath = intent.getStringExtra("imageName");
            Bitmap bitmap = null;
            try {
                // read image from file
                bitmap = BitmapFactory.decodeStream(openFileInput(imagePath));
                flagImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    //to allow sharing photos in my app
    private void handleReceivedImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            flagImage.setImageURI(imageUri);

        } else {
            Toast.makeText(this, "Error, URI is invalid", Toast.LENGTH_LONG).show();
        }
    }
}
