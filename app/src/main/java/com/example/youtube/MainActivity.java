package com.example.youtube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.youtube.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String question = "mike mangini";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private MainFragment mainFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = new Intent(this, SearchActivity.class);
        ImageView searchButton = findViewById(R.id.search_button);
        View.OnClickListener change = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(intent);

            }
        };
        searchButton.setOnClickListener(change);

        Intent search = getIntent();
        if (search != null) {
            if (search.hasExtra("question")) {
                question = search.getStringExtra("question");
            }

        }
        Bundle bundle = new Bundle();
        bundle.putString("question", question);
        mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);


        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_main, mainFragment);

        fragmentTransaction.commit();
    }


}
