package com.example.challenge.controller;

import com.example.challenge.model.Player;
import com.example.challenge.service.IPlayerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.example.challenge.model.utilities.Position.PF;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@RunWith(SpringRunner.class)
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPlayerService playerService;



    @Test
    public void testGetPlayerStatistics_Success() throws Exception {
        Player mockPlayer = new Player("Sifiso Abdalla", PF, 7, 7, 2,
                6, 1, 5, 1, 1, 0, 3, 2);

//        StatResponseDto expectedDto = new StatResponseDto(mockPlayer);

        when(playerService.getByFullName(anyString())).thenReturn(Optional.of(mockPlayer));

        mockMvc.perform(get("/stats/player/{playerFullName}", "Sifiso Abdalla"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPlayerStatistics_PlayerNotFound() throws Exception {
        when(playerService.getByFullName(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/stats/player/{playerFullName}", "NonexistentPlayer"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Player not found"));

    }


}
