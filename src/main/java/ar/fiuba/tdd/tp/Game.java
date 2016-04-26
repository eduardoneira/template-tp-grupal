package ar.fiuba.tdd.tp;

import java.util.*;

public class Game {

    protected Player player;
    protected String name;
    protected Set<String> keywords;

    public String getName() {
        return name;
    }

    public Game(String name) {
        this.name = name;

        // TODO: armar factories para los distintos juegos, aca hardcodeo para probar

        String namePlayer = "player";
        this.player = new Player(namePlayer);
        this.keywords = new HashSet<String>();
    }

    public String processComand(String stringCommand) {

        System.out.println("SERVER PROCESS " + stringCommand);
        String[] splitCommand = stringCommand.split(" ");
        List<String> parsedCommand = new LinkedList<String>();
        for (String elem : splitCommand) {
            if (keywords.contains(elem)) {
                parsedCommand.add(elem);
            }
        }
        if (parsedCommand.size() > 0) {
            String command = parsedCommand.get(0);
            parsedCommand.remove(0);

            return player.doAction(command, parsedCommand);
        } else {
            return "invalid command";
        }
    }

}
