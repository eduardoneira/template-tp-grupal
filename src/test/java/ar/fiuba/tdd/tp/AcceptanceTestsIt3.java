package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;
import ar.fiuba.tdd.tp.driver.GameState;

import ar.fiuba.tdd.tp.random.RandomDummy;
import ar.fiuba.tdd.tp.timedevent.InstantTimerDummy;
import org.junit.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;

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

    //@Test
    public void escenario1trucho() throws IllegalAccessException, InterruptedException, IOException,
            InstantiationException, GameLoadFailedException, ClassNotFoundException {
        boolean result = false;
        for (int i = 0; i < 50; ++i) {
            result |= p1LooseWhenCaughtInBiblioteca();
        }
        assert (result);
    }

    @Test
    public void escenario2() throws IllegalAccessException, InterruptedException, IOException,
            InstantiationException, GameLoadFailedException, ClassNotFoundException {
        assert (p1LooseWhenCaughtInPasilloButNotP2());
    }

    //@Test
    public void escenario2trucho() throws IllegalAccessException, InterruptedException, IOException,
            InstantiationException, GameLoadFailedException, ClassNotFoundException {
        boolean result = false;
        for (int i = 0; i < 50; ++i) {
            result |= p1LooseWhenCaughtInPasilloButNotP2();
        }
        assert (result);
    }

    public boolean p1LooseWhenCaughtInBiblioteca() throws InterruptedException, ClassNotFoundException,
            IOException, GameLoadFailedException, InstantiationException, IllegalAccessException {

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

        driver.setRandom(new RandomDummy(4));
        driver.sendCommandByPlayer("p1", "talk bibliotecario");
        driver.sendCommandByPlayer("p1", "open doorAccesoToBiblioteca");
        driver.sendCommandByPlayer("p1", "open doorBibliotecaToAcceso");
        //System.out.println(driver.sendCommandByPlayer("p1", "look accesoBiblioteca"));
        GameState state = driver.getCurrentStateByPlayer("p1");
        //driver.close();
        return (GameState.Lost == state || null == state);
    }

    public boolean p1LooseWhenCaughtInPasilloButNotP2() throws InterruptedException, ClassNotFoundException,
            IOException, GameLoadFailedException, InstantiationException, IllegalAccessException {

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

        driver.setRandom(new RandomDummy(3));
        driver.sendCommandByPlayer("p1", "talk bibliotecario");
        driver.sendCommandByPlayer("p1", "open doorAccesoToBiblioteca");
        driver.sendCommandByPlayer("p2", "open doorAccesoToBiblioteca");
        driver.sendCommandByPlayer("p1", "open doorAccesoToPasillo");
        driver.sendCommandByPlayer("p1", "look pasillo");
        //assert (GameState.Lost == driver.getCurrentStateByPlayer("p1")
        // || null == driver.getCurrentStateByPlayer("p1")); // para el jugador1
        //assert (GameState.InProgress == driver.getCurrentStateByPlayer("p2")); // para el jugador2
        GameState stateP1 = driver.getCurrentStateByPlayer("p1");
        GameState stateP2 = driver.getCurrentStateByPlayer("p2");
        //driver.close();
        return (GameState.Lost == stateP1 || null == stateP1)
                && (GameState.InProgress == stateP2);
    }
}