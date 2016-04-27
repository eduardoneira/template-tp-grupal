package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Master on 20/04/2016.
 */
class ClientWorker implements Runnable {
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private Game game;

    //Constructor

    //TODO: usar gameName
    ClientWorker(ServerSocket server, String gameName) {
        this.serverSocket = server;
        game = Motor.createGame(gameName);
    }

    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            clientSocket = serverSocket.accept();
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            String inputLine = "";
            String outputLine = "";

            while (!outputLine.equals("ganaste")) {
                inputLine = in.readLine();
                if (inputLine != null) {
                    System.out.println("INPUT LINE " + inputLine);
                    outputLine = game.processComand(inputLine);
                    out.println(outputLine);
                    out.flush();
                }
            }
            out.close();
            in.close();
            clientSocket.close();

        } catch (IOException e) {
            System.err.println("Thread failed");
        }
    }
}
