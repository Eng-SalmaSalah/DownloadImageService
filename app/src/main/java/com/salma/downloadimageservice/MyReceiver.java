package com.salma.downloadimageservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

            String imageName = intent.getStringExtra("imageName");
            Intent intentToImageActivity = new Intent(context, ImageActivity.class);
            intentToImageActivity.putExtra("imageName",imageName);
            intentToImageActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentToImageActivity);

    }
}
