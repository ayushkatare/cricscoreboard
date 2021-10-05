package com.example.springbootgradledefaulttemplate.service;

import com.example.springbootgradledefaulttemplate.model.Ball;
import com.example.springbootgradledefaulttemplate.model.Over;
import com.example.springbootgradledefaulttemplate.model.Player;
import com.example.springbootgradledefaulttemplate.model.Team;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MatchService {

    Team first;
    Team second;
    int noOfOvers;
    Queue<String> battingOrder;
    Queue<String> bowlingOrder;
    Scanner sc;
    int noOfPlayersEachTeam;
    Boolean firstInning,secondInning;

    public MatchService(Team t1, Team t2, int noOfOvers, int noOfPlayersEachTeam){
        this.first = t1;
        this.second = t2;
        this.noOfOvers = noOfOvers;
        this.sc = new Scanner(System.in);
        this.noOfPlayersEachTeam = noOfPlayersEachTeam;
        this.battingOrder = new LinkedList<>();
        this.firstInning = false;
        this.secondInning = false;
    }

    private void getBattingOrder(){
        this.battingOrder.clear();
        for(int i=0;i<this.noOfPlayersEachTeam;i++){
            String input = sc.nextLine();
            this.battingOrder.add(input.trim());
        }
    }

    private boolean isTeamAllOut(String player){
        return this.battingOrder.isEmpty() && player.equals("NONE");
    }

    private boolean isAllOverCompleted(int over){
        return over==this.noOfOvers;
    }

    private boolean checkWicket(String input){
        return input.equals("W");
    }

    private boolean checkNoBallOrWide(String input){
        return input.equals("Wd") || input.equals("Nb");
    }

    private void printScoreBoard(Team team, int wickets, Over over){
        for(Player player : team.getPlayerList()){
            System.out.println("Name->"+player.getName()+" Score->"+player.getRunsScoredByPlayer()+
                    " 4s->"+player.getNoOfFourByPlayer()+" 6s->"+player.getNoOfSixByPlayer()+" Balls->"+player.getNoOfBallFacedByPlayer());
        }

        System.out.println("Over : "+over.getOverId());
        int count = 1;
        for(Ball ball : over.getBallList()){
            System.out.print(count+"->"+ball.name()+",");
            count++;
        }
        System.out.println();
        System.out.println("Total Runs : "+team.getRunScoredByTeam()+" Wickets : "+wickets);
    }

    private void runScoredByPlayer(Player player, int run){
        if(run==4){
            player.setNoOfFourByPlayer(player.getNoOfFourByPlayer()+1);
        }else if(run==6){
            player.setNoOfSixByPlayer(player.getNoOfSixByPlayer()+1);
        }

        player.setRunsScoredByPlayer(player.getRunsScoredByPlayer()+run);
        player.setNoOfBallFacedByPlayer(player.getNoOfBallFacedByPlayer()+1);
    }

    private boolean matchStatus(){
        return this.firstInning && this.secondInning && this.second.getRunScoredByTeam()>this.first.getRunScoredByTeam();
    }

    private void addBallToOver(Over over, String value){
        if(value.equalsIgnoreCase("Wd")){
            over.getBallList().add(Ball.WIDE);
        }else if(value.equalsIgnoreCase("Nb")){
            over.getBallList().add(Ball.NOBALL);
        }else if(value.equalsIgnoreCase("1")){
            over.getBallList().add(Ball.ONE);
        }else if(value.equalsIgnoreCase("2")){
            over.getBallList().add(Ball.TWO);
        }else if(value.equalsIgnoreCase("3")){
            over.getBallList().add(Ball.THREE);
        }else if(value.equalsIgnoreCase("4")){
            over.getBallList().add(Ball.FOUR);
        }else if(value.equalsIgnoreCase("6")){
            over.getBallList().add(Ball.SIX);
        }else if(value.equalsIgnoreCase("W")){
            over.getBallList().add(Ball.WICKET);
        }else if(value.equalsIgnoreCase("0")){
            over.getBallList().add(Ball.ZERO);
        }
    }

    private void play(Team team){
        System.out.println("Team : "+team.getTeamId()+" is playing");
        Player p1 = team.getPlayerMap().get(this.battingOrder.remove());
        Player p2 = team.getPlayerMap().get(this.battingOrder.remove());

        String playerOnStrike = "p1";
        int overId = 0;
        int wickets = 0;

        while(!isTeamAllOut(playerOnStrike) && !isAllOverCompleted(overId) && !matchStatus()){
            int count = 0;
            Over over = new Over(String.valueOf(overId+1));

            while(count<6 && !isTeamAllOut(playerOnStrike) && !matchStatus()){
                String input = sc.nextLine();
                if(checkWicket(input)){
                    playerOnStrike = this.battingOrder.isEmpty()?"NONE":playerOnStrike;
                    wickets++;
                    if(playerOnStrike.equals("p1")){
                        p1.setNoOfBallFacedByPlayer(p1.getNoOfBallFacedByPlayer()+1);
                        if(!playerOnStrike.equals("NONE")){
                            p1 = team.getPlayerMap().get(this.battingOrder.remove());
                        }

                    }else{
                        p2.setNoOfBallFacedByPlayer(p2.getNoOfBallFacedByPlayer()+1);
                        if(!playerOnStrike.equals("NONE")){
                            p2 = team.getPlayerMap().get(this.battingOrder.remove());
                        }
                    }
                    count++;
                }else if(checkNoBallOrWide(input)){
                    team.setRunScoredByTeam(team.getRunScoredByTeam()+1);
                }else{
                    count++;
                    int value = Integer.valueOf(input);

                    if(playerOnStrike.equals("p1")){
                        runScoredByPlayer(p1, value);
                        team.setRunScoredByTeam(team.getRunScoredByTeam()+value);
                        playerOnStrike = value%2==0?"p1":"p2";
                    }else{
                        runScoredByPlayer(p2, value);
                        team.setRunScoredByTeam(team.getRunScoredByTeam()+value);
                        playerOnStrike = value%2==0?"p2":"p1";
                    }
                }

                addBallToOver(over, input);
            }

            if(!playerOnStrike.equals("NONE")){
                playerOnStrike = playerOnStrike.equals("p1")?"p2":"p1";
            }

            printScoreBoard(team, wickets, over);
            overId++;
        }
    }

    public void beginMatch(){

        System.out.println("Get Batting Order of team : "+this.first.getTeamId());
        getBattingOrder();
        this.firstInning = true;
        play(this.first);
        System.out.println("Get Batting Order of team : "+this.second.getTeamId());
        getBattingOrder();
        this.secondInning = true;
        play(this.second);
        matchResult();
    }

    private void matchResult(){
        if(this.first.getRunScoredByTeam()==this.second.getRunScoredByTeam()){
            System.out.println("Match is drawn");
        }else if(this.first.getRunScoredByTeam()>this.second.getRunScoredByTeam()){
            System.out.println("Team "+this.first.getTeamId()+" won the match");
        }else{
            System.out.println("Team "+this.second.getTeamId()+" won the match");
        }
    }

}
