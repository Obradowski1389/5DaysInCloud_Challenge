package com.example.challenge.service;

import com.example.challenge.model.Player;
import com.example.challenge.model.utilities.Position;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<Player> parse(Reader reader) throws IOException {
        CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        List<Player> players = new ArrayList<>();
        for (CSVRecord record : csvParser) {
            Player player = new Player();
            extractStats(record, player);
            derivedStats(player);

            players.add(player);
        }
        return players;
    }

    private static void extractStats(CSVRecord record, Player player) {
        player.setFullName(record.get("PLAYER"));
        player.setPosition(resolvePosition(record.get("POSITION")));
        player.setFreeMade(Integer.parseInt(record.get("FTM")));
        player.setFreeAttempted(Integer.parseInt(record.get("FTA")));
        player.setTwoMade(Integer.parseInt(record.get("2PM")));
        player.setTwoAttempted(Integer.parseInt(record.get("2PA")));
        player.setTreeMade(Integer.parseInt(record.get("3PM")));
        player.setTreeAttempted(Integer.parseInt(record.get("3PA")));
        player.setRebounds(Integer.parseInt(record.get("REB")));
        player.setBlocks(Integer.parseInt(record.get("BLK")));
        player.setAssists(Integer.parseInt(record.get("AST")));
        player.setSteals(Integer.parseInt(record.get("STL")));
        player.setTurnovers(Integer.parseInt(record.get("TOV")));
    }

    private static void derivedStats(Player player) {
        player.setGlobalFT(player.getFreeMade() / player.getFreeAttempted());
        player.setGlobalTwoPoints(player.getTwoMade() / player.getTwoAttempted());
        player.setGlobalTreePoints(player.getTreeMade() / player.getTreeAttempted());
        player.setPoints(player.getFreeMade() + 2*player.getTwoMade() + 3*player.getTreeMade());
        player.setVal(calculateVal(player));
        player.setEfg(calculateEfg(player));
        player.setTrueShooting(calculateTrueShooting(player));
        player.setHollingerAssistRatio(calculateHAST(player));
    }

    private static double calculateHAST(Player player) {
        double temp = player.getTwoAttempted() + player.getTreeAttempted() + 0.475*player.getFreeAttempted()
                + player.getAssists() + player.getTurnovers();

        return player.getAssists() / temp;
    }

    private static double calculateTrueShooting(Player player) {
        return player.getPoints() / ( 2 * (player.getTwoAttempted() + player.getTreeAttempted() + 0.475 * player.getFreeAttempted()));
    }

    private static double calculateEfg(Player player) {
        double temp1 = player.getTwoMade() + player.getTreeMade() + 0.5*player.getTreeMade();
        double temp2 = player.getTwoAttempted() + player.getTreeAttempted();

        return temp1 / temp2;
    }

    private static double calculateVal(Player player) {
        double temp1 = player.getFreeMade() + 2*player.getTwoMade() + 3*player.getTreeMade() + player.getRebounds() +
                player.getBlocks() + player.getAssists() + player.getSteals();

        double temp2 = player.getFreeAttempted()-player.getFreeMade() + player.getTwoAttempted()-player.getTwoMade() +
                player.getTreeAttempted()-player.getTreeMade() + player.getTurnovers();

        return temp1 - temp2;
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
}
