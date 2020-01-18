package com.example.youtube;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtube.utils.HttpSearch;

public class SearchActivity extends AppCompatActivity {


    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button searchButton = findViewById(R.id.search_button);
        View.OnClickListener search = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText search = findViewById(R.id.search_content);
                String searchContent = search.getText().toString();
                Log.d(TAG, "search: " + searchContent);
                new HttpSearch(searchContent).execute();

            }
        };
        searchButton.setOnClickListener(search);
    }
}
