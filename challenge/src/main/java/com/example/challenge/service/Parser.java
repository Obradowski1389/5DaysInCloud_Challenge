package com.example.challenge.service;

import com.example.challenge.model.Player;
import com.example.challenge.model.utilities.Position;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class Parser {
    public static List<Player> parse(Reader reader) throws IOException {
        Map<String, Player> playerMap = new HashMap<>();

        CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord record : csvParser) {
            String playerName = record.get("PLAYER");
            Player player = playerMap.getOrDefault(playerName, new Player());
            extractStats(record, player);

            playerMap.put(playerName, player);
        }

        return calculateDerivedStatistics(playerMap.values());
    }

    private static List<Player> calculateDerivedStatistics(Collection<Player> values) {
        return new ArrayList<>(values);
    }

    private static void extractStats(CSVRecord record, Player player) {
        player.setFullName(record.get("PLAYER"));
        player.setGamesPlayed(player.getGamesPlayed() + 1);
        player.setPosition(resolvePosition(record.get("POSITION")));
        player.setFreeMade(player.getFreeMade() + checkInputValue(Integer.parseInt(record.get("FTM"))));
        player.setFreeAttempted(player.getFreeAttempted() + checkInputValue(Integer.parseInt(record.get("FTA"))));
        player.setTwoMade(player.getTwoMade() + checkInputValue(Integer.parseInt(record.get("2PM"))));
        player.setTwoAttempted(player.getTwoAttempted() + checkInputValue(Integer.parseInt(record.get("2PA"))));
        player.setTreeMade(player.getTreeMade() + checkInputValue(Integer.parseInt(record.get("3PM"))));
        player.setTreeAttempted(player.getTreeAttempted() + checkInputValue(Integer.parseInt(record.get("3PA"))));
        player.setRebounds(player.getRebounds() + checkInputValue(Integer.parseInt(record.get("REB"))));
        player.setBlocks(player.getBlocks() + checkInputValue(Integer.parseInt(record.get("BLK"))));
        player.setAssists(player.getAssists() + checkInputValue(Integer.parseInt(record.get("AST"))));
        player.setSteals(player.getSteals() + checkInputValue(Integer.parseInt(record.get("STL"))));
        player.setTurnovers(player.getTurnovers() + checkInputValue(Integer.parseInt(record.get("TOV"))));
        player.setPoints(player.getFreeMade() + 2*player.getTwoMade() + 3*player.getTreeMade());

    }

    private static Position resolvePosition(String position) {
        return switch (position.toUpperCase()) {
            case "PG" -> Position.PG;
            case "SG" -> Position.SG;
            case "SF" -> Position.SF;
            case "PF" -> Position.PF;
            case "C" -> Position.C;
            default -> throw new IllegalArgumentException("Invalid position: " + position);
        };
    }

    private static int checkInputValue(Object obj) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        } else {
            return 0;
        }
    }
}
