package com.example.cricscoreboard.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {
    String teamId;
    List<Player> playerList;
    int runScoredByTeam;
    Map<String, Player> playerMap;

    public Team(String teamId) {
        this.teamId = teamId;
        this.playerList = new ArrayList<>();
        this.runScoredByTeam = 0;
        this.playerMap = new HashMap<>();
    }

    public Map<String, Player> getPlayerMap() {
        return playerMap;
    }

    public void addPlayerToTeam(Player player){
        this.playerList.add(player);
        this.playerMap.put(player.getName(), player);
    }

    public void setRunScoredByTeam(int run){
        this.runScoredByTeam = run;
    }

    public String getTeamId() {
        return teamId;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public int getRunScoredByTeam() {
        return runScoredByTeam;
    }
}
