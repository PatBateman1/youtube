package com.example.youtube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = new Intent(this, SearchActivity.class);
        Button button = new Button(this);
        button.setLayoutParams(new ConstraintLayout.LayoutParams(300, 180));
        button.setText("switch");
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_main);

        View.OnClickListener change = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(intent);

            }
        };
        button.setOnClickListener(change);
        layout.addView(button);
    }
}
