package com.example.youtube.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youtube.R;

import java.util.List;


public class PlayListFragment extends Fragment {

    private List<String> playlist;
    private TextView v;
    public PlayListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        if (v != null) {
            if (playlist != null) {
                v.setText("" + playlist.size());
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_list, container, false);
        if (getArguments() != null) {
            playlist = getArguments().getStringArrayList("playList");
        }
        v = view.findViewById(R.id.playlist);
        if (playlist != null) {
            v.setText("" + playlist.size());
        }
        return view;
    }
}
