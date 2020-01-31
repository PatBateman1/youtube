package com.example.youtube.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youtube.R;
import com.example.youtube.adapters.PlayListAdapter;

import java.util.List;


public class PlayListFragment extends Fragment {

    private List<String> playlist;
    private PlayListAdapter adapter;
    private ListView list;

    public PlayListFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_list, container, false);
        list = view.findViewById(R.id.play_list);
        if (getArguments() != null) {
            playlist = getArguments().getStringArrayList("playList");
        }


        initAdapter();


        return view;
    }


    private void initAdapter() {

        list.setAdapter(adapter);
    }

    public void setAdapter(PlayListAdapter adapter) {
        this.adapter = adapter;
    }
}
