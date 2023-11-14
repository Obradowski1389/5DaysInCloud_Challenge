package com.example.challenge;

import com.example.challenge.model.Player;
import com.example.challenge.service.IPlayerService;
import com.example.challenge.service.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ChallengeApplication {

	private static final String FILE_PATH = "src/main/resources/static/L9HomeworkChallengePlayersInput.csv";

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ChallengeApplication.class);

		app.setWebApplicationType(WebApplicationType.SERVLET);
		ConfigurableApplicationContext context = app.run(args);

        try {
            IPlayerService playerService = context.getBean(IPlayerService.class);
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            List<Player> players = Parser.parse(reader);
            playerService.savePlayers(players);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}

}
