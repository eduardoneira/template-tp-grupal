package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class ClientWorker implements Runnable {
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private Game game;
    private volatile boolean isRunning = false;

    ClientWorker(ServerSocket server, String gameName) {
        this.serverSocket = server;
        Motor motor = new Motor(gameName);
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
                /// TODO: esto es viejo, deberia cambiar pero deberia estar en algun lado la condicion de victoria
                if (outputLine.equals("ganaste")) {
                    this.isRunning = false;
                }
            }
            
            out.close();
            in.close();
            this.clientSocket.close();
            this.serverSocket.close();

        } catch (IOException e) {
            System.err.println("Thread failed");
        }
    }

    public void kill() {
        this.isRunning = false;
    }
}
