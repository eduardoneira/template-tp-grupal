package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;

public abstract class Game {

    protected Player player;
    protected String name;
    protected Set<String> keywords;
    protected Map<String, GameObject> objects;

    public String getName() {
        return name;
    }

    public Game(String name) {
        this.name = name;

        // TODO: armar factories para los distintos juegos, aca hardcodeo para probar

        String namePlayer = "player";
        this.player = new Player(namePlayer);
        this.keywords = new HashSet<String>();
        this.objects = new HashMap<>();
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
            return process(parsedCommand);
        } else {
            return "invalid command";
        }

    }

    private String process(List<String> parsedCommand) {
        String command = parsedCommand.get(0);
        parsedCommand.remove(0);

        List<GameObject> objectsInvolved = new LinkedList<>();
        for (String name : parsedCommand) {
            objectsInvolved.add(objects.get(name));
        }

        String result = player.handleAction(command, objectsInvolved);
        if (checkWinCondition()) {
            return "ganaste";
        } else {
            return result;
        }
    }
}
