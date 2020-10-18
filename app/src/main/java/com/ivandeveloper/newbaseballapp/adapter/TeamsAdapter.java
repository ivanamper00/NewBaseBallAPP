package com.ivandeveloper.newbaseballapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.islamkhsh.CardSliderAdapter;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.ivandeveloper.newbaseballapp.R;
import com.ivandeveloper.newbaseballapp.model.StandingsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamsAdapter extends CardSliderAdapter<TeamsAdapter.TeamsViewHolder> {

    List<List<StandingsResponse>> statisticsTeams;
    List<StandingsResponse> statsTeams;
    StandingsResponse stats;
    Context context;
    public class TeamsViewHolder extends RecyclerView.ViewHolder {
        ImageView teamsLogo;
        TextView stage;
        TextView group;
        TextView teamName;
        TextView gamesPlayed;
        TextView gamesHome;
        TextView gamesAway;
        TextView fors;
        TextView against;
        public TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            teamsLogo = itemView.findViewById(R.id.teams_fragment_team_image);
            teamName = itemView.findViewById(R.id.teams_fragment_team_name);
            gamesPlayed = itemView.findViewById(R.id.teams_fragment_games_played);
            gamesHome = itemView.findViewById(R.id.teams_fragment_games_home);
            gamesAway = itemView.findViewById(R.id.teams_fragment_games_away);
            stage = itemView.findViewById(R.id.teams_fragment_stage);
            group = itemView.findViewById(R.id.teams_fragment_group);
            fors = itemView.findViewById(R.id.teams_fragment_for);
            against = itemView.findViewById(R.id.teams_fragment_against);
        }
    }
    public TeamsAdapter(Context context, List<List<StandingsResponse>> statisticsTeams) {
        this.statisticsTeams = statisticsTeams;
        statsTeams = statisticsTeams.get(0);
        this.context = context;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void bindVH(TeamsAdapter.TeamsViewHolder holder, int i) {

        stats = statsTeams.get(i);
        Picasso.get().load(stats.getTeam().getLogo()).into(holder.teamsLogo);
        holder.teamName.setText(stats.getTeam().getName());
        holder.gamesPlayed.setText(stats.getGames().getPlayed().toString());
        holder.gamesHome.setText(stats.getGames().getWin().getTotal().toString() + "(" + converted(stats.getGames().getWin().getPercentage()) +"%)");
        holder.gamesAway.setText(stats.getGames().getLose().getTotal().toString() + "(" + converted(stats.getGames().getLose().getPercentage()) +"%)");
        holder.fors.setText(stats.getPoints().get_for().toString());
        holder.stage.setText(stats.getStage());
        holder.group.setText(stats.getGroup().getName());
        holder.against.setText(stats.getPoints().getAgainst().toString());

    }

    @NonNull
    @Override
    public TeamsAdapter.TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_list,parent,false);
        return new TeamsAdapter.TeamsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return statsTeams.size();
    }

    public String converted(String number){
        double newNumber = Double.parseDouble(number);
        if(Double.parseDouble(number) < 1){
            newNumber = Double.parseDouble(number.replace("0.",""));
        }
        return Double.toString(newNumber/10);
    }
}
