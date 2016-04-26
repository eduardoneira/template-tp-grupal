package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {

        /*if (args.length != 2) {
            System.err.println(
                    "Usage: java Client <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);*/
        /// TODO: harcodeo aca a que puerto se conecta, falta completar el cliente con sus comandos
        String hostName = "localhost";
        int portNumber = 8081;

        Socket socket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        try {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
            String fromServer;
            String fromUser;

            //out.println("empezamos");
            while ((fromUser = stdIn.readLine()) != null) {
                System.out.println("Client: " + fromUser);
                out.println(fromUser);
                out.flush();

                fromServer = in.readLine();
                if (fromServer != null) {
                    System.out.println("Server: " + fromServer);
                    // TODO: esto es viejo, actualizarlo
                    if (fromServer.equals("ganaste")) {
                        break;
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
