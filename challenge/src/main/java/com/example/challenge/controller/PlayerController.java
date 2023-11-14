package com.example.challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stats")
public class PlayerController {

    @GetMapping("/player/{playerFullName}")
    public ResponseEntity<?> getPlayerStatistics() {

    }
}
