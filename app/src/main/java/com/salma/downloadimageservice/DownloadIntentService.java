package com.salma.downloadimageservice;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownloadIntentService extends IntentService {
    HttpsURLConnection httpsURLConnection = null;
    InputStream inputStream = null;
    MyReceiver myReceiver;
    IntentFilter filter;
    Bitmap image = null;
    URL imageURL = null;

    public DownloadIntentService() {
        super("DownloadIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            filter=new IntentFilter("imageDownloaded");
            myReceiver=new MyReceiver();
            registerReceiver(myReceiver,filter);
            String imageURLString = intent.getStringExtra("imageURL");
            imageURL = new URL(imageURLString);
            httpsURLConnection = (HttpsURLConnection) imageURL.openConnection();
            httpsURLConnection.connect();
            inputStream = httpsURLConnection.getInputStream();
            image = BitmapFactory.decodeStream(inputStream);
            saveToInternalStorage(image);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void saveToInternalStorage(Bitmap image) {
        FileOutputStream fos = null;
        String imageName="flag";
        try {
            fos =  openFileOutput(imageName, Context.MODE_PRIVATE);
            // Use the compress method on the BitMap object to write image to the OutputStream
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Log.i("done", "imageDownloaded");
            Intent intent=new Intent(this,MyReceiver.class);
            intent.setAction("imageDownloaded");
            intent.putExtra("imageName",imageName);
            sendBroadcast(intent);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
