package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.driver.GameState;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithParent;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.random.RNG;
import ar.fiuba.tdd.tp.random.RandomJava;
import ar.fiuba.tdd.tp.random.RandomReference;
import ar.fiuba.tdd.tp.timedevent.AbstractTimer;
import ar.fiuba.tdd.tp.timedevent.ActionGeneration;
import ar.fiuba.tdd.tp.timedevent.TimerReference;
import ar.fiuba.tdd.tp.timedevent.TimerSystem;

import java.net.Socket;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Game implements GameBuilder {

    protected Map<String, Player> players;
    protected final Map<String, Set<String>> commandsPerPlayer;
    protected Map<String, List<AbstractCondition>> winConditionsPerPlayer;
    protected Map<String, List<AbstractCondition>> looseConditionsPerPlayer;
    protected ActionGeneration actionGeneration;
    protected Thread actionGenerationThread;

    protected Map<String, GameState> gameStateByPlayer;
    protected final String name;
    protected final Map<String, GameObject> objects;
    protected TimerReference timer;
    protected RandomReference random;

    protected static final String needHelpRegex = "help(.)*";
    protected static final String win = ". You won the game!";
    protected static final String loose = ". You lost!";
    protected Map<String, Socket> clientSockets;

    ReentrantLock lock;

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
        //gameStateByPlayer = Ready;
        gameStateByPlayer = new HashMap<>();
        timer = new TimerReference(new TimerSystem());
        random = new RandomReference(new RandomJava());
        this.clientSockets = new HashMap<>();
        actionGeneration = null;
        actionGenerationThread = null;

        lock = new ReentrantLock(true);
    }

    protected void configActionGeneration() {
        actionGeneration = new ActionGeneration(this.clientSockets, this.timer, this.lock);
    }

    public void start() {
        lock.lock();
        try {
            actionGenerationThread = new Thread(actionGeneration);
            actionGenerationThread.start();
        } finally {
            lock.unlock();
        }
    }

    public void stopActionGeneration() {
        if (actionGenerationThread != null) {
            actionGeneration.killActionGeneration();
            try {
                actionGenerationThread.interrupt();
                actionGenerationThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        lock.lock();
        try {
            stopActionGeneration();
        } finally {
            lock.unlock();
        }
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

    protected void removePlayer(String playerId) {
        removePlayerItems(playerId);
        players.get(playerId).getParent().removeChild(players.get(playerId));
        players.remove(playerId);
        commandsPerPlayer.remove(playerId);
        winConditionsPerPlayer.remove(playerId);
        looseConditionsPerPlayer.remove(playerId);
        gameStateByPlayer.remove(playerId);
    }

    // esto lo deberia definir cada juego
    protected void removePlayerItems(String playerId) {
        Player player = players.get(playerId);
        for (GameObjectWithParent o : player.getChildren()) {
            objects.remove(o.getName());
        }
    }

    private String preProcess(String playerId, List<String> parsedCommand, BooleanState forward) {
        if (parsedCommand.size() > 0) {
            if (gameStateByPlayer.get(playerId) == GameState.Ready) {
                gameStateByPlayer.put(playerId, GameState.InProgress);
            }
            return process(playerId, parsedCommand, forward);
        } else {
            return "invalid command";
        }
    }

    protected void createPlayer(String playerId, String type) {

        Set<String> commands = new HashSet<>();
        commandsPerPlayer.put(playerId, commands);
        // hardcodeo
        commands.add("look");
        commands.add("what");

        List<AbstractCondition> winConds = new ArrayList<>();
        winConditionsPerPlayer.put(playerId, winConds);

        List<AbstractCondition> looseConds = new ArrayList<>();
        looseConditionsPerPlayer.put(playerId, looseConds);

        //Player player = new Player("player" + Integer.toString(players.size()+1), null);
        players.put(playerId, configPlayer(playerId, type));

        gameStateByPlayer.put(playerId, GameState.Ready);
    }

    protected abstract Player configPlayer(String playerId, String type);

    public String processCommand(String playerId, String stringCommand, BooleanState forward) {
        lock.lock();
        try {
            forward.setFalse();
            System.out.println("SERVER PROCESS " + stringCommand + " FROM " + playerId);

            // TODO: refactorizar y agregar tipo de jugador
            if (!players.containsKey(playerId)) {
                if (stringCommand.equals("create player")) {
                    createPlayer(playerId, "");
                    return "Entered game as player";
                } else {
                    return "You need to input 'create player' to start";
                }
            } else {
                if (stringCommand.toLowerCase().matches(needHelpRegex)) {
                    return help(playerId);
                } else {
                    List<String> parsedCommand = parseCommand(playerId, stringCommand);
                    return preProcess(playerId, parsedCommand, forward);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private List<String> parseCommand(String playerId, String command) {
        String[] splitCommand = command.split(" ");
        List<String> parsedCommand = new LinkedList<>();
        for (String elem : splitCommand) {
            if (commandsPerPlayer.get(playerId).contains(elem) || objects.keySet().contains(elem)) {
                parsedCommand.add(elem);
            }
        }
        return parsedCommand;
    }

    private String process(String playerId, List<String> parsedCommand, BooleanState forward) {
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

        forward.setTrue();
        return players.get(playerId).getName() + ": " + handleProcessedCommand(playerId, command, objectsInvolved);
    }

    public void updateGameState() {
        lock.lock();
        try {
            for (String playerId : players.keySet()) {
                updateGameState(playerId);
            }
        } finally {
            lock.unlock();
        }
    }

    protected abstract void updateGameAfterHandle(String playerId);

    protected void updateGameState(String playerId) {
        updateGameAfterHandle(playerId);
        if (checkWinCondition(playerId)) {
            gameStateByPlayer.put(playerId, GameState.Won);
        } else if (checkLooseCondition(playerId)) {
            gameStateByPlayer.put(playerId, GameState.Lost);
        }
    }

    private String handleProcessedCommand(String playerId, String command, List<GameObject> objectsInvolved) {
        String result = players.get(playerId).handleAction(command, objectsInvolved);
        updateGameState();
        if (gameStateByPlayer.get(playerId).equals(GameState.Won)) {
            return result + win;
        } else if (gameStateByPlayer.get(playerId).equals(GameState.Lost)) {
            removePlayer(playerId);
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
        return gameStateByPlayer.get(" ");
    }

    public GameState getCurrentState(String playerId) {
        lock.lock();
        try {
            // TODO: descomentar esta linea para debugear mejor las tests
            //System.out.println("devuelvo estado de " + playerId);
            if (gameStateByPlayer.containsKey(playerId)) {
                updateGameState();
            }
            return gameStateByPlayer.get(playerId);
        } finally {
            lock.unlock();
        }
    }

    public void setClients(Map<String,Socket> clients) {
        lock.lock();
        try {
            this.clientSockets = clients;
        } finally {
            lock.unlock();
        }
    }

    public void setTimer(AbstractTimer instance) {
        lock.lock();
        try {
            this.timer.setTimer(instance);
        } finally {
            lock.unlock();
        }
    }

    public void setRandom(RNG random) {
        lock.lock();
        try {
            this.random.setRandom(random);
        } finally {
            lock.unlock();
        }
    }
}
