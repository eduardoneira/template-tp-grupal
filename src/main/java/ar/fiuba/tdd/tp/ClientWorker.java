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
    private FetchQuest game;

    //Constructor
    ClientWorker(ServerSocket server) {
        this.serverSocket = server;
        game = new FetchQuest();
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
            inputLine = in.readLine();
            while (inputLine != null) {
                System.out.println("INPUT LINE "+inputLine);
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
            System.err.println("thread failed");
        }
    }
}
