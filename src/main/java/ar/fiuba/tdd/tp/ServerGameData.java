package ar.fiuba.tdd.tp;

public class ServerGameData {

    private int port;
    private String name;

    public ServerGameData( String name) {
        super();
        this.port = 0;
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isRunning() {
        return port != 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
