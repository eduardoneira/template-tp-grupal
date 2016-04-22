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
    ClientWorker(ServerSocket server) {
        this.serverSocket = server;
        game = new Game("");
    }

    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            clientSocket = serverSocket.accept();
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

            //System.out.println("Entrando al loop de input/output");
            String inputLine;
            String outputLine;
            while ((inputLine = in.readLine()) != null) {
                outputLine = game.processComand(inputLine);
                out.println(outputLine);
                /// TODO: esto es viejo, deberia cambiar pero deberia estar en algun lado la condicion de victoria
                if (outputLine.equals("ganaste")) {
                    break;
                }
            }

            out.close();
            in.close();
            clientSocket.close();

        } catch (IOException e) {
            System.err.println("thread failed");
        }
    }
}
