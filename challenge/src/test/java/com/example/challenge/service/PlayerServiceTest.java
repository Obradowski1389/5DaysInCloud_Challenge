package com.example.challenge.service;
import com.example.challenge.model.Player;
import com.example.challenge.repository.PlayerRepository;
import com.example.challenge.service.impl.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.challenge.model.utilities.Position.PF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSavePlayers() {
        List<Player> playersToSave = new ArrayList<>();

        playerService.savePlayers(playersToSave);
        verify(playerRepository, times(1)).saveAll(playersToSave);
    }

    @Test
    void testGetByFullName_PlayerFound() {
        Player expectedPlayer = new Player("Sifiso Abdalla", PF,7,7,2,6,1,5,1,1,0,3,2);
        when(playerRepository.getByFullName("Sifiso Abdalla")).thenReturn(Optional.of(expectedPlayer));

        Optional<Player> result = playerService.getByFullName("Sifiso Abdalla");

        assertTrue(result.isPresent());
        assertEquals(expectedPlayer, result.get());
    }

    @Test
    void testGetByFullName_PlayerNotFound() {
        String playerName = "NonexistentPlayer";
        when(playerRepository.getByFullName(playerName)).thenReturn(Optional.empty());

        Optional<Player> result = playerService.getByFullName(playerName);

        assertFalse(result.isPresent());
    }
}

