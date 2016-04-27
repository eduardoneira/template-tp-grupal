package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.*;

public class Client {
    //TODO: refactorizar en clases e interfaces
    private static String HOSTNAME = "localhost";

    private static String CONNECTTOREGEX = "connect to [0-9]+";
    private static String CONNECTTO = "connect to";
    private static String EXIT = "exit game";
    private static String EXITSUCCESSFUL = "Exit " + "successful!";
    private static String CONNECTIONSUCCESFUL = "Connected!";

    private static String ERRORCONNECTION = "Must connect to a valid port using command 'connect to [port]'";
    private static String IOERROR = "Couldn't get I/O for the connection";

    public static void main(String[] args) throws IOException {

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
        String input = in.readLine().toLowerCase();
        boolean connected = false;
        while ( !connected) {
            if (input.matches(CONNECTTOREGEX)) {
                try {
                    socket = new Socket(HOSTNAME, Integer.valueOf(input.replace(CONNECTTO.concat(" "),"")));
                    System.out.println(CONNECTIONSUCCESFUL);
                    connected = true;
                } catch (Exception e) {
                    System.out.println(ERRORCONNECTION);
                    input = in.readLine().toLowerCase();
                }
            } else {
                System.out.println(ERRORCONNECTION);
                input = in.readLine().toLowerCase();
            }
        }
        return socket;
    }

    private static void playGame(PrintWriter out, BufferedReader in, BufferedReader stdIn) throws IOException {
        String fromServer;
        String fromUser = stdIn.readLine();

        while (!fromUser.equals(EXIT)) {
            System.out.println("Client: " + fromUser);
            out.println(fromUser);
            out.flush();

            fromServer = in.readLine();
            if (fromServer != null) {
                System.out.println("Server: " + fromServer);
            }
            fromUser = stdIn.readLine();
        }
    }

}
