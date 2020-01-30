package com.example.youtube.interfaces;

public interface IPlayerControl {
    //play state constant:
    int  PLAYER_STATE_PLAY = 1;
    int  PLAYER_STATE_PAUSE = 2;
    int  PLAYER_STATE_STOP = 3;


    void playOrPause();

    void seekTo(int seek);

    void next();

    void setViewController(IPlayerViewControl viewController);

    void removeViewController();
}
