package ar.fiuba.tdd.tp;


import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;
import ar.fiuba.tdd.tp.driver.GameState;
import org.junit.Test;

import java.io.IOException;

public class AcceptanceTests {
    @Test
    public void loseWhenUseEscalera() throws ClassNotFoundException, IOException,
            GameLoadFailedException, InstantiationException, IllegalAccessException {
    //It should Lost if download using stairs:
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte using Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto in Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial in Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Escalera");
        assert(GameState.Lost == driver.getCurrentState());
    }

    @Test
    public void loseWhenUseBarandaWhitoutHavingAMartillo() throws ClassNotFoundException, IOException,
            GameLoadFailedException, InstantiationException, IllegalAccessException {
        //It should Lost if goto basement without a hammer:
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte using Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto in Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial in Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Baranda");
        assert(GameState.Lost == driver.getCurrentState());
    }

    @Test
    public void winWhenGoToSotano() throws ClassNotFoundException, IOException,
        GameLoadFailedException, InstantiationException, IllegalAccessException {
        //It should Win if goto basement with a hammer:
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon2");
        driver.sendCommand("pick Martillo");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte using Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto in Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial in Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Baranda");
        driver.sendCommand("break Ventana using Martillo");
        driver.sendCommand("goto Sotano");
        assert(GameState.Won == driver.getCurrentState());
    }
}
