package com.example.youtube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = new Intent(this, YoutubeActivity.class);
        Button button = new Button(this);
        button.setLayoutParams(new ConstraintLayout.LayoutParams(300, 80));
        button.setText("switch");
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.activity_main);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        };
        button.setOnClickListener(click);
        layout.addView(button);
    }
}
