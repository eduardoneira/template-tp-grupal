package ar.fiuba.tdd.tp.client;

import java.io.*;
import java.net.*;

public class Client implements ClientResponses {

    public static void main(String[] args)  throws IOException {

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        Socket socket = makeConnection(stdIn);
        while (socket != null) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

            try {
                playGame(out,in,stdIn);
                socket.close();
                System.out.println(EXITSUCCESSFUL);
            } catch (IOException e) {
                System.err.println(IOERROR);
                System.exit(2);
            }
            socket = makeConnection(stdIn);
        }
    }

    private static Socket makeConnection(BufferedReader in) throws IOException {

        Socket socket = null;
        String input = in.readLine();
        boolean connected = false;
        while ( !connected) {
            if (input.toLowerCase().matches(CONNECTTOREGEX)) {
                try {
                    socket = new Socket(HOSTNAME, Integer.parseInt(input.toLowerCase().replace(CONNECTTO.concat(" "),"")));
                    System.out.println(CONNECTIONSUCCESFUL);
                    connected = true;
                } catch (IOException e) {
                    System.out.println(ERRORCONNECTION);
                    input = in.readLine();
                }
            } else {
                System.out.println(ERRORCONNECTION);
                input = in.readLine();
            }
        }
        return socket;
    }

    private static void playGame(PrintWriter out, BufferedReader in, BufferedReader stdIn) throws IOException {
        String fromServer;
        String fromUser = "";

        while (!EXIT.equals(fromUser)) {
            fromUser = stdIn.readLine();
            System.out.println("Client: " + fromUser);
            out.println(fromUser);
            out.flush();

            try {
                fromServer = in.readLine();
            } catch (IOException e) {
                System.out.println("Connection to server ended");
                break;
            }

            if (fromServer != null) {
                System.out.println("Server: " + fromServer);
            }
        }
    }
}
