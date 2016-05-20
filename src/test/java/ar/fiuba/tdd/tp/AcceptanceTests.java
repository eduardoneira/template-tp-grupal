package ar.fiuba.tdd.tp;


import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;
import ar.fiuba.tdd.tp.driver.GameState;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AcceptanceTests {
    @SuppressWarnings("CPD-START")

    @Test
    public void loseWhenUseEscalera() throws ClassNotFoundException, IOException,
            GameLoadFailedException, InstantiationException, IllegalAccessException {
    //It should Lost if download using stairs:
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("TheEscape");
        driver.sendCommand("open doorPasilloToAccesoBiblioteca");
        driver.sendCommand("open doorAccesoToPasillo");
        driver.sendCommand("open doorPasilloToSalon3");
        driver.sendCommand("pick llave");
        driver.sendCommand("open doorSalon3ToPasillo");
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
        assert (GameState.Lost == driver.getCurrentState());
    }

    @Test
    public void loseWhenUseBarandaWhitoutHavingAMartillo() throws ClassNotFoundException, IOException,
            GameLoadFailedException, InstantiationException, IllegalAccessException {
        //It should Lost if goto basement without a hammer:
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("TheEscape");
        driver.sendCommand("open doorPasilloToAccesoBiblioteca");
        driver.sendCommand("open doorAccesoToPasillo");
        driver.sendCommand("open doorPasilloToSalon3");
        driver.sendCommand("pick llave");
        driver.sendCommand("open doorSalon3ToPasillo");
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
        assert (GameState.Lost == driver.getCurrentState());
    }

    @Test
    public void winWhenGoToSotano() throws ClassNotFoundException, IOException,
        GameLoadFailedException, InstantiationException, IllegalAccessException {
        //It should Win if goto basement with a hammer:


        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("TheEscape");
        driver.sendCommand("open doorPasilloToAccesoBiblioteca");
        driver.sendCommand("open doorAccesoToPasillo");
        driver.sendCommand("open doorPasilloToSalon3");
        driver.sendCommand("pick llave");
        driver.sendCommand("open doorSalon3ToPasillo");
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