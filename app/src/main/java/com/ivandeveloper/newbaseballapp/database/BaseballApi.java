package com.ivandeveloper.newbaseballapp.database;

import com.ivandeveloper.newbaseballapp.model.GameModelResponse;
import com.ivandeveloper.newbaseballapp.model.GamesModel;
import com.ivandeveloper.newbaseballapp.model.LeagueModel;
import com.ivandeveloper.newbaseballapp.model.StandingsModel;
import com.ivandeveloper.newbaseballapp.model.TeamStatisticsModel;
import com.ivandeveloper.newbaseballapp.model.TeamsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface BaseballApi {

    String BASE_URL = "https://api-baseball.p.rapidapi.com/";

//    @GET("timezone")
//    Call<TimeZoneModel> getTimeZone();
    @Headers({"x-rapidapi-host: api-baseball.p.rapidapi.com",
        "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
        "useQueryString: true"})
    @GET("games")
    Call<GamesModel> getGames(@Query("season") String season, @Query("league") int league);

    @Headers({"x-rapidapi-host: api-baseball.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("standings")
    Call<StandingsModel> getStandings(@Query("season") String season, @Query("league") int league);


    @Headers({"x-rapidapi-host: api-baseball.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("games")
    Call<GamesModel> getGamesbyId(@Query("id") String id);


    @Headers({"x-rapidapi-host: api-baseball.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("games/h2h")
    Call<GamesModel> getGamesHTH(@Query("h2h") String i);

    @Headers({"x-rapidapi-host: api-baseball.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("leagues")
    Call<LeagueModel> getLeagues();

    @Headers({"x-rapidapi-host: api-baseball.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("teams")
    Call<TeamsModel> getTeams(@Query("season") String season, @Query("league") int league);


    @Headers({"x-rapidapi-host: api-baseball.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("teams/statistics")
    Call<TeamStatisticsModel> getStatistics(@Query("league") int l, @Query("season") String y, @Query("team") String t);

}
