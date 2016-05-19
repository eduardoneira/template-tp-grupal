package ar.fiuba.tdd.tp.driver;


import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.Motor;
import ar.fiuba.tdd.tp.server.BuilderLoader;

import java.io.IOException;

public class ConcreteGameDriver implements GameDriver {
    private Game game;
    private BuilderLoader builderLoader;

    public ConcreteGameDriver() {
        builderLoader = new BuilderLoader();
    }

    @Override
    public void initGame(String jarPath) throws GameLoadFailedException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, IOException {
        GameBuilder gameBuilder = builderLoader.load(jarPath);
        game = gameBuilder.build();
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
