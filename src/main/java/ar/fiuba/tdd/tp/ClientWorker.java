package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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

        try {
            clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            String inputLine;
            String outputLine;
            inputLine = in.readLine();
            while (inputLine != null ) {
                System.out.println("INPUT LINE " + inputLine);
                outputLine = game.processComand(inputLine);
                out.println(outputLine);
                out.flush();
                /// TODO: esto es viejo, deberia cambiar pero deberia estar en algun lado la condicion de victoria
                if (outputLine.equals("ganaste")) {
                    break;
                }
                inputLine = in.readLine();
            }
            out.close();
            in.close();
            clientSocket.close();

        } catch (IOException e) {
            System.err.println("Thread failed");
        }
    }
}
