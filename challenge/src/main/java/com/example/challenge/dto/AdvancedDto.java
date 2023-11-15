package com.example.challenge.dto;

import com.example.challenge.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvancedDto {
    private double valorization;
    private double effectiveFieldGoalPercentage;
    private double trueShootingPercentage;
    private double hollingerAssistRatio;

    public AdvancedDto(Player player) {
        this.valorization = (player.getVal() );
        this.effectiveFieldGoalPercentage = (double) Math.round(player.getEfg() * 100 * 10) / 10 ;
        this.trueShootingPercentage = (double) Math.round(player.getTrueShooting() * 100 * 10) / 10;
        this.hollingerAssistRatio = (double) Math.round(player.getHollingerAssistRatio() * 100 * 10) / 10;
    }
}
