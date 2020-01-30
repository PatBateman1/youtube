package com.example.youtube.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.youtube.MainActivity;
import com.example.youtube.interfaces.IPlayerControl;
import com.example.youtube.interfaces.IPlayerViewControl;

import java.io.IOException;

public class PlayVideoService extends Service {

    private static final String TAG = "PlayVideoService";

    private InnerBinder mBinder;
    private MediaPlayer player;
    private int currentSong = 0;


    public PlayVideoService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (mBinder == null) {
            mBinder = new InnerBinder();
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinder = null;
    }

    private void initPlayer() {
        player = new MediaPlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    private void initPlayerList() {
        if (MainActivity.playList.size() > 0) {
            if (player != null) {
                player.stop();
                player = null;
            }
            initPlayer();
            String videoStreamUrl = MainActivity.playList.get(currentSong % MainActivity.playList.size()).split("\\^")[1];
            Log.d(TAG, "initPlayerList: " + videoStreamUrl);
            try {
                player.setDataSource(videoStreamUrl);
                player.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "initPlayerList: cannot set data source");
            }
            
        } else {
            Log.d(TAG, "initPlayerList: 11111111111");
        }
    }



    private class InnerBinder extends Binder implements IPlayerControl {
        private IPlayerViewControl viewController;
        private int currentPlayerState = IPlayerControl.PLAYER_STATE_STOP;

        @Override
        public void playOrPause() {
            if (currentPlayerState == IPlayerControl.PLAYER_STATE_STOP) {
                initPlayerList();
                currentPlayerState = IPlayerControl.PLAYER_STATE_PLAY;
            } else if (currentPlayerState == IPlayerControl.PLAYER_STATE_PAUSE) {
                player.start();
                currentPlayerState = IPlayerControl.PLAYER_STATE_PLAY;
            } else {
                player.pause();
                currentPlayerState = IPlayerControl.PLAYER_STATE_PAUSE;
            }

            if (viewController != null) {
                viewController.onStateChange(currentPlayerState);
            }

        }

        @Override
        public void seekTo(int seek) {
            if (player != null) {
                player.seekTo(seek);
            }
        }

        @Override
        public void next() {
            currentSong++;
            initPlayerList();
            currentPlayerState = IPlayerControl.PLAYER_STATE_PLAY;
            if (viewController != null) {
                viewController.onStateChange(currentPlayerState);
            }

        }

        @Override
        public void setViewController(IPlayerViewControl viewController) {
            this.viewController = viewController;
        }

        @Override
        public void removeViewController() {
            viewController = null;
        }
    }
}
