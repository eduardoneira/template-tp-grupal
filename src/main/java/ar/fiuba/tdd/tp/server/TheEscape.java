package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingDoor;
import sun.awt.image.ImageWatched;

/**
 * Created by eduardo.neira on 19/05/2016.
 */
public class TheEscape extends Game {

    private Room pasillo;
    private LinkingDoor pasilloToSalon1;
    private LinkingDoor pasilloToSalon2;
    private LinkingDoor pasilloToSalon3;
    private LinkingDoor pasilloToBibliotecaAcceso;


    private Room salon1;
    private LinkingDoor salon1ToPasillo;

    private Room salon2;
    private LinkingDoor salon2ToPasillo;

    private Room salon3;
    private LinkingDoor salon3ToPasillo;

    private Room bibliotecaAcceso;
    private LinkingDoor bibliotecaAccesoToPasillo;
    private LinkingDoor bibliotecaAccesoToBiblioteca;

    private Room biblioteca;
    private LinkingDoor bibliotecaToBibliotecaAcceso;
    private LinkingDoor bibliotecaToSotano;

    private Room sotano;
    private LinkingDoor sotanoToBiblioteca;
    private LinkingDoor sotanoToSotanoAbajo;

    private Room sotanoAbajo;
    private LinkingDoor sotanoAbajoToAfuera;

    private Room afuera;


    public TheEscape(String name) {
        super(name);
    }

    @Override
    public boolean checkWinCondition() {
        return false;
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }

    @Override
    public Game build() {
        createRooms();
        return null;
    }


    private void createRooms() {
        salon1 = new Room("salon1");
        objects.put(salon1.getName(), salon1);

        salon2 = new Room("salon2");
        objects.put(salon2.getName(), salon2);

        salon3 = new Room("salon3");
        objects.put(salon3.getName(), salon3);

        pasillo = new Room("pasillo");
        objects.put(pasillo.getName(), pasillo);

        bibliotecaAcceso = new Room("bibliotecaAcceso");
        objects.put(bibliotecaAcceso.getName(), bibliotecaAcceso);

        biblioteca = new Room("biblioteca");
        objects.put(biblioteca.getName(),biblioteca);

        sotano = new Room("sotano");
        objects.put(sotano.getName(),sotano);

        sotanoAbajo = new Room("sotanoAbajo");
        objects.put(sotanoAbajo.getName(),sotanoAbajo);

        afuera = new Room("afuera");
        objects.put(afuera.getName(),afuera);
    }


}
