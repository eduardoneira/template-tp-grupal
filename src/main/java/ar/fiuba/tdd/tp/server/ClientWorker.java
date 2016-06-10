package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.Motor;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ClientWorker implements Runnable {
    private Map<String, Socket> clientSockets;
    private final ServerSocket serverSocket;
    private final Game game;
    private BooleanState isRunning;

    private static final String win = ". You won the game!";
    private static final String loose = ". You lost!";

    ClientWorker(ServerSocket server, String gameName) throws ClassNotFoundException, IOException,
            InstantiationException, IllegalAccessException {
        this.serverSocket = server;
        Motor motor = new Motor();
        this.game = motor.createGame(gameName);
        this.clientSockets = new HashMap<>();
        isRunning = new BooleanState(false);
    }

    public void run() {

        try {
            this.isRunning.setTrue();
            serverSocket.setSoTimeout(1000);
            while (this.isRunning.isTrue()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    //clientSocket.setSoTimeout(1000);
                    clientSockets.put(clientSocket.toString(), clientSocket);
                    ClientWorkerInner inner = new ClientWorkerInner(clientSockets, clientSocket.toString(), game, isRunning);
                    Thread thread = new Thread(inner);
                    thread.start();
                } catch (SocketTimeoutException e) {
                    // timeout
                }
            }


            /*PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            String inputLine;
            String outputLine;
            while (this.isRunning && (inputLine = in.readLine()) != null ) {
                System.out.println("INPUT LINE " + inputLine);
                outputLine = this.game.processCommand(clientSocket.toString(), inputLine);
                out.println(outputLine);
                out.flush();

                if (outputLine.contains(win) || outputLine.contains(loose)) {
                    this.isRunning = false;
                }
            }
            
            out.close();
            in.close();*/
            this.closeSockets();

        } catch (IOException e) {
            System.err.println("Thread failed");
        }
    }

    private void closeSockets() throws IOException {
        //this.clientSocket.close();
        this.serverSocket.close();
        Server.stopGame(this.game.getName());
    }

    public void kill() {
        this.isRunning.setFalse();
    }
}
