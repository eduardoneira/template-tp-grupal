package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;

public abstract class Game {

    protected Player player;
    protected String name;
    protected Set<String> keywords;
    protected Map<String, GameObject> objects;

    protected final String needHelpRegex = "help(.)*";
    protected final String help = "You can use this words : ";

    public String getName() {
        return name;
    }

    public Game(String name) {
        this(name,new Player("player"));
    }

    public Game(String name, Player player) {
        this.name = name;
        this.player = player;
        this.keywords = new HashSet<String>();
        this.objects = new HashMap<>();
    }

    public abstract boolean checkWinCondition();

    public String processCommand(String stringCommand) {

        System.out.println("SERVER PROCESS " + stringCommand);

        if ( stringCommand.toLowerCase().matches(needHelpRegex)) {
            return help();
        }

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

    private String help() {
        String response = " ";
        for (String keyword : keywords) {
            response = response.concat(keyword.concat(", "));
        }
        return help.concat(response);
    }
}
