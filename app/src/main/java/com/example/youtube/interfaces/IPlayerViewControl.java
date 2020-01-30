package com.example.youtube.interfaces;

public interface IPlayerViewControl {
    void onStateChange(int state);

    void onSeekChange(int seek);
}
