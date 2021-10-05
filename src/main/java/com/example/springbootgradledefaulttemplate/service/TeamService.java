package com.example.springbootgradledefaulttemplate.service;

import com.example.springbootgradledefaulttemplate.model.Player;
import com.example.springbootgradledefaulttemplate.model.Team;

public class TeamService {

    public Team createTeamOfNPlayers(String name ,int N){
        Team team = new Team(name);

        for(int i=0;i<N;i++){
            team.addPlayerToTeam(new Player(name+'p'+i));
        }

        printAllPlayers(team);
        return team;
    }

    public void printAllPlayers(Team team){
        System.out.println("Team : "+team.getTeamId());
        for(Player player : team.getPlayerList()){
            System.out.println("Player : "+player.getName());
        }
    }
}
