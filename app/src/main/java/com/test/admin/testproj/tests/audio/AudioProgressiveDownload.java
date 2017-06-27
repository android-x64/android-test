package com.test.admin.testproj.tests.audio;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.test.admin.testproj.R;

import java.io.IOException;

/**
 * Created by Admin on 04.04.2015.
 */
public class AudioProgressiveDownload extends Activity implements MediaPlayer.OnPreparedListener {
    private static final String url = "http://project-tango.org/Projects/TangoBand/Songs/files/01%20La%20Cumparsita.mp3";
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_progressive_download);

    }

    @Override
    protected void onDestroy() {
        releaseMediaPlayer();
        super.onDestroy();
    }

    public void onPlayClick(View v) {
        releaseMediaPlayer();
        initMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if(null != mediaPlayer) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}
