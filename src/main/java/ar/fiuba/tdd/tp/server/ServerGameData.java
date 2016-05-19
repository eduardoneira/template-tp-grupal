package ar.fiuba.tdd.tp.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerGameData {
    private static int nextFreePort = 8081;

    private int port;
    private String name;
    private ClientWorker worker;

    public ServerGameData( String name) {
        super();
        this.port = 0;
        this.name = name;
        this.worker = null;
    }

    public int getPort() {
        return port;
    }

    public void startGame() throws IOException {
        this.port = nextFreePort;
        this.worker = new ClientWorker(new ServerSocket(nextFreePort), this.name);
        Thread thread = new Thread(worker);
        thread.start();

    }

    public static void increasePort() {
        nextFreePort += 1;
    }

    public boolean isRunning() {
        return (port != 0);
    }
    
    public void stopGame() {
        if (this.worker != null) {
            this.worker.kill();
        }
        this.port = 0;
        this.worker = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
}
