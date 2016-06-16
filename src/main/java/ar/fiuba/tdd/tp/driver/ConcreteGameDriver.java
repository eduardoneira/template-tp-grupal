package ar.fiuba.tdd.tp.driver;


import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.Motor;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.random.RNG;
import ar.fiuba.tdd.tp.server.BuilderLoader;
import ar.fiuba.tdd.tp.timedEvent.AbstractTimer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class ConcreteGameDriver implements GameDriver {
    private Game game;
    private BuilderLoader builderLoader;

    public ConcreteGameDriver() {
        builderLoader = new BuilderLoader();
    }

    @Override
    public void initGame(String jarPath) throws GameLoadFailedException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, IOException {
        String path = "build/classes/main/ar/fiuba/tdd/tp/";
        GameBuilder gameBuilder = builderLoader.load(path.concat(jarPath).concat(".jar"));
        game = gameBuilder.build();
        if (game == null) {
            throw new GameLoadFailedException();
        }
    }

    @Override
    public void start() {
        game.start();
    }

    @Override
    public String sendCommand(String cmd) {
        // TODO: revisar
        return game.processCommand(" ", cmd, new BooleanState());
    }

    @Override
    public String sendCommandByPlayer(String playerId, String cmd) {
        // TODO: revisar
        return game.processCommand(playerId, cmd, new BooleanState());
    }

    @Override
    public GameState getCurrentState() {

        return game.getCurrentState();
    }

    @Override
    public GameState getCurrentStateByPlayer(String playerId) {

        return game.getCurrentState(playerId);
    }

    @Override
    public void setTimer(AbstractTimer timer) {
        game.setTimer(timer);
    }

    @Override
    public void setRandom(RNG random) {
        game.setRandom(random);
    }

    @Override
    public void close() {
        game.close();
    }
}
