package ar.fiuba.tdd.tp.command;

import ar.fiuba.tdd.tp.Server;
import ar.fiuba.tdd.tp.ServerGameData;

import java.io.IOException;

import java.util.Map;

public class LoadGameServerCommand implements AbstractCommand {

    private final String loadSuccessful = "Game loaded and listening on port ";
    private final String gameRunning = "Can't load game, game is running on port ";
    private final String exception = "Can't start game due to exception";

    public void handle(Object[] param) {
        Map<String,ServerGameData> map = Server.getGamesData();
        String gameName = (String) param[0];
        ServerGameData game = map.get(gameName.toLowerCase());
        if (game.isRunning()) {
            System.out.println(gameRunning.concat(String.valueOf(game.getPort())));
        } else {
            try {
                game.startGame();
                Server.setGamesData(map);
                System.out.println(loadSuccessful.concat(String.valueOf(game.getPort())));
            } catch (IOException e) {
                System.out.println(exception);
            }
        }
    }
}
