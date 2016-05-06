package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.command.AbstractCommandProxy;
import ar.fiuba.tdd.tp.command.CloseServerCommand;
import ar.fiuba.tdd.tp.command.DefaultCommandProxy;
import ar.fiuba.tdd.tp.command.LoadGameServerCommand;
import ar.fiuba.tdd.tp.command.LoadGameServerCommandProxy;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server implements ServerResponses {

    private static Map<String,ServerGameData> gamesData;
    private static List<AbstractCommandProxy> commands;
    private static boolean serverRunning;

    public static void main(String[] args) throws IOException {

        serverRunning = true;

        BufferedReader stdIn = init();
        String fromUser;

        while (serverRunning) {

            fromUser = stdIn.readLine();
            boolean requestHandled = false;
            for (AbstractCommandProxy command : commands) {
                requestHandled = command.handle(fromUser);
                if (requestHandled) {
                    break;
                }
            }
            
            if (!requestHandled) {
                System.out.println(invalidCommand);
            }
        }

    }

    public static Map<String, ServerGameData> getGamesData() {
        return gamesData;
    }

    public static void setGamesData(Map<String, ServerGameData> gamesData) {
        Server.gamesData = gamesData;
    }

    public static void stopServer() {
        serverRunning = false;
    }

    public static void stopGame(String name) {
        gamesData.get(name.toLowerCase()).stopGame();
    }

    private static BufferedReader init() throws IOException {
        gamesData = new HashMap<>();
        commands = new ArrayList<>();
        Motor motor = new Motor();
        for (String game : motor.getNamesGames()) {
            gamesData.put(game.toLowerCase(),new ServerGameData(game));
        }
        
        commands.add(new DefaultCommandProxy(closeServer,new CloseServerCommand()));
        commands.add(new LoadGameServerCommandProxy(loadGame,new LoadGameServerCommand()));
        
        return new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
    }

}