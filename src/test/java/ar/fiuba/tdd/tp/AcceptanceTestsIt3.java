package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;
import ar.fiuba.tdd.tp.driver.GameState;

import ar.fiuba.tdd.tp.timedevent.AbstractTimer;
import ar.fiuba.tdd.tp.timedevent.InstantTimerDummy;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class AcceptanceTestsIt3 {

    GameDriver driver;

    @SuppressWarnings("CPD-START")

    @Before
    public void initialization() throws ClassNotFoundException, IOException,
            GameLoadFailedException, InstantiationException, IllegalAccessException {

        driver = new ConcreteGameDriver();
        driver.initGame("TheEscape2");
        driver.setTimer(new InstantTimerDummy());
        driver.sendCommandByPlayer("p1", "create player");
        driver.sendCommandByPlayer("p1", "open doorPasilloToSalon1");
        driver.sendCommandByPlayer("p1", "pick licor");
        driver.sendCommandByPlayer("p1", "open doorSalon1ToPasillo");
        driver.sendCommandByPlayer("p1", "open doorPasilloToAccesoBiblioteca");

        driver.sendCommandByPlayer("p2", "create player");
        driver.sendCommandByPlayer("p2", "open doorPasilloToAccesoBiblioteca");
    }

    @Test
    public void P1LooseWhenCaughtInBiblioteca() {
        assert (GameState.InProgress == driver.getCurrentStateByPlayer("p1"));
        System.out.println(driver.sendCommandByPlayer("p1", "talk bibliotecario"));
        System.out.println(driver.sendCommandByPlayer("p1", "open doorAccesoToBiblioteca"));
        System.out.println(driver.sendCommandByPlayer("p1", "open doorBibliotecaToAcceso"));
        //System.out.println(driver.sendCommandByPlayer("p1", "look pasillo"));
        //System.out.println(driver.sendCommandByPlayer("p1", "open doorAccesoToPasillo"));
        //System.out.println(driver.sendCommandByPlayer("p1", "look pasillo"));
        GameState state = driver.getCurrentStateByPlayer("p1");
        assert (GameState.Lost == state || null == state);
    }

    @Test
    public void P1LooseWhenCaughtInPasilloButNotP2() {
        driver.sendCommandByPlayer("p1", "talk bibliotecario");
        System.out.println(driver.sendCommandByPlayer("p1", "open doorAccesoToBiblioteca"));
        System.out.println(driver.sendCommandByPlayer("p2", "open doorAccesoToBiblioteca"));
        //driver.sendCommandByPlayer("p2", "open doorBibliotecaToAcceso");
        System.out.println(driver.sendCommandByPlayer("p1", "open doorAccesoToPasillo"));
        System.out.println(driver.sendCommandByPlayer("p2", "look pasillo"));
        assert (GameState.Lost == driver.getCurrentStateByPlayer("p2") || null == driver.getCurrentStateByPlayer("p2")); // para el jugador2
        assert (GameState.InProgress == driver.getCurrentStateByPlayer("p1")); // para el jugador1
    }
}