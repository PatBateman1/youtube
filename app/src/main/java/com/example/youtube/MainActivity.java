package com.example.youtube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.youtube.adapters.ViewPagerAdapter;
import com.example.youtube.fragments.MainFragment;
import com.example.youtube.fragments.PlayListFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<String> playList = new ArrayList<>();

    private String question = "mike mangini";

    private FragmentManager fragmentManager;
    private MainFragment mainFragment;

    private PlayListFragment playListFragment;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private List<Fragment> fragments;



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

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);

        Bundle bundle1 = new Bundle();
        bundle1.putString("question", question);
        bundle1.putStringArrayList("playList", (ArrayList<String>) playList);

        Bundle bundle2 = new Bundle();
        bundle2.putStringArrayList("playList", (ArrayList<String>) playList);

        mainFragment = new MainFragment();
        mainFragment.setArguments(bundle1);

        playListFragment = new PlayListFragment();
        playListFragment.setArguments(bundle2);

        fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        fragments.add(mainFragment);
        fragments.add(playListFragment);
        //fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.add(R.id.container, mainFragment);
        //fragmentTransaction.commit();

        adapter = new ViewPagerAdapter(fragmentManager, fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
