package com.example.challenge.model;

import com.example.challenge.model.utilities.Position;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
@NotNull
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String fullName;

    @NotNull
    private int gamesPlayed;

    @NotNull
    private Position position;

    @NotNull
    private double freeMade;

    @NotNull
    private double freeAttempted;

    @NotNull
    private double twoMade;

    @NotNull
    private double twoAttempted;

    @NotNull
    private double treeMade;

    @NotNull
    private double treeAttempted;

    @NotNull
    private double rebounds;

    @NotNull
    private double blocks;

    @NotNull
    private double assists;

    @NotNull
    private double steals;

    @NotNull
    private double turnovers;

    private double globalFT;

    private double globalTwoPoints;

    private double globalTreePoints;

    private double points;

    private double val;

    private double efg;

    private double trueShooting;

    private double hollingerAssistRatio;

    public Player(String fullName, Position position, double freeMade, double freeAttempted, double twoMade,
                  double twoAttempted, double treeMade, double treeAttempted, double rebounds,
                  double blocks, double assists, double steals, double turnovers) {
        this.fullName = fullName;
        this.position = position;
        this.freeMade = freeMade;
        this.freeAttempted = freeAttempted;
        this.twoMade = twoMade;
        this.twoAttempted = twoAttempted;
        this.treeMade = treeMade;
        this.treeAttempted = treeAttempted;
        this.rebounds = rebounds;
        this.blocks = blocks;
        this.assists = assists;
        this.steals = steals;
        this.turnovers = turnovers;
    }


    public void derivedStats() {
        this.setPoints(this.getPoints() + this.getFreeMade() + 2*this.getTwoMade() + 3*this.getTreeMade());
        this.setGlobalFT(this.getFreeMade() / this.getFreeAttempted());
        this.setGlobalTwoPoints(this.getTwoMade() / this.getTwoAttempted());
        this.setGlobalTreePoints(this.getTreeMade() / this.getTreeAttempted());
        this.setVal(calculateVal());
        this.setEfg(calculateEfg());
        this.setTrueShooting(calculateTrueShooting());
        this.setHollingerAssistRatio(calculateHAST());
    }

    private double calculateHAST() {
        double temp = this.getTwoAttempted() + this.getTreeAttempted() + 0.475*this.getFreeAttempted()
                + this.getAssists() + this.getTurnovers();

        return this.getAssists() / temp;
    }

    private double calculateTrueShooting() {
        return this.getPoints() / ( 2 * (this.getTwoAttempted() + this.getTreeAttempted() + 0.475 * this.getFreeAttempted()));
    }

    public double calculateEfg() {
        double temp1 = this.getTwoMade() + this.getTreeMade() + 0.5*this.getTreeMade();
        double temp2 = this.getTwoAttempted() + this.getTreeAttempted();

        return temp1 / temp2;
    }

    public double calculateVal() {
        double temp = this.getFreeMade() + 2*this.getTwoMade() + 3*this.getTreeMade() + this.getRebounds() +
                this.getBlocks() + this.getAssists() + this.getSteals() -
                (this.getFreeAttempted()-this.getFreeMade() + this.getTwoAttempted()-this.getTwoMade() +
                this.getTreeAttempted()-this.getTreeMade() + this.getTurnovers());

        return (double) Math.round(temp / getGamesPlayed() * 10) / 10;
    }
}
