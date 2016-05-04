package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;

public abstract class Game {

    protected Player player;
    protected String name;
    protected Set<String> commands;
    protected Map<String, GameObject> objects;

    protected String needHelpRegex = "help(.)*";
    protected String win = ". You won the game!";
    protected String loose = ". You lost!";

    public String getName() {
        return name;
    }

    public Game(String name) {
        this(name, new Player("player", null));
    }

    public Game(String name, Player player) {
        this.name = name;
        this.player = player;
        this.objects = new HashMap<>();
        this.commands = new HashSet<>();

        // hardcodeo
        this.commands.add("look");
        this.commands.add("what");
    }

    public abstract boolean checkWinCondition();

    public abstract boolean checkLooseCondition();

    private String preProcess(List<String> parsedCommand) {
        if (parsedCommand.size() > 0) {
            return process(parsedCommand);
        } else {
            return "invalid command";
        }
    }

    public String processCommand(String stringCommand) {

        System.out.println("SERVER PROCESS " + stringCommand);

        if ( stringCommand.toLowerCase().matches(needHelpRegex)) {
            return help();
        }

        String[] splitCommand = stringCommand.split(" ");
        List<String> parsedCommand = new LinkedList<String>();
        for (String elem : splitCommand) {
            if (commands.contains(elem) || objects.keySet().contains(elem)) {
                parsedCommand.add(elem);
            }
        }

        return preProcess(parsedCommand);

    }

    private String process(List<String> parsedCommand) {
        String command = parsedCommand.get(0);
        parsedCommand.remove(0);

        if (!commands.contains(command)) {
            return "invalid command: " + command;
        }

        List<GameObject> objectsInvolved = parseObjectsFromCommand(parsedCommand);
        // si alguna vez hay comandos que no reciben argumentos, hay que sacar esto
        if (objectsInvolved.size() == 0) {
            return "you didn't input any visible object names";
        }

        return handleProcessedCommand(command, objectsInvolved);
    }

    private String handleProcessedCommand(String command, List<GameObject> objectsInvolved) {
        String result = player.handleAction(command, objectsInvolved);
        if (checkWinCondition()) {
            return result + win;
        } else if (checkLooseCondition()) {
            return result + loose;
        } else {
            return result;
        }
    }

    private List<GameObject> parseObjectsFromCommand(List<String> parsedCommand) {
        List<GameObject> objectsInvolved = new LinkedList<>();
        for (String name : parsedCommand) {
            if (player.getParent().getName().equals(name)) {
                objectsInvolved.add(player.getParent());
            } else if (player.getParent().containsInHierarchy(name)) {
                objectsInvolved.add(player.getParent().getChildFromHierarchy(name));
            }
        }
        return objectsInvolved;
    }

    private String help() {
        String response = "You can use the following commands: ";
        for (String command : commands) {
            response = response.concat(command);
            response = response.concat(", ");
        }
        response = response.concat("On the following visible objects: ");
        for (String object : objects.keySet()) {
            if (player.getParent().getName().equals(object) || player.getParent().containsInHierarchy(object)) {
                response = response.concat(object);
                response = response.concat(", ");
            }
        }
        return response;
    }
}
