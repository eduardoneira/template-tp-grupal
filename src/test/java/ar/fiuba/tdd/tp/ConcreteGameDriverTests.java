package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;
import ar.fiuba.tdd.tp.driver.GameState;
import org.junit.Test;


public class ConcreteGameDriverTests {
    @Test
    public void fetchquestTest() throws GameLoadFailedException {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("Fetch Quest");
        driver.sendCommand("pick stick");
        assert(GameState.Won == driver.getCurrentState());
    }
}
