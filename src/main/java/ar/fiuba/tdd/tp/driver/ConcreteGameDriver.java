package ar.fiuba.tdd.tp.driver;


import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.Motor;
import ar.fiuba.tdd.tp.server.BuilderLoader;

import java.io.File;
import java.io.IOException;
import java.time.Clock;

public class ConcreteGameDriver implements GameDriver {
    private Game game;
    private Motor motor;

    public ConcreteGameDriver() {
        motor = new Motor();
    }

    @Override
    public void initGame(String jarPath) throws GameLoadFailedException {
        //TODO: cambiar esto cuando este implementada la carga de un Game desde un jar
        game = motor.createGame(jarPath);

        if (game == null) {
            throw new GameLoadFailedException();
        }
    }

    @Override
    public String sendCommand(String cmd) {

        return game.processCommand(cmd);

    }

    @Override
    public GameState getCurrentState() {
        return game.getCurrentState();
    }
}
