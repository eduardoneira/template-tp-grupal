package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;
import ar.fiuba.tdd.tp.driver.GameState;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AcceptanceTests {

    GameDriver driver;

    @SuppressWarnings("CPD-START")

    @Before
    public void initialization() throws ClassNotFoundException, IOException,
            GameLoadFailedException, InstantiationException, IllegalAccessException {

        driver = new ConcreteGameDriver();
        driver.initGame("TheEscape");
        driver.sendCommand("create player");
        driver.sendCommand("open doorPasilloToAccesoBiblioteca");
        driver.sendCommand("open doorAccesoToPasillo");
        driver.sendCommand("open doorPasilloToSalon3");
        driver.sendCommand("pick llave");
        driver.sendCommand("open doorSalon3ToPasillo");
    }

    @Test
    public void loseWhenUseEscalera() {

        //It should Lost if download using stairs:
        driver.sendCommand("open doorPasilloToSalon1");
        driver.sendCommand("open cuadroDeBarco");
        driver.sendCommand("open cajaFuerte");
        driver.sendCommand("pick credencial");
        driver.sendCommand("put fotoPlayer credencial");
        driver.sendCommand("open doorSalon1ToPasillo");
        driver.sendCommand("open doorPasilloToAccesoBiblioteca");
        driver.sendCommand("talk bibliotecario");
        driver.sendCommand("open doorAccesoToBiblioteca");
        driver.sendCommand("pick libroViejo");
        driver.sendCommand("open doorBibliotecaToSotano");
        driver.sendCommand("open escalera");
        assert (null == driver.getCurrentState());
    }

    @Test
    public void loseWhenUseBarandaWhitoutHavingAMartillo() {

        //It should Lost if goto basement without a hammer:
        driver.sendCommand("open doorPasilloToSalon1");
        driver.sendCommand("open cuadroDeBarco");
        driver.sendCommand("open cajaFuerte");
        driver.sendCommand("pick credencial");
        driver.sendCommand("put fotoPlayer credencial");
        driver.sendCommand("open doorSalon1ToPasillo");
        driver.sendCommand("open doorPasilloToAccesoBiblioteca");
        driver.sendCommand("talk bibliotecario");
        driver.sendCommand("open doorAccesoToBiblioteca");
        driver.sendCommand("pick libroViejo");
        driver.sendCommand("open doorBibliotecaToSotano");
        driver.sendCommand("open baranda");
        assert (null == driver.getCurrentState());
    }

    @Test
    public void winWhenGoToSotano() {

        //It should Win if goto basement with a hammer:
        driver.sendCommand("open doorPasilloToSalon2");
        driver.sendCommand("pick martillo");
        driver.sendCommand("open doorSalon2ToPasillo");
        driver.sendCommand("open doorPasilloToSalon1");
        driver.sendCommand("open cuadroDeBarco");
        driver.sendCommand("open cajaFuerte");
        driver.sendCommand("pick credencial");
        driver.sendCommand("put fotoPlayer");
        driver.sendCommand("open doorSalon1ToPasillo");
        driver.sendCommand("open doorPasilloToAccesoBiblioteca");
        driver.sendCommand("talk bibliotecario");
        driver.sendCommand("open doorAccesoToBiblioteca");
        driver.sendCommand("pick libroViejo");
        driver.sendCommand("open doorBibliotecaToSotano");
        driver.sendCommand("open baranda");
        driver.sendCommand("open ventana");
        //driver.sendCommand("open Sotano");
        assert (GameState.Won == driver.getCurrentState());
    }
}