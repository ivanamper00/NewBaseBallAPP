package com.ivandeveloper.newbaseballapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.ivandeveloper.newbaseballapp.R;
import com.ivandeveloper.newbaseballapp.SplashScreen;
import com.ivandeveloper.newbaseballapp.controller.GlobalController;
import com.ivandeveloper.newbaseballapp.controller.activity.MainActivity;
import com.ivandeveloper.newbaseballapp.model.LeagueModel;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.LeaguesViewHolder> {
    Context context;
    List<LeagueModel.Response> leagueModelList;
    LeagueModel.Response leagueModel;
    GlobalController globalController;
    Activity activity;
    CountDownTimer countDownTimer;
    public class LeaguesViewHolder extends RecyclerView.ViewHolder {
        TextView country;
        TextView league;
        ImageView flag;
        public LeaguesViewHolder(@NonNull View itemView) {
            super(itemView);
            league = itemView.findViewById(R.id.league_id);
            country = itemView.findViewById(R.id.league_country_name);
            flag = itemView.findViewById(R.id.league_flag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    globalController.startLoading();
                    loop();
                    globalController.clearContents();
                    globalController.setDefaultLeague(Integer.parseInt(league.getText().toString()));
                    globalController.saveGames(globalController.getCurrentSeason(),globalController.getDefaultLeague());
                    globalController.saveStandings();
                    globalController.saveLeagues();
                    globalController.saveTeams();

                }
            });
        }
    }
    public LeaguesAdapter(Context context, List<LeagueModel.Response> leagueModelList, GlobalController globalController){
        this.context = context;
        this.leagueModelList = leagueModelList;
        this.activity = (Activity) context;
        this.globalController = globalController;
    }

    @NonNull
    @Override
    public LeaguesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.league_list,parent,false);
        return new LeaguesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LeaguesViewHolder holder, int position) {
        leagueModel = leagueModelList.get(position);

        holder.country.setText(leagueModel.getCountry().getName() + "\n(" + leagueModel.getName() + ")");
        holder.league.setText(Integer.toString(leagueModel.getId()));
        if(leagueModel.getCountry().getFlag().substring(leagueModel.getCountry().getFlag().length()-3).contains("svg")){
            SvgLoader.pluck()
                    .with(activity)
                    .load(leagueModel.getCountry().getFlag(), holder.flag);
        }else{
            Picasso.get().load(leagueModel.getCountry().getFlag()).into(holder.flag);
        }

    }

    @Override
    public int getItemCount() {
        return leagueModelList.size();
    }
    public void loop(){
        loadIntent();
    }

    public void loadIntent(){
        countDownTimer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(globalController.retrieveGames() == null ||
                        globalController.retrieveLeagues() == null ||
                        globalController.retrieveStandings() == null ||
                        globalController.retrieveTeams() == null){
                    loop();
                }else{
                    globalController.stopLoading();
                    globalController.NextIntent(MainActivity.class);
                }

            }
        }.start();
    }


}
