package com.ivandeveloper.newbaseballapp.controller.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ivandeveloper.newbaseballapp.R;
import com.ivandeveloper.newbaseballapp.controller.GlobalController;
import com.ivandeveloper.newbaseballapp.controller.activity.mainFragments.GamesFragment;
import com.ivandeveloper.newbaseballapp.controller.activity.mainFragments.LeaguesFragment;
import com.ivandeveloper.newbaseballapp.controller.activity.mainFragments.TeamsFragment;
import com.ivandeveloper.newbaseballapp.controller.activity.mainFragments.StandingsFragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    GlobalController globalController;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FloatingActionButton fab;
    FloatingActionButton fabUpdates;
    FloatingActionButton fabGames;
    FloatingActionButton fabTeams;
    FloatingActionButton fabLeagues;
    CountDownTimer countDownTimer;
    TextView textView;
    boolean isFabOpen = false;
    HashMap<Integer, View> fabSelector;
    int flag;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        globalController = new GlobalController(MainActivity.this);
        flag = 1;
        fab = findViewById(R.id.fab_main);
        fabSelector = new HashMap<>();

        fabUpdates = findViewById(R.id.fab_updates);
        fabGames = findViewById(R.id.fab_games);
        fabTeams = findViewById(R.id.fab_teams);
        fabLeagues = findViewById(R.id.fab_leagues);
        textView = findViewById(R.id.main_title);

        fabSelector.put(1,fabUpdates);
        fabSelector.put(2,fabGames);
        fabSelector.put(3,fabTeams);
        fabSelector.put(4,fabLeagues);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
//        getSupportActionBar().setTitle("Umpire: Standings");
        textView.setText("Standings");
        fragmentTransaction.add(R.id.fab_frame_layout, new StandingsFragment());
        fragmentTransaction.commit();
        hider();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFabOpen){
                    showFabMenu();
                }else{
                    closeFabMenu();
                }
            }
        });

        fabUpdates.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
//                getSupportActionBar().setTitle("Umpire: Standings");
                textView.setText("Standings");
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fab_frame_layout, new StandingsFragment());
                fragmentTransaction.commit();
                flag = 1;
                closeFabMenu();
                hider();
            }
        });
        fabGames.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
//                getSupportActionBar().setTitle("Umpire: Games");
                textView.setText("Games");
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fab_frame_layout, new GamesFragment());
                fragmentTransaction.commit();
                flag = 2;
                closeFabMenu();
                hider();
            }
        });
        fabTeams.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
//                getSupportActionBar().setTitle("Umpire: Statistics");
                textView.setText("Statistics");
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fab_frame_layout, new TeamsFragment());
                fragmentTransaction.commit();
                flag = 3;
                closeFabMenu();
                hider();
            }
        });
        fabLeagues.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
//                getSupportActionBar().setTitle("Umpire: Country Leagues");
                textView.setText("Country Leagues");
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fab_frame_layout, new LeaguesFragment());
                fragmentTransaction.commit();
                flag = 4;
                closeFabMenu();
                hider();
            }
        });
    }


    public void showFabMenu(){
        isFabOpen = true;
        fabUpdates.animate().translationY(-getResources().getDimension(R.dimen.standard_205));
        fabGames.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
        fabTeams.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fabLeagues.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
    }
    public void closeFabMenu(){
        isFabOpen = false;
        fabUpdates.animate().translationY(0);
        fabGames.animate().translationY(0);
        fabTeams.animate().translationY(0);
        fabLeagues.animate().translationY(0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void hider(){
        fabSelector.get(flag).setClickable(false);
        fabSelector.get(flag).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.disabled)));
        for(int i = 1; i < fabSelector.size()+1; i++ ){
            if(i != flag){
                fabSelector.get(i).setClickable(true);
                fabSelector.get(i).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            }
        }
    }
}