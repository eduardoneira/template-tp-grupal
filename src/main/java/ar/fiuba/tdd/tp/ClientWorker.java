package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class ClientWorker implements Runnable {
    private Socket clientSocket;
    private final ServerSocket serverSocket;
    private final Game game;
    private volatile boolean isRunning = false;

    private static final String win = ". You won the game!";
    private static final String loose = ". You lost!";

    ClientWorker(ServerSocket server, String gameName) {
        this.serverSocket = server;
        Motor motor = new Motor();
        this.game = motor.createGame(gameName);
    }

    public void run() {

        try {
            this.isRunning = true;
            clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            String inputLine;
            String outputLine;
            while (this.isRunning && (inputLine = in.readLine()) != null ) {
                System.out.println("INPUT LINE " + inputLine);
                outputLine = this.game.processCommand(inputLine);
                out.println(outputLine);
                out.flush();

                if (outputLine.contains(win) || outputLine.contains(loose)) {
                    this.isRunning = false;
                }
            }
            
            out.close();
            in.close();
            this.closeSockets();

        } catch (IOException e) {
            System.err.println("Thread failed");
        }
    }

    private void closeSockets() throws IOException {
        this.clientSocket.close();
        this.serverSocket.close();
        Server.stopGame(this.game.getName());
    }

    public void kill() {
        this.isRunning = false;
    }
}
