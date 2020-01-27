package com.example.youtube.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class AddPlayList extends AsyncTask <Void, String, String> {
    private static final String TAG = "AddPlayList";

    private final String API = "http://morning-wave-70140.herokuapp.com/api/";
    private String videoName;
    private String url;
    private List<String> playList;

    public AddPlayList(String videoName, String id, List<String> playList) {
        this.url = API + id;
        this.videoName = videoName;
        this.playList = playList;
    }

    @Override
    protected String doInBackground(Void... voids) {
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
                if (json.has("url")) {
                    String stream = json.getString("url");

                    String item = videoName + "^" + stream;
                    playList.add(item);
                    Log.d(TAG, "onPostExecute: " + item);
                    Log.d(TAG, "onPostExecute: " + playList.size());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}
