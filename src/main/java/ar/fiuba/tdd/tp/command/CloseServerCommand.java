package ar.fiuba.tdd.tp.command;

import ar.fiuba.tdd.tp.server.Server;
import ar.fiuba.tdd.tp.server.ServerGameData;

import java.util.Map;



public class CloseServerCommand implements AbstractCommand {
    private static final String closingServer = "Closing Server. Bye";

    @Override
    public void handle(Object[] param) {
        Map<String,ServerGameData> map = Server.getGamesData();
        for (Map.Entry<String,ServerGameData> key : map.entrySet()) {
            key.getValue().stopGame();
        }
        Server.setGamesData(map);
        Server.stopServer();
        System.out.println(closingServer);
    }
}
