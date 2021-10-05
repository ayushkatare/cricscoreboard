package com.example.cricscoreboard.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Over {
    String overId;
    List<Ball> ballList;
    Map<String, Player> playerWicket;
    int runScoredInOver;

    public Over(String overId) {
        this.overId = overId;
        this.ballList = new ArrayList<>();
        this.playerWicket = new HashMap<>();
        this.runScoredInOver = 0;
    }

    public String getOverId() {
        return overId;
    }

    public List<Ball> getBallList() {
        return ballList;
    }

    public Map<String, Player> getPlayerWicket() {
        return playerWicket;
    }

    public void setRunScoredInOver(int runs){
        this.runScoredInOver = runs;
    }
}
