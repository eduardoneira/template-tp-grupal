package ar.fiuba.tdd.tp;
/**
 * Created by Master on 20/04/2016.
 */

import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] args) throws IOException {

        /*if (args.length != 1) {
            System.err.println(
                    "Usage: java Server <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);*/

        int portNumber = 8081;

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromUser;

        while((fromUser = stdIn.readLine()) != null) {

            // TODO: refactorizar
            String[] tokens = fromUser.split(" ");
            if(tokens.length != 3 || !tokens[0].equals("load") || !tokens[1].equals("game")){
                System.out.println("Error: Invalid command");
                continue;
            }

            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(portNumber);
            } catch (IOException e) {
                System.err.println("Could not listen on port: 4444");
                System.exit(-1);
            }

            ClientWorker w = new ClientWorker(serverSocket);
            Thread t = new Thread(w);
            t.start();

            System.out.println(tokens[2] + " loaded and listening on port " + portNumber);
            ++portNumber;
        }

        //serverSocket.close();
    }
}