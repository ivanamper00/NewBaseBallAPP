package com.ivandeveloper.newbaseballapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ivandeveloper.newbaseballapp.R;
import com.ivandeveloper.newbaseballapp.model.GameModelResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GamesViewHolder> {
    List<GameModelResponse> gameList;
    GameModelResponse game;
    Context context;
    public static class GamesViewHolder extends RecyclerView.ViewHolder{
        TextView homeScore;
        TextView dateTime;
        TextView awayScore;
        TextView homeTeam;
        TextView awayTeam;
        ImageView homeLogo;
        ImageView awayLogo;
        public GamesViewHolder(@NonNull View itemView) {
            super(itemView);
            homeScore = itemView.findViewById(R.id.games_fragment_score_home);
            awayScore = itemView.findViewById(R.id.games_fragment_score_away);
            homeTeam = itemView.findViewById(R.id.games_fragment_team_name_home);
            awayTeam = itemView.findViewById(R.id.games_fragment_team_name_away);
            homeLogo = itemView.findViewById(R.id.games_fragment_home_team);
            awayLogo = itemView.findViewById(R.id.games_fragment_away_team);
            dateTime = itemView.findViewById(R.id.games_fragment_date);
        }
    }
    public GamesAdapter(Context context, List<GameModelResponse> gameList){
        this.gameList = gameList;
        this.context = context;
    }

    @NonNull
    @Override
    public GamesAdapter.GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list,parent,false);
        return new GamesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GamesAdapter.GamesViewHolder holder, int position) {
        game = gameList.get(position);
        holder.dateTime.setText(game.getDate().substring(0,10) + " " + game.getTime());
        holder.homeScore.setText(Integer.toString(game.getScores().getHome().getTotal()));
        holder.awayScore.setText(Integer.toString(game.getScores().getAway().getTotal()));
        holder.homeTeam.setText(game.getTeams().getHome().getName());
        holder.awayTeam.setText(game.getTeams().getAway().getName());
        Picasso.get().load(game.getTeams().getHome().getLogo()).into(holder.homeLogo);
        Picasso.get().load(game.getTeams().getAway().getLogo()).into(holder.awayLogo);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

}
