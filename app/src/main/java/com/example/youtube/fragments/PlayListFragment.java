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


//        playlist.add("pieceof shit^https://r3---sn-p5qlsnsr.googlevideo.com/videoplayback?expire=1580373499" +
//                "&ei=m0EyXovbI4LL8wT_o5L4CA&ip=18.204.231.255&id=o-AItiQjrVxel28a3U1wiNhtJsdpqdo7WQP" +
//                "HSQAaFRta_7&itag=18&source=youtube&requiressl=yes&mm=31%2C29&mn=sn-p5qlsnsr%2Csn-p5" +
//                "qs7nek&ms=au%2Crdu&mv=m&mvi=2&pl=18&initcwndbps=973750&vprv=1&mime=video%2Fmp4&gir=" +
//                "yes&clen=35336969&ratebypass=yes&dur=386.937&lmt=1573942956494127&mt=1580351773&fvi" +
//                "p=3&fexp=23842630&c=WEB&txp=5531432&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2" +
//                "Crequiressl%2Cvprv%2Cmime%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=ALgxI2wwRAIgDLj" +
//                "MbG-qBl5vHVd5uUhB_UELtBWOm6MJAsCzyYa3Bu0CIEkc8nzgn6UX3jn8jCdYL_hbFoOBs2UWg7Zn_WSjah" +
//                "QN&lsparams=mm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AHylml4wRAIgRhmGrCt1df6" +
//                "V6J86G54xqTC5V-SSwucGB3CJ0phX6a8CIDIXW7hBUh1cOuP7d0lTSEtUxudFYreTMC_k2xFDCn5R");

        initAdapter();


        return view;
    }


    private void initAdapter() {
       // adapter = new PlayListAdapter(getActivity(), playlist);
        // adapter.notifyDataSetChanged();
        list.setAdapter(adapter);
    }

    public void setAdapter(PlayListAdapter adapter) {
        this.adapter = adapter;
    }
}
