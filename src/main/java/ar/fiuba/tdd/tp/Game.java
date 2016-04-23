package ar.fiuba.tdd.tp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Master on 21/04/2016.
 */
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

        System.out.println("SERVER PROCESS "+stringCommand);
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

            return player.doAction(command, parsedCommand.toArray(new String[parsedCommand.size()]));
        } else {
            return "invalid command";
        }
    }

}
