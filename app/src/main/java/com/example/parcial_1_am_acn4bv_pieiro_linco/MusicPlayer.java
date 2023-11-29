package com.example.parcial_1_am_acn4bv_pieiro_linco;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayer {

    private static MusicPlayer instance;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    private MusicPlayer() {
        // Constructor privado para prevenir la creación de instancias externas
    }

    public static MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }

    public void initialize(Context context, int resourceId) {
        mediaPlayer = MediaPlayer.create(context, resourceId);
        mediaPlayer.setLooping(true); // Repetir la música
    }

    public void start() {
        if (mediaPlayer != null && !isPlaying) {
            mediaPlayer.start();
            isPlaying = true;
        }
    }

    public void pause() {
        if (mediaPlayer != null && isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            isPlaying = false;
        }
    }
}
