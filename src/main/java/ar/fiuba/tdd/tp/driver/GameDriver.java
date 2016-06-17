package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.random.RNG;
import ar.fiuba.tdd.tp.timedevent.AbstractTimer;
import ar.fiuba.tdd.tp.timedevent.InstantTimerDummy;

import java.io.IOException;

public interface GameDriver {

    void initGame(String jarPath) throws GameLoadFailedException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, IOException;

    String sendCommand(String cmd);

    String sendCommandByPlayer(String playerId, String cmd);


    GameState getCurrentState();

    GameState getCurrentStateByPlayer(String playerId);

    void setTimer(AbstractTimer timer);

    void setRandom(RNG random);

    void start();

    void close();
}