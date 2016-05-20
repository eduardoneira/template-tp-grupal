package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.Box;
import ar.fiuba.tdd.tp.objects.concrete.Chest;
import ar.fiuba.tdd.tp.objects.concrete.GeneralMovableObject;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingDoor;
import ar.fiuba.tdd.tp.objects.concrete.Key;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

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
    private LinkingDoor baranda;
    private LinkingDoor sotanoToCuartoDeLaMuerte;


    private Room sotanoAbajo;
    private LinkingDoor sotanoAbajoToAfuera;
    private LinkingDoor sotanoAbajoToCuartoDeLaMuerte;

    private Room afuera;

    private Room CuartoDeLosMuertos;

    private GeneralMovableObject destornillador1;
    private GeneralMovableObject destornillador2;
    private ConcreteGameObjectWithParentAndChildren mesa;
    private GeneralMovableObject cuadroDeTren;
    private GeneralMovableObject silla1;
    private GeneralMovableObject silla2;

    private GeneralMovableObject libro1;
    private GeneralMovableObject libro2;
    private GeneralMovableObject libro3;
    private GeneralMovableObject libro4;
    private GeneralMovableObject libro5;
    private GeneralMovableObject libro6;
    private GeneralMovableObject libro7;
    private GeneralMovableObject libro8;
    private GeneralMovableObject libro9;

    private GameObjectWithChildren estante;

    private GeneralMovableObject vaso1;
    private GeneralMovableObject vaso2;
    private GeneralMovableObject licor;

    private GeneralMovableObject lapicera;
    private ConcreteGameObjectWithParentAndChildren foto;
    private Chest cajaFuerte;
    private Box cuadroBarco;

    private Key martillo;
    private Key llave;

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
        createMisc();
        player.setParent(pasillo);
        return null;
    }

    @SuppressWarnings("CPD-START")

    private void createMisc() {


        //pasillo
        foto = new ConcreteGameObjectWithParentAndChildren("foto",pasillo);
        lapicera = new GeneralMovableObject("lapicera",pasillo);

        //Cuarto1
        mesa = new ConcreteGameObjectWithParentAndChildren("mesa",salon1 );
        vaso1 = new  GeneralMovableObject("vaso1",salon1);
        vaso2 = new  GeneralMovableObject("vaso2",salon1);
        licor = new  GeneralMovableObject("licor",salon1);
        silla1 = new GeneralMovableObject("silla1",salon1);
        silla2 = new GeneralMovableObject("silla2",salon2);
        cuadroDeTren = new GeneralMovableObject("cuadroDeTren",salon1);
        cuadroBarco = new Box("cuadro",salon1);
        cajaFuerte = new Chest ("cajaFuerte",salon1);

        //salon2
        destornillador1 = new GeneralMovableObject("destornillador1",salon2);
        destornillador2 = new GeneralMovableObject("destornillador2",salon2);

        //salon3
        llave = new Key("llave",salon3,1);

        //biblioteca
        estante = new ConcreteGameObjectWithParentAndChildren("estante",biblioteca);


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
