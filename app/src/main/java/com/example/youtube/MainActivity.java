package com.example.youtube;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.youtube.adapters.PlayListAdapter;
import com.example.youtube.adapters.ViewPagerAdapter;
import com.example.youtube.fragments.MainFragment;
import com.example.youtube.fragments.PlayListFragment;
import com.example.youtube.interfaces.IPlayerControl;
import com.example.youtube.interfaces.IPlayerViewControl;
import com.example.youtube.services.PlayVideoService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static List<String> playList;

    private String question = "mike mangini";

    private FragmentManager fragmentManager;
    private MainFragment mainFragment;
    private PlayListFragment playListFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private List<Fragment> fragments;
    private PlayListAdapter playListAdapter;
    private Button play;
    private Button next;
    private SeekBar seekBar;
    private PlayerConnection mPlayerConnection;
    private IPlayerControl iPlayerControl;
    private boolean isUserTouchProgress = false;


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

        initAdapter();
        mainFragment = new MainFragment();
        mainFragment.setArguments(bundle1);

        playListFragment = new PlayListFragment();
        playListFragment.setArguments(bundle2);
        playListFragment.setAdapter(playListAdapter);
        mainFragment.setPlayListAdapter(playListAdapter);

        fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        fragments.add(mainFragment);
        fragments.add(playListFragment);

        adapter = new ViewPagerAdapter(fragmentManager, fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        initPlayList();

        initView();

        initEvent();

        initService();

        initBindService();
    }

    private void initPlayList() {
        if (playList == null) {
            playList = new ArrayList<>();
        }
    }

    private void initAdapter() {
        playListAdapter = new PlayListAdapter(this);

    }

    private void initView() {
        play = findViewById(R.id.play);
        next = findViewById(R.id.next);
        seekBar = findViewById(R.id.seek_bar);
    }

    private void initEvent() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isUserTouchProgress = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isUserTouchProgress = false;
                if (iPlayerControl != null) {
                    iPlayerControl.seekTo(seekBar.getProgress());
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iPlayerControl != null) {
                    iPlayerControl.playOrPause();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iPlayerControl != null) {
                    iPlayerControl.next();
                }
            }
        });
    }

    private void initService() {
        Intent serviceIntent = new Intent(this, PlayVideoService.class);
        //serviceIntent.putStringArrayListExtra("playList", (ArrayList<String>) playList);
        startService(serviceIntent);
    }

    private void initBindService() {
        Intent intent = new Intent(this, PlayVideoService.class);
        if (mPlayerConnection == null) {
            mPlayerConnection = new PlayerConnection();
        }

        bindService(intent, mPlayerConnection, BIND_AUTO_CREATE);
    }

    private class PlayerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iPlayerControl = (IPlayerControl) service;
            iPlayerControl.setViewController(mIPlayerViewControl);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iPlayerControl = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayerConnection != null) {
            iPlayerControl.removeViewController();
            unbindService(mPlayerConnection);
        }
    }

    private IPlayerViewControl mIPlayerViewControl = new IPlayerViewControl() {
        @Override
        public void onStateChange(int state) {
            switch (state) {
                case IPlayerControl.PLAYER_STATE_PLAY:
                    play.setText("pause");
                    break;
                case IPlayerControl.PLAYER_STATE_PAUSE:
                    play.setText("play");
                    break;
                case IPlayerControl.PLAYER_STATE_STOP:
                    break;
            }
        }

        @Override
        public void onSeekChange(int seek) {
            if(!isUserTouchProgress) {
                seekBar.setProgress(seek);
            }
        }
    };
}
