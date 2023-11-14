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
}
