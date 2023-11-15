package com.example.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShootStat {
    private double attempts;
    private double made;
    private double shootingPercentage;

    public ShootStat(double attempted, double made, int gamesPlayed) {
        this.made = (double) Math.round(made / gamesPlayed * 10) / 10;
        this.attempts = (double) Math.round(attempted / gamesPlayed * 10) / 10;
        this.shootingPercentage = (double) Math.round(made / attempted * 100 * 10) / 10;
    }
}
