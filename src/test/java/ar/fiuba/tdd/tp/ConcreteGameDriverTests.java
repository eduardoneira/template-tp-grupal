package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;
import ar.fiuba.tdd.tp.driver.GameState;
import org.junit.Test;

import java.io.IOException;


public class ConcreteGameDriverTests {
    @Test
    public void fetchquestTest() throws GameLoadFailedException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {

        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/FetchQuest.jar");
        driver.sendCommand("pick stick");
        assert(GameState.Won == driver.getCurrentState());

    }
}
