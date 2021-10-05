package com.example.springbootgradledefaulttemplate.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    List<Over> overList;
    int runsScoredByPlayer;
    int noOfSixByPlayer;
    int noOfFourByPlayer;
    int noOfBallFacedByPlayer;

    public Player(String name){
        this.name = name;
        this.overList = new ArrayList<>();
        this.runsScoredByPlayer = 0;
        this.noOfSixByPlayer = 0;
        this.noOfFourByPlayer = 0;
        this.noOfBallFacedByPlayer = 0;
    }

    public String getName() {
        return name;
    }

    public List<Over> getOverList() {
        return overList;
    }

    public int getRunsScoredByPlayer() {
        return runsScoredByPlayer;
    }

    public void setRunsScoredByPlayer(int runsScored) {
        this.runsScoredByPlayer = runsScored;
    }

    public int getNoOfSixByPlayer() {
        return noOfSixByPlayer;
    }

    public void setNoOfSixByPlayer(int noOfSix) {
        this.noOfSixByPlayer = noOfSix;
    }

    public int getNoOfFourByPlayer() {
        return noOfFourByPlayer;
    }

    public void setNoOfFourByPlayer(int noOfFour) {
        this.noOfFourByPlayer = noOfFour;
    }

    public int getNoOfBallFacedByPlayer() {
        return noOfBallFacedByPlayer;
    }

    public void setNoOfBallFacedByPlayer(int noOfBallFaced) {
        this.noOfBallFacedByPlayer = noOfBallFaced;
    }
}
