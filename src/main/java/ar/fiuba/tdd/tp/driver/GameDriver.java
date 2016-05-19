package ar.fiuba.tdd.tp.driver;

import java.io.IOException;

public interface GameDriver {
    void initGame(String jarPath) throws GameLoadFailedException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException;

    String sendCommand(String cmd);

    GameState getCurrentState();
}