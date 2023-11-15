package com.example.challenge.dto;

import com.example.challenge.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatResponseDto {
    private String playerName;
    private int gamesPlayed;
    private TraditionalDto traditional;
    private AdvancedDto advanced;

    public StatResponseDto(Player player) {
        this.playerName = player.getFullName();
        this.gamesPlayed = player.getGamesPlayed();
        this.traditional = new TraditionalDto(player);
        this.advanced = new AdvancedDto(player);
    }
}
