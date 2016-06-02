package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.driver.GameState;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.AbstractLockedOpenable;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;

import static ar.fiuba.tdd.tp.driver.GameState.*;

public abstract class Game implements GameBuilder {

    protected Map<String, Player> players;
    protected final Map<String, Set<String>> commandsPerPlayer;
    protected Map<String, List<AbstractCondition>> winConditionsPerPlayer;
    protected Map<String, List<AbstractCondition>> looseConditionsPerPlayer;

    protected GameState gameState;
    protected final String name;
    protected final Map<String, GameObject> objects;

    protected static final String needHelpRegex = "help(.)*";
    protected static final String win = ". You won the game!";
    protected static final String loose = ". You lost!";

    public String getName() {
        return name;
    }

    public Game(String name) {
        this.name = name;
        this.players = new HashMap<>();
        this.objects = new HashMap<>();
        this.winConditionsPerPlayer = new HashMap<>();
        this.looseConditionsPerPlayer = new HashMap<>();
        this.commandsPerPlayer = new HashMap<>();
        gameState = Ready;
    }

    public boolean checkWinCondition(String playerId) {
        for (AbstractCondition cond : winConditionsPerPlayer.get(playerId)) {
            if (cond.checkCondition()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLooseCondition(String playerId) {
        for (AbstractCondition cond : looseConditionsPerPlayer.get(playerId)) {
            if (cond.checkCondition()) {
                return true;
            }
        }
        return false;
    }

    private String preProcess(String playerId, List<String> parsedCommand) {
        if (parsedCommand.size() > 0) {
            if (gameState == Ready) {
                gameState = InProgress;
            }
            return process(playerId, parsedCommand);
        } else {
            return "invalid command";
        }
    }

    protected void createPlayer(String playerId) {
        Player player = new Player("player" + Integer.toString(players.size()+1), null);
        players.put(playerId, player);

        Set<String> commands = new HashSet<>();
        commandsPerPlayer.put(playerId, commands);
        // hardcodeo
        commands.add("look");
        commands.add("what");

        List<AbstractCondition> winConds = new ArrayList<>();
        winConditionsPerPlayer.put(playerId, winConds);

        List<AbstractCondition> looseConds = new ArrayList<>();
        looseConditionsPerPlayer.put(playerId, looseConds);

        configPlayer(playerId);
    }

    protected abstract void configPlayer(String playerId);

    public String processCommand(String playerId, String stringCommand) {

        System.out.println("SERVER PROCESS " + stringCommand + " FROM " + playerId);

        // TODO: refactorizar
        if (!players.containsKey(playerId)) {
            if (stringCommand.equals("create player")) {
                createPlayer(playerId);
                return "Entered game as player";
            } else {
                return "You need to input 'create player' to start";
            }
        }

        if ( stringCommand.toLowerCase().matches(needHelpRegex)) {
            return help(playerId);
        }

        String[] splitCommand = stringCommand.split(" ");
        List<String> parsedCommand = new LinkedList<>();
        for (String elem : splitCommand) {
            if (commandsPerPlayer.get(playerId).contains(elem) || objects.keySet().contains(elem)) {
                parsedCommand.add(elem);
            }
        }

        return preProcess(playerId, parsedCommand);

    }

    private String process(String playerId, List<String> parsedCommand) {
        String command = parsedCommand.get(0);
        parsedCommand.remove(0);

        if (!commandsPerPlayer.get(playerId).contains(command)) {
            return "invalid command: " + command;
        }

        List<GameObject> objectsInvolved = parseObjectsFromCommand(parsedCommand);
        // si alguna vez hay comandos que no reciben argumentos, hay que sacar esto
        if (objectsInvolved.size() == 0) {
            return "you didn't input any visible object names";
        }

        return handleProcessedCommand(playerId, command, objectsInvolved);
    }

    protected abstract void updateGameAfterHandle();

    private String handleProcessedCommand(String playerId, String command, List<GameObject> objectsInvolved) {
        String result = players.get(playerId).handleAction(command, objectsInvolved);
        updateGameAfterHandle();
        if (checkWinCondition(playerId)) {
            gameState = Won;
            return result + win;
        } else if (checkLooseCondition(playerId)) {
            gameState = Lost;
            return result + loose;
        } else {
            return result;
        }
    }

    private List<GameObject> parseObjectsFromCommand(List<String> parsedCommand) {
        List<GameObject> objectsInvolved = new LinkedList<>();
        for (String name : parsedCommand) {
            /*if (player.getParent().getName().equals(name)) {
                objectsInvolved.add(player.getParent());
            } else if (player.getParent().containsInHierarchy(name)) {
                objectsInvolved.add(player.getParent().getChildFromHierarchy(name));
            }*/
            if (objects.containsKey(name)) {
                objectsInvolved.add(objects.get(name));
            }
        }
        return objectsInvolved;
    }

    private String help(String playerId) {
        String response = "You can use the following commands: ";
        for (String command : commandsPerPlayer.get(playerId)) {
            response = response.concat(command);
            response = response.concat(", ");
        }
        response = response.concat("On the following visible objects: ");
        for (String object : objects.keySet()) {
            /*if (player.getParent().getName().equals(object) || player.getParent().containsInHierarchy(object)) {
                response = response.concat(object);
                response = response.concat(", ");
            }*/
            response = response.concat(object);
            response = response.concat(", ");
        }
        return response;
    }

    public GameState getCurrentState() {
        return gameState;
    }
}
