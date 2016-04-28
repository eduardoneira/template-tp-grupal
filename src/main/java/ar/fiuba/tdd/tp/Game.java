package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.player.Player;

import java.util.*;

public abstract class Game {

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

    public abstract boolean checkWinCondition();

    public String processCommand(String stringCommand) {

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

            String result = player.doAction(command, parsedCommand);
            if (checkWinCondition()) {
                return "ganaste";
            } else {
                return result;
            }
        } else {
            return "invalid command";
        }
    }

}
