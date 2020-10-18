package com.ivandeveloper.newbaseballapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StandingsModel {
    @SerializedName("response")
    private List<List<StandingsResponse>> response = null;

    public StandingsModel(List<List<StandingsResponse>> response) {
        this.response = response;
    }

    public List<List<StandingsResponse>> getResponse() {
        return response;
    }
}
