package com.example.youtube.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youtube.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class VideoAdapter extends BaseAdapter {

    private List<Map<String, Object>> videos;
    private LayoutInflater inflater;
    private Context context;
    private View.OnClickListener onClickListener;

    public VideoAdapter(Context context, List<Map<String, Object>> data, View.OnClickListener onClickListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.videos = data;
        this.onClickListener = onClickListener;
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
            viewHolder.videoId = convertView.findViewById(R.id.video_id);
            viewHolder.videoCover = convertView.findViewById(R.id.video_cover);
            viewHolder.videoTitle = convertView.findViewById(R.id.video_title);
            viewHolder.videoPublishedAt = convertView.findViewById(R.id.video_published_at);
            viewHolder.videoCover.setOnClickListener(onClickListener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        Map<String, Object> video = videos.get(position);
        viewHolder.videoId.setText((String) video.get("id"));
        viewHolder.videoCover.setContentDescription((String) video.get("id"));
        viewHolder.videoTitle.setText((String) video.get("title"));
        viewHolder.videoPublishedAt.setText((String) video.get("publishedAt"));
        Picasso.with(context).load((String) video.get("cover")).into(viewHolder.videoCover);

        return convertView;
    }



    public class MyViewHolder {
        public TextView videoId;
        public ImageView videoCover;
        public TextView videoTitle;
        public TextView videoPublishedAt;
    }

    public void setVideos (List<Map<String, Object>> data) {
        this.videos = data;
    }

}
