package com.example.challenge.controller;

import com.example.challenge.dto.StatResponseDto;
import com.example.challenge.error.CustomError;
import com.example.challenge.model.Player;
import com.example.challenge.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/stats")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @GetMapping("/player/{playerFullName}")
    public ResponseEntity<?> getPlayerStatistics(@PathVariable String playerFullName) {
        Optional<Player> player = playerService.getByFullName(playerFullName);
        if (player.isPresent()) {
            StatResponseDto dto = new StatResponseDto(player.get());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomError(HttpStatus.NOT_FOUND, "Player not found"), HttpStatus.NOT_FOUND);
    }
}
