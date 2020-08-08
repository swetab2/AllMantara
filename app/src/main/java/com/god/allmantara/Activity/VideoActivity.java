package com.god.allmantara.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.god.allmantara.Constant;
import com.god.allmantara.R;

public class VideoActivity extends AppCompatActivity {


    protected VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = findViewById(R.id.videoView);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        String strUrl = getIntent().getStringExtra(Constant.STR_URL);
        Uri uri = Uri.parse(strUrl);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.seekTo(1000);
        videoView.start();
    }
}
