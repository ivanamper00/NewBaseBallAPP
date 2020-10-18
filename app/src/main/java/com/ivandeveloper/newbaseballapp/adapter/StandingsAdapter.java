package com.ivandeveloper.newbaseballapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.islamkhsh.CardSliderViewPager;
import com.ivandeveloper.newbaseballapp.R;
import com.ivandeveloper.newbaseballapp.model.StandingsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder> {
    List<List<StandingsResponse>> standings;
    List<StandingsResponse> standing;
    Context context;
    public static class StandingsViewHolder extends RecyclerView.ViewHolder {
        TextView league;
        CardSliderViewPager cardSliderViewPager;
        public StandingsViewHolder(@NonNull View itemView) {
            super(itemView);
            league = itemView.findViewById(R.id.standings_league);
            cardSliderViewPager = itemView.findViewById(R.id.standings_list_card_recycler);
        }
    }
    public StandingsAdapter(Context context, List<List<StandingsResponse>> standings){
        this.standings = standings;
        this.context = context;
    }
    @NonNull
    @Override
    public StandingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standings_by_league_list,parent,false);
        return new StandingsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StandingsViewHolder holder, int position) {
        standing = standings.get(position);
        holder.league.setText(standing.get(position).getStage() + " - "+standing.get(position).getGroup().getName());
        StandingsListAdapter adapter = new StandingsListAdapter(context, standing);
        holder.cardSliderViewPager.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return standings.size();
    }
}
