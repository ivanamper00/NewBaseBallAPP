package com.ivandeveloper.newbaseballapp.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ivandeveloper.newbaseballapp.database.BaseballApi;
import com.ivandeveloper.newbaseballapp.model.GameModelResponse;
import com.ivandeveloper.newbaseballapp.model.GamesModel;
import com.ivandeveloper.newbaseballapp.model.LeagueModel;
import com.ivandeveloper.newbaseballapp.model.StandingsModel;
import com.ivandeveloper.newbaseballapp.model.StandingsResponse;
import com.ivandeveloper.newbaseballapp.model.TeamsModel;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class GlobalController {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String PLAYERS = "Players";
    public static final String PLAYERS_FREE_AGENT = "Players_free_agent";
    public static final String UPDATES = "updates";
    public static final String GAMES_SEASON_LEAGUE = "games";
    public static final String TEAMS_SEASON_LEAGUE = "teams";
    public static final String LEAGUE_SEASON = "league";
    public static final String STANDING_SEASON_LEAGUE = "standings";
    public static final String CURRENT_LEAGUE = "current_league";
    public static final String CURRENT_SEASON = "Current season";

    ProgressDialog pdLoading;
    Context context;
    Intent intent;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    CountDownTimer countDownTimer;

    @SuppressLint("CommitPrefEdits")
    public GlobalController(Context context){
        this.pdLoading = new ProgressDialog(context);
        this.context = context;
        this.activity = (Activity) context;
        this.intent = activity.getIntent();
        this.sharedPreferences = this.context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void clearContents(){
        this.editor.clear();
        this.editor.commit();
    }

    public int getDefaultLeague(){
        return sharedPreferences.getInt(CURRENT_LEAGUE,1);
    }
    public void setDefaultLeague(int league){
        editor.putInt(CURRENT_LEAGUE ,league);
        editor.commit();
    }
    public String getDefaultSeason(){
        return sharedPreferences.getString(CURRENT_SEASON,getCurrentSeason());
    }
    public void setDefaultSeason(String season){
        editor.putString(CURRENT_SEASON ,season);
        editor.commit();
    }

    //Next Intent w/ Data Function
    public void NextIntent(Class toClass, String data) {
        Intent intent = new Intent(context, toClass);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }
    //Next Intent w/o Data Function
    public void NextIntent(Class toClass) {
        NextIntent(toClass, "");
    }

    //Retrofit Builder Function
    public BaseballApi getRetrofitBuilder() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseballApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BaseballApi api = retrofit.create(BaseballApi.class);
        return api;
    }

    // Loading Function
    public void startLoading(String text){
        pdLoading.setMessage("\t" + text);
        pdLoading.setCancelable(false);
        pdLoading.show();
    }
    public void startLoading(){
        startLoading("Please Wait...");
    }
    public void stopLoading(){
        pdLoading.dismiss();
    }

    //Getting Year/Season
    @SuppressLint("SimpleDateFormat")
    public String getCurrentSeason(){
        Calendar calendar;
        SimpleDateFormat simpleDateFormat;
        String date;
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy");
        date = simpleDateFormat.format(calendar.getTime());
        return date;
    }

    public <T> void saveData(String calling, List<T> object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(calling ,json);
        editor.commit();
    }

    public List<GameModelResponse> retrieveGames(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(GAMES_SEASON_LEAGUE,"");
        Type type = new TypeToken<List<GameModelResponse>>(){}.getType();
        List<GameModelResponse> objects = gson.fromJson(json, type);
        return objects;
    }
    public List<TeamsModel.Response> retrieveTeams(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(TEAMS_SEASON_LEAGUE,"");
        Type type = new TypeToken<List<TeamsModel.Response>>(){}.getType();
        List<TeamsModel.Response> objects = gson.fromJson(json, type);
        return objects;
    }

    public List<List<StandingsResponse>> retrieveStandings(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(STANDING_SEASON_LEAGUE,"");
        Type type = new TypeToken<List<List<StandingsResponse>>>(){}.getType();
        List<List<StandingsResponse>> objects = gson.fromJson(json, type);
        return objects;
    }
    public List<LeagueModel.Response> retrieveLeagues(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LEAGUE_SEASON,"");
        Type type = new TypeToken<List<LeagueModel.Response>>(){}.getType();
        List<LeagueModel.Response> objects = gson.fromJson(json, type);
        return objects;
    }

    public void saveStandings(String season, int league){
        Call<StandingsModel> call = getRetrofitBuilder().getStandings(season,league);

        call.enqueue(new Callback<StandingsModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<StandingsModel> call, retrofit2.Response<StandingsModel> response) {
                StandingsModel standings = response.body();
                List<List<StandingsResponse>> standingList = standings.getResponse();
                saveData(STANDING_SEASON_LEAGUE, standingList);
//                setSuccess(PLAYERS,"Players..");
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Standings Saved!");
            }
            @Override
            public void onFailure(Call<StandingsModel> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
//                setErrors(PLAYERS,t.getMessage());
            }
        });
    }

    public void saveGames(String season, int league){

        Call<GamesModel> call = getRetrofitBuilder().getGames(season,league);

        call.enqueue(new Callback<GamesModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<GamesModel> call, retrofit2.Response<GamesModel> response) {
                GamesModel gamesModel = response.body();
                List<GameModelResponse> gameResponse = gamesModel.getResponse();
                saveData(GAMES_SEASON_LEAGUE, gameResponse);
//                setSuccess(PLAYERS,"Players..");
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Games Saved!");
            }
            @Override
            public void onFailure(Call<GamesModel> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
//                setErrors(PLAYERS,t.getMessage());
            }
        });
    }


    public void saveTeams(String season, int league){

        Call<TeamsModel> call = getRetrofitBuilder().getTeams(season,league);

        call.enqueue(new Callback<TeamsModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<TeamsModel> call, retrofit2.Response<TeamsModel> response) {
                TeamsModel teamsModel = response.body();
                List<TeamsModel.Response> teamsResponse = teamsModel.getResponse();
                saveData(TEAMS_SEASON_LEAGUE, teamsResponse);
//                setSuccess(PLAYERS,"Players..");
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Teams Saved!");
            }
            @Override
            public void onFailure(Call<TeamsModel> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
//                setErrors(PLAYERS,t.getMessage());
            }
        });
    }
    public void saveLeagues(){

        Call<LeagueModel> call = getRetrofitBuilder().getLeagues();

        call.enqueue(new Callback<LeagueModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<LeagueModel> call, retrofit2.Response<LeagueModel> response) {
                LeagueModel leagues = response.body();
                List<LeagueModel.Response> leaguesResponse = leagues.getResponse();
                saveData(LEAGUE_SEASON, leaguesResponse);
//                setSuccess(PLAYERS,"Players..");
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Leagues Saved!");
            }
            @Override
            public void onFailure(Call<LeagueModel> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
//                setErrors(PLAYERS,t.getMessage());
            }
        });
    }

    public void saveGames(){
        saveGames(getDefaultSeason(),getDefaultLeague());
    }
    public void saveTeams(){
        saveTeams(getDefaultSeason(),getDefaultLeague());
    }
    public void saveStandings(){
        saveStandings(getDefaultSeason(),getDefaultLeague());
    }

    public void loop(boolean flag){
        if(flag){
            counterLoop();
        }
    }

    public void counterLoop(){
        countDownTimer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                loop(true);
            }
        }.start();
    }






    // Loading Function

//
//    public void checkingConnection(){
//        startLoading("Checking Connection...");
//        countDownTimer = new CountDownTimer(3000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                if (isConnected()){
//                    stopLoading();
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                stopLoading();
//                netChecker();
//            }
//        }.start();
//    }

    //Connection Checker Function
//    public boolean isConnected() {
//        boolean connected = false;
////        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
//        //we are connected to a network
////        connected = connectivityManager;
////                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
////                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
//
//
//
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
//        if (activeNetwork != null) {
//            // connected to the internet
//            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
//                // connected to wifi
//            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//                // connected to mobile data
//            }
//        } else {
//            // not connected to the internet
//        }
//        return connected;
//    }

//    public void netChecker(){
//        if(!isConnected()){
//            noInternetDialog();
//        }
//    }

//    public void noInternetDialog(){
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
//        alertDialog.setCancelable(false);
//        alertDialog.setMessage("\tDisconnected to Network");
//        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finishIntent();
//            }
//        });
//        alertDialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//
//                checkingConnection();
//            }
//        });
//        AlertDialog alert = alertDialog.create();
//        alert.show();
//    }



}
