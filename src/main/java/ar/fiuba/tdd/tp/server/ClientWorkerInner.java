package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.Motor;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * Created by Master on 02/06/2016.
 */
public class ClientWorkerInner implements Runnable {
    private Map<String, Socket> clientSockets;
    private String myClientSocket;
    private final Game game;
    private BooleanState isRunning;

    private static final String win = ". You won the game!";
    private static final String loose = ". You lost!";

    ClientWorkerInner(Map<String, Socket> clientSockets, String myClientSocket, Game game, BooleanState isRunning) {
        this.clientSockets = clientSockets;
        this.myClientSocket = myClientSocket;
        this.game = game;
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        Socket clientSocket = clientSockets.get(myClientSocket);
        try {
            PrintWriter myOut = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            String inputLine;
            String outputLine = "";
            BooleanState forward = new BooleanState();

            while (this.isRunning.isTrue() && (inputLine = in.readLine()) != null && (!outputLine.contains(loose))) {
                System.out.println("INPUT LINE " + inputLine);
                // mutex aca?
                outputLine = this.game.processCommand(clientSocket.toString(), inputLine, forward);
                printOutput(forward, outputLine, myOut);

                if (outputLine.contains(win)) {
                    this.isRunning.setFalse();
                    break;
                }
            }

            myOut.close();
            in.close();
            this.closeSockets();

        } catch (IOException e) {
            System.err.println("Thread failed");
        }
    }

    private void printOutput(BooleanState forward, String outputLine, PrintWriter myOut) throws IOException {
        if (forward.isTrue()) {
            for (Socket s : clientSockets.values()) {
                PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));
                out.println(outputLine);
                out.flush();
            }
        } else {
            myOut.println(outputLine);
            myOut.flush();
        }
    }

    private void closeSockets() throws IOException {
        this.clientSockets.get(myClientSocket).close();
        this.clientSockets.remove(myClientSocket);
        //this.serverSocket.close();
        //Server.stopGame(this.game.getName());
    }
}
