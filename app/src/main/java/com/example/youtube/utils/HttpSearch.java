package com.example.youtube.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HttpSearch extends AsyncTask <Void, String, String> {

    private final static String API_KEY = "&key=AIzaSyBlGPQx1C9hk_xk0uA0JpRuSuvTdhpq3VY";
    private final static String urlFront = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=";
    private String question;

    public HttpSearch (String question) {
        this.question = urlFront + question.replaceAll(" ", "%20") + API_KEY;
    }


    @Override
    protected String doInBackground(Void... voids) {
        String url = urlFront + question + API_KEY;

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpClient.getConnectionManager().getSchemeRegistry().register( new Scheme("https", SSLSocketFactory.getSocketFactory(), 443) );
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
                JSONObject jsonObject = new JSONObject(response);
                Log.d("api", "onPostExecute: " + jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}




