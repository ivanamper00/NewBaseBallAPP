package com.ivandeveloper.newbaseballapp.model;

import com.google.gson.annotations.SerializedName;

public class LeaguesModelResponse {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("logo")
    private String logo;
    @SerializedName("country")
    private CountryModel country;

        public LeaguesModelResponse(Integer id, String name, String type, String logo, CountryModel country) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.logo = logo;
            this.country = country;
        }

    public Integer getId() {
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

    public CountryModel getCountry() {
        return country;
    }
}
