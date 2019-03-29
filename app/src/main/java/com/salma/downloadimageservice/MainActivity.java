package com.salma.downloadimageservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button downloadBtn;
    EditText downloadLink;
    String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadBtn = (Button) findViewById(R.id.btnDownload);
        downloadLink = (EditText) findViewById(R.id.txtURL);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgUrl = downloadLink.getText().toString();
                Intent intent = new Intent(MainActivity.this, DownloadIntentService.class);
                intent.putExtra("imageURL", imgUrl);
                startService(intent);

            }
        });
    }
}
