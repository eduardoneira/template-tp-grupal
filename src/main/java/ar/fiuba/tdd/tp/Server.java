package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {
    //TODO: refactorizar en clases e interfaces
    private static int FIRSTPORT = 8081;
    private static Map<String,ServerGameData> gamesData;

    private static String LOADGAME = "load game";
    private static String CLOSESERVER = "close server";

    private static String INVALIDCOMMAND = "Error : Invalid command";
    private static String NOTSUCHGAME = "There is no such game. Games Available are :";
    private static String LOADSUCCESSFUL = "Game loaded and listening on port ";
    private static String GAMERUNNING = "Can't load game, game is running on port ";
    private static String CLOSINGSERVER = "Closing Server. Bye";

    public static void main(String[] args) throws IOException {

        int portNumber = FIRSTPORT;
        boolean serverRunning = true;

        BufferedReader stdIn = init();
        String fromUser;

        while (serverRunning) {

            fromUser = stdIn.readLine();
            if (fromUser.toLowerCase().matches(LOADGAME + "(.*)")) {
                String game = fromUser.replace(LOADGAME + " ","");
                if (Motor.isValidGame(game)) {
                    portNumber = openSocketInPort(portNumber,game.toLowerCase());
                } else {
                    printGamesAvailable();
                }
            } else if (fromUser.toLowerCase().matches(CLOSESERVER)) {
                closeServer();
                serverRunning = false;
            } else {
                System.out.println(INVALIDCOMMAND);
            }
        }

    }


    private static BufferedReader init() throws IOException {
        gamesData = new HashMap<String,ServerGameData>();
        for (String game : Motor.GAMESAVAILABLE) {
            gamesData.put(game.toLowerCase(),new ServerGameData(game));
        }

        return new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
    }

    private static int openSocketInPort(int port, String game) throws IOException {
        ServerGameData data = gamesData.get(game);
        if (data.isRunning()) {
            System.out.println(GAMERUNNING.concat(String.valueOf(data.getPort())));
            return port;
        } else {
            ClientWorker worker = new ClientWorker(new ServerSocket(port), data.getName());
            Thread thread = new Thread(worker);
            thread.start();
            data.setPort(port);
            System.out.println(LOADSUCCESSFUL + String.valueOf(port));
            return port + 1;
        }
    }

    private static void printGamesAvailable() {
        System.out.println(NOTSUCHGAME);
        for ( String game : Motor.GAMESAVAILABLE) {
            System.out.println(game);
        }
    }

    private static void closeServer() {
        System.out.println(CLOSINGSERVER);
    }
}