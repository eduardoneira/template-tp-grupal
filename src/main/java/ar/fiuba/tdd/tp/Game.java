package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Game {

    protected Player player;
    protected String name;
    protected Set<String> keywords;
    protected Map<String, GameObject> objects;

    protected String needHelpRegex = "help(.)*";
    protected String help = "You can use this keywords (all other words will be ignored) : ";
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
        this.keywords = new HashSet<>();
        this.objects = new HashMap<>();

        // hardcodeo
        this.keywords.add("look");
        this.keywords.add("what");
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
            if (keywords.contains(elem)) {
                parsedCommand.add(elem);
            }
        }

        return preProcess(parsedCommand);

    }

    private String process(List<String> parsedCommand) {
        String command = parsedCommand.get(0);
        parsedCommand.remove(0);

        /*if (!commands.contains(command)) {
            return "invalid command: " + command;
        }*/

        List<GameObject> objectsInvolved = parsedCommand.stream().map(name -> objects.get(name))
                .collect(Collectors.toCollection(LinkedList::new));

        /*List<GameObject> objectsInvolved = new LinkedList<>();
        for (String name : parsedCommand) {
            if (player.getParent().getName().equals(name)) {
                objectsInvolved.add(player.getParent());
            } else if (player.getParent().containsInHierarchy(name)) {
                objectsInvolved.add(player.getParent().getChildFromHirerarchy(name));
            } else {
                return "there is no visible object called '" + name + "'";
            }
        }*/

        String result = player.handleAction(command, objectsInvolved);
        if (checkWinCondition()) {
            return result + win;
        } else if (checkLooseCondition()) {
            return result + loose;
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
