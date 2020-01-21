package com.example.youtube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtra("question", searchContent);
                startActivity(intent);
            }
        };
        searchButton.setOnClickListener(search);
    }
}
