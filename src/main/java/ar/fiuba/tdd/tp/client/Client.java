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
            //System.out.println("Client: " + fromUser);
            out.println(fromUser);
            out.flush();

            // TODO: REVISAR ESTOOOO, es re cabeza
            try {

                // si hay algo para leer, lo leo (esto representaria updates de cosas que hicieron otros jugadores)
                while (in.ready() && (fromServer = in.readLine()) != null) {
                    System.out.println(/*"Server: " +*/ fromServer);
                }

                // leo el resultado de mi accion. En general se llega aca antes que el server procese la accion, por eso funciona
                // pero si el server la procesa antes y la leo en el while anterior, aca me voy a quedar clavado esperando a que llegue input
                // por eso el socketTimeout de 1 segundo
                fromServer = in.readLine();
                System.out.println(/*"Server: " +*/ fromServer);

            } catch (IOException e) {
                // si recibo una SocketTimeout es porque ya habia leido lo que tenia para leer y me quede clavado
                // en cambio si llega una IOException que no es SocketTimeout, es pq se cerro el juego
                if(!(e instanceof  SocketTimeoutException)) {
                    System.out.println("Connection to server ended");
                    break;
                }
            }
        }
    }
}
