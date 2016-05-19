package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.server.BuilderLoader;

public class Main {
    public static void main(String[] args) throws Exception {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("C:\\Users\\eduardo.neira\\TDD\\template-tp-grupal\\build\\classes\\main\\ar\\fiuba\\tdd\\tp\\FetchQuest.jar");
        driver.sendCommand("pick stick");
    }
}
