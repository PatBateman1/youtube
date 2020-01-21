package com.example.youtube.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.youtube.R;
import com.example.youtube.adapters.VideoAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private String question = "mike mangini";

    private ListView videosList;
    private VideoAdapter adapter;
    private List<Map<String, Object>> dataSet = new ArrayList<>();



    public MainFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        videosList =  view.findViewById(R.id.lv_main);
        if (getArguments() != null) {
            question = getArguments().getString("question");
        }
        Log.d(TAG, "onCreateView: 5555555555555"+getArguments());
        Log.d(TAG, "onCreateView: 6666666666666"+question);
        initList();

        new HttpSearch(question).execute();
        return view;
    }

    private void initList() {
        adapter = new VideoAdapter(getActivity(), dataSet);
        videosList.setAdapter(adapter);
    }




    public class HttpSearch extends AsyncTask<Void, String, String> {

        private static final String TAG = "HttpSearch";

        private final static String API_KEY = "&key=AIzaSyBlGPQx1C9hk_xk0uA0JpRuSuvTdhpq3VY";
        private final static String urlFront = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&q=";
        private String question;


        public HttpSearch (String questions) {
            this.question = urlFront + questions.replaceAll(" ", "%20") + API_KEY;

        }


        @Override
        protected String doInBackground(Void... voids) {
            String url = question;
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);

            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                String json = EntityUtils.toString(httpEntity);

                return json;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    JSONObject json = new JSONObject(response);
                    dataSet = parseDataFromResponse(json);
                    adapter.setVideos(dataSet);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ArrayList<Map<String, Object>> parseDataFromResponse(JSONObject json) {
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        if (json.has("items")) {
            try {
                JSONArray jsonArray = json.getJSONArray("items");
                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject ith = jsonArray.getJSONObject(i);
                    JSONObject snippet = ith.getJSONObject("snippet");
                    JSONObject thumb = snippet.getJSONObject("thumbnails");
                    String title = snippet.getString("title");
                    String publishedAt = snippet.getString("publishedAt");
                    String cover = thumb.getJSONObject("high").getString("url");

                    Map<String, Object> map = new HashMap<>();
                    map.put("title", title);
                    map.put("publishedAt", publishedAt);
                    map.put("cover", cover);
                    data.add(map);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return data;
    }


}