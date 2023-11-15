package com.example.challenge.controller;

import com.example.challenge.model.Player;
import com.example.challenge.service.IPlayerService;
import com.example.challenge.service.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileController {
    @Autowired
    private IPlayerService playerService;

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<Player> players = Parser.parse(reader, new ArrayList<>());
            playerService.savePlayers(players);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/appendFile")
    public ResponseEntity<?> appendFile(@RequestParam("file")MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<Player> players = Parser.parse(reader, playerService.getAll());
            playerService.savePlayers(players);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
