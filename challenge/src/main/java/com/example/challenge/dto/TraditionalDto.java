package com.example.challenge.dto;

import com.example.challenge.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraditionalDto {
    private ShootStat freeThrows;
    private ShootStat twoPoints;
    private ShootStat treePoints;
    private double points;
    private double rebounds;
    private double blocks;
    private double assists;
    private double steals;
    private double turnovers;

    public TraditionalDto(Player player) {
        this.freeThrows = new ShootStat(player.getFreeAttempted(), player.getFreeMade(), player.getGamesPlayed());
        this.twoPoints = new ShootStat(player.getTwoAttempted(), player.getTwoMade(), player.getGamesPlayed());
        this.treePoints = new ShootStat(player.getTreeAttempted(), player.getTreeMade(), player.getGamesPlayed());
        this.points = (double) Math.round(player.getPoints() / player.getGamesPlayed() * 10) / 10;
        this.rebounds = (double) Math.round(player.getRebounds() / player.getGamesPlayed() * 10) / 10;
        this.blocks = (double) Math.round(player.getBlocks() / player.getGamesPlayed() * 10) / 10;
        this.assists = (double) Math.round(player.getAssists() / player.getGamesPlayed() * 10) / 10;
        this.steals = (double) Math.round(player.getSteals() / player.getGamesPlayed() * 10) / 10;
        this.turnovers = (double) Math.round(player.getTurnovers() / player.getGamesPlayed() * 10) / 10;
    }
}
