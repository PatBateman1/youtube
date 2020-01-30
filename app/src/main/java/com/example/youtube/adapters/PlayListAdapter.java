package com.example.youtube.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.youtube.MainActivity;
import com.example.youtube.R;

public class PlayListAdapter extends BaseAdapter {


    private Context context;
    private LayoutInflater inflater;


    public PlayListAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return MainActivity.playList.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.playList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.play_list_item, null);
        }
        String[] result = MainActivity.playList.get(position).split("\\^");
        String videoName = result[0];
        String videoStreamUrl = result[1];
        TextView name = convertView.findViewById(R.id.play_list_item_title);
        name.setText(videoName);

        return convertView;
    }

}
