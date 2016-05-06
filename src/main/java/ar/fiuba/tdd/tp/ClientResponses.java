package ar.fiuba.tdd.tp;

public interface ClientResponses {
    String HOSTNAME = "localhost";

    String CONNECTTOREGEX = "connect to [0-9]+";
    String CONNECTTO = "connect to";
    String EXIT = "exit game";
    String EXITSUCCESSFUL = "Exit " + "successful!";
    String CONNECTIONSUCCESFUL = "Connected! Use command 'help' if you don't know what to do";

    String ERRORCONNECTION = "Must connect to a valid port using command 'connect to [port]'";
    String IOERROR = "Couldn't get I/O for the connection";

}
