package com.ivandeveloper.newbaseballapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeagueModel {
    @SerializedName("response")
    private List<Response> response;

    public LeagueModel(List<Response> response) {
        this.response = response;
    }

    public class Response {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("type")
        private String type;
        @SerializedName("logo")
        private String logo;
        @SerializedName("country")
        private CountryModel country;
        @SerializedName("season")
        private Integer season;

        public Response(int id, String name, String type, String logo, CountryModel country, Integer season) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.logo = logo;
            this.country = country;
            this.season = season;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getLogo() {
            return logo;
        }

        public Integer getSeason() {
            return season;
        }

        public CountryModel getCountry() {
            return country;
        }
    }

    public List<Response> getResponse() {
        return response;
    }
}
