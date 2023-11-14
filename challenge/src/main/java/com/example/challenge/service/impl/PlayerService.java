package com.example.challenge.service.impl;

import com.example.challenge.model.Player;
import com.example.challenge.repository.PlayerRepository;
import com.example.challenge.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public void savePlayers(List<Player> players) {
        this.playerRepository.saveAll(players);
    }



}
