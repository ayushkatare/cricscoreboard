package com.example.cricscoreboard;

import com.example.cricscoreboard.model.Team;
import com.example.cricscoreboard.service.MatchService;
import com.example.cricscoreboard.service.TeamService;

import java.util.Scanner;

public class Driver {

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        System.out.println("Enter no of player in each team");
        int noOfPlayers = in.nextInt();
        System.out.println("Enter no of overs");
        int noOfOvers = in.nextInt();
        TeamService teamService = new TeamService();
        Team t1 = teamService.createTeamOfNPlayers("T1", noOfPlayers);
        Team t2 = teamService.createTeamOfNPlayers("T2", noOfPlayers);

        MatchService matchService = new MatchService(t1, t2, noOfOvers, noOfPlayers);
        matchService.beginMatch();
    }
}
