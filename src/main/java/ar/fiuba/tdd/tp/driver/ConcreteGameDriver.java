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
        System.out.print("1");
        game = motor.createGame(jarPath);
        System.out.print("2");
        BuilderLoader matiPrueba = new BuilderLoader();
        try {
            System.out.print("3");
            matiPrueba.load("build/classes/main/ar/fiuba/tdd/tp/FetchQuest.jar");
            System.out.print("4");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        if (game == null) {
            System.out.print("AUXILIOERRORJARKLALAL");
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
