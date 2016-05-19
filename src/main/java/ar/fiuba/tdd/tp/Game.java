package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.GameState;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;

public abstract class Game implements GameBuilder {

    protected Player player;
    protected GameState gameState;
    protected final String name;
    protected final Set<String> commands;
    protected final Map<String, GameObject> objects;

    protected static final String needHelpRegex = "help(.)*";
    protected static final String win = ". You won the game!";
    protected static final String loose = ". You lost!";

    public String getName() {
        return name;
    }

    public Game(String name) {
        this(name, new Player("player", new Room("null")));
    }

    public Game(String name, Player player) {
        this.name = name;
        this.player = player;
        this.objects = new HashMap<>();
        this.commands = new HashSet<>();
        this.gameState = GameState.Ready;

        // hardcodeo
        this.commands.add("look");
        this.commands.add("what");
    }

    public abstract boolean checkWinCondition();

    public abstract boolean checkLooseCondition();

    private String preProcess(List<String> parsedCommand) {
        if (parsedCommand.size() > 0) {
            if (gameState == GameState.Ready) {
                gameState = GameState.InProgress;
            }
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
        List<String> parsedCommand = new LinkedList<>();
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
            gameState = GameState.Won;
            return result + win;
        } else if (checkLooseCondition()) {
            gameState = GameState.Lost;
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

    public GameState getCurrentState() {
        return gameState;
    }
}
