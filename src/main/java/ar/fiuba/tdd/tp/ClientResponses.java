package ar.fiuba.tdd.tp;

public interface ClientResponses {
    final String HOSTNAME = "localhost";

    final String CONNECTTOREGEX = "connect to [0-9]+";
    final String CONNECTTO = "connect to";
    final String EXIT = "exit game";
    final String EXITSUCCESSFUL = "Exit " + "successful!";
    final String CONNECTIONSUCCESFUL = "Connected!";

    final String ERRORCONNECTION = "Must connect to a valid port using command 'connect to [port]'";
    final String IOERROR = "Couldn't get I/O for the connection";

}
