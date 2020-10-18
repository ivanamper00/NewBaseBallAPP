package com.ivandeveloper.newbaseballapp.model;

import com.google.gson.annotations.SerializedName;

public class StandingsResponse {
    @SerializedName("position")
    private Integer position;
    @SerializedName("stage")
    private String stage;
    @SerializedName("group")
    private Group group;
    @SerializedName("team")
    private TeamsModel.Response team;
    @SerializedName("league")
    private LeagueModel league;
    @SerializedName("country")
    private CountryModel country;
    @SerializedName("games")
    private Games games;
    @SerializedName("points")
    private Points points;
    @SerializedName("form")
    private String form;
    @SerializedName("description")
    private String description;

    public StandingsResponse(Integer position, String stage, Group group, TeamsModel.Response team, LeagueModel league, CountryModel country, Games games, Points points, String form, String description) {
        this.position = position;
        this.stage = stage;
        this.group = group;
        this.team = team;
        this.league = league;
        this.country = country;
        this.games = games;
        this.points = points;
        this.form = form;
        this.description = description;
    }

    public static class Group{
            @SerializedName("name")
            private String name;

            public Group(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
        }
        public static class Games{
            @SerializedName("played")
            private Integer played;
            @SerializedName("win")
            private WinLose win;
            @SerializedName("lose")
            private WinLose lose;

                public Games(Integer played, WinLose win, WinLose lose) {
                    this.played = played;
                    this.win = win;
                    this.lose = lose;
                }

                public static class WinLose{
                    @SerializedName("total")
                    private Integer total;
                    @SerializedName("percentage")
                    private String percentage;

                    public WinLose(Integer total, String percentage) {
                        this.total = total;
                        this.percentage = percentage;
                    }

                    public Integer getTotal() {
                        return total;
                    }

                    public String getPercentage() {
                        return percentage;
                    }
                }

            public Integer getPlayed() {
                return played;
            }

            public WinLose getWin() {
                return win;
            }

            public WinLose getLose() {
                return lose;
            }
        }
        public static class Points{
            @SerializedName("for")
            private Integer _for;
            @SerializedName("against")
            private Integer against;

            public Points(Integer _for, Integer against) {
                this._for = _for;
                this.against = against;
            }

            public Integer get_for() {
                return _for;
            }

            public Integer getAgainst() {
                return against;
            }
        }

    public Integer getPosition() {
        return position;
    }

    public String getStage() {
        return stage;
    }

    public Group getGroup() {
        return group;
    }

    public TeamsModel.Response getTeam() {
        return team;
    }

    public LeagueModel getLeague() {
        return league;
    }

    public CountryModel getCountry() {
        return country;
    }

    public Games getGames() {
        return games;
    }

    public Points getPoints() {
        return points;
    }

    public String getForm() {
        return form;
    }

    public String getDescription() {
        return description;
    }
}
