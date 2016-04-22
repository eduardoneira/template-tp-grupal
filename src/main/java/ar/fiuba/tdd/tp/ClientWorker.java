package ar.fiuba.tdd.tp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    public void run(){

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //System.out.println("Entrando al loop de input/output");
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                outputLine = game.processComand(inputLine);
                out.println(outputLine);
                if (outputLine.equals("ganaste"))
                    break;
            }

            out.close();
            in.close();
            clientSocket.close();

        } catch (IOException e) {
            System.err.println("thread failed");
        }
    }
}
