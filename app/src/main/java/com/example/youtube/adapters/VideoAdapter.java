package com.example.youtube.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.youtube.R;

import java.util.List;
import java.util.Map;

public class VideoAdapter extends BaseAdapter {

    private List<Map<String, Object>> videos;
    private LayoutInflater inflater;

    public VideoAdapter(Context context, List<Map<String, Object>> data) {
        this.inflater = LayoutInflater.from(context);
        this.videos = data;
    }


    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new MyViewHolder();
            convertView = inflater.inflate(R.layout.vedio_item, null);
            //viewHolder.vedioCover = convertView.findViewById(R.id.video_cover);
            viewHolder.vedioTitle = convertView.findViewById(R.id.video_title);
            viewHolder.vedioPublishedAt = convertView.findViewById(R.id.video_published_at);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        Map<String, Object> video = videos.get(position);
        viewHolder.vedioTitle.setText((String) video.get("title"));
        viewHolder.vedioPublishedAt.setText((String) video.get("publishedAt"));

        return convertView;
    }



    public class MyViewHolder {
         //public ImageView vedioCover;
         public TextView vedioTitle;
         public TextView vedioPublishedAt;
    }

    public void setVideos (List<Map<String, Object>> data) {
        this.videos = data;
    }

}
