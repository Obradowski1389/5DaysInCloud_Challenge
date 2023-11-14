package com.example.challenge.service;

import com.example.challenge.model.Player;

import java.util.List;
import java.util.Optional;

public interface IPlayerService {
    void savePlayers(List<Player> players);

    Optional<Player> getByFullName(String playerFullName);
}
