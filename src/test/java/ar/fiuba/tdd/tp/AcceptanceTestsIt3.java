package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;
import ar.fiuba.tdd.tp.driver.GameState;

import ar.fiuba.tdd.tp.random.RandomDummy;
import ar.fiuba.tdd.tp.timedEvent.AbstractTimer;
import ar.fiuba.tdd.tp.timedEvent.ControllableInstantTimerDummy;
import ar.fiuba.tdd.tp.timedEvent.InstantTimerDummy;
import org.junit.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;

public class AcceptanceTestsIt3 {

    GameDriver driver;

    @SuppressWarnings("CPD-START")

    /*@Before
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
    }*/

    @Test
    public void escenario1() throws IllegalAccessException, InterruptedException, IOException,
            InstantiationException, GameLoadFailedException, ClassNotFoundException {
        assert (p1LooseWhenCaughtInBiblioteca());
    }

    @Test
    public void escenario2() throws IllegalAccessException, InterruptedException, IOException,
            InstantiationException, GameLoadFailedException, ClassNotFoundException {
        assert (p1LooseWhenCaughtInPasilloButNotP2());
    }

    public boolean p1LooseWhenCaughtInBiblioteca() throws InterruptedException, ClassNotFoundException,
            IOException, GameLoadFailedException, InstantiationException, IllegalAccessException {

        driver = new ConcreteGameDriver();
        driver.initGame("TheEscape2");
        ControllableInstantTimerDummy timer = new ControllableInstantTimerDummy();
        driver.setTimer(timer);
        driver.start();
        driver.sendCommandByPlayer("p1", "create player");
        driver.sendCommandByPlayer("p1", "open doorPasilloToSalon1");
        driver.sendCommandByPlayer("p1", "pick licor");
        driver.sendCommandByPlayer("p1", "open doorSalon1ToPasillo");
        driver.sendCommandByPlayer("p1", "open doorPasilloToAccesoBiblioteca");

        driver.sendCommandByPlayer("p2", "create player");
        driver.sendCommandByPlayer("p2", "open doorPasilloToAccesoBiblioteca");

        driver.setRandom(new RandomDummy(4));
        driver.sendCommandByPlayer("p1", "talk bibliotecario");
        System.out.println(driver.sendCommandByPlayer("p1", "open doorAccesoToBiblioteca"));
        timer.passTime();
        System.out.println(driver.sendCommandByPlayer("p1", "open doorBibliotecaToAcceso"));

        driver.close();

        /*GameState stateP1;
        while ((stateP1 = driver.getCurrentStateByPlayer("p1")) != GameState.Lost && stateP1 != null) {
            yield();
        }*/

        return (null == driver.getCurrentStateByPlayer("p1") || GameState.Lost == driver.getCurrentStateByPlayer("p1"));
    }

    public boolean p1LooseWhenCaughtInPasilloButNotP2() throws InterruptedException, ClassNotFoundException,
            IOException, GameLoadFailedException, InstantiationException, IllegalAccessException {

        driver = new ConcreteGameDriver();
        driver.initGame("TheEscape2");
        ControllableInstantTimerDummy timer = new ControllableInstantTimerDummy();
        System.out.println("cambiando timer");
        driver.setTimer(timer);
        System.out.println("timer cambiado");
        driver.start();
        driver.sendCommandByPlayer("p1", "create player");
        driver.sendCommandByPlayer("p1", "open doorPasilloToSalon1");
        driver.sendCommandByPlayer("p1", "pick licor");
        driver.sendCommandByPlayer("p1", "open doorSalon1ToPasillo");
        driver.sendCommandByPlayer("p1", "open doorPasilloToAccesoBiblioteca");

        driver.sendCommandByPlayer("p2", "create player");
        driver.sendCommandByPlayer("p2", "open doorPasilloToAccesoBiblioteca");
        driver.setRandom(new RandomDummy(3));
        driver.sendCommandByPlayer("p1", "talk bibliotecario");
        System.out.println(driver.sendCommandByPlayer("p1", "open doorAccesoToBiblioteca"));
        System.out.println(driver.sendCommandByPlayer("p2", "open doorAccesoToBiblioteca"));
        timer.passTime();
        System.out.println(driver.sendCommandByPlayer("p1", "open doorAccesoToPasillo"));

        // TODO: como se cuelga con driver.close(), a veces queda abierto el ActionGeneration del test anterior
        // TODO: y esto hace que el bibliotecario se mueva siempre al accesoBiblioteca en vez de al pasillo
        // TODO: descomentar esta linea para chequear
        System.out.println(driver.sendCommandByPlayer("p2", "look pasillo"));
        System.out.println(driver.sendCommandByPlayer("p2", "look pasillo"));

        driver.close();

        /*GameState stateP1;
        while ((stateP1 = driver.getCurrentStateByPlayer("p1")) != GameState.Lost  && stateP1 != null) {
            yield();
        }*/

        return (GameState.InProgress == driver.getCurrentStateByPlayer("p2"))
            && (null == driver.getCurrentStateByPlayer("p1") || GameState.Lost == driver.getCurrentStateByPlayer("p1"));
    }
}