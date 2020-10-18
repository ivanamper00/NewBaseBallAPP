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

import com.github.islamkhsh.CardSliderAdapter;
import com.ivandeveloper.newbaseballapp.R;
import com.ivandeveloper.newbaseballapp.model.StandingsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StandingsListAdapter extends CardSliderAdapter<StandingsListAdapter.StandingsListViewHolder> {
    List<StandingsResponse> standings;
    StandingsResponse standing;
    Context context;


    public static class StandingsListViewHolder extends RecyclerView.ViewHolder {
        TextView win;
        TextView Loss;
        TextView teamName;
        ImageView teamLogo;
        public StandingsListViewHolder(@NonNull View itemView) {
            super(itemView);
            win = itemView.findViewById(R.id.standings_team_win);
            Loss = itemView.findViewById(R.id.standings_team_loss);
            teamLogo = itemView.findViewById(R.id.standings_team_image);
            teamName = itemView.findViewById(R.id.standings_team_name);
        }
    }
    public StandingsListAdapter(Context context, List<StandingsResponse> standings){
        this.standings = standings;
        this.context = context;
    }
    @NonNull
    @Override
    public StandingsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standings_list,parent,false);
        return new StandingsListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void bindVH(StandingsListViewHolder holder, int position) {
        standing = standings.get(position);
        Picasso.get().load(standing.getTeam().getLogo()).into(holder.teamLogo);
        holder.teamName.setText(standing.getTeam().getName());
        holder.win.setText("W: " + standing.getGames().getWin().getTotal().toString());
        holder.Loss.setText("L: " + standing.getGames().getLose().getTotal().toString());

    }


    @Override
    public int getItemCount() {
        return standings.size();
    }
}
