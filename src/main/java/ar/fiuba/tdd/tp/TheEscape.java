package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.*;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingDoor;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingLockedDoor;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.LinkedList;
import java.util.List;

public class TheEscape extends Game {
    @SuppressWarnings("CPD-START")

    // pasillo
    private Room pasillo;

    private LinkingDoor pasilloToSalon1;
    private LinkingDoor pasilloToSalon2;
    private LinkingDoor pasilloToSalon3;
    private LinkingDoor pasilloToBibliotecaAcceso;

    // salon 1
    private Room salon1;

    private LinkingDoor salon1ToPasillo;

    private ConcreteGameObjectWithParentAndChildren mesa;
    private GeneralMovableObject vaso1;
    private GeneralMovableObject vaso2;
    private GeneralMovableObject licor;

    private GeneralMovableObject silla1;
    private GeneralMovableObject silla2;

    private GeneralMovableObject cuadroDeTren;
    private Box cuadroBarco;
    private ConcreteGameObjectWithParentAndChildren cajaFuerte;
    BooleanState cajaFuerteAbierta;
    BooleanState cajaFuerteConLlave;
    private Box credencial;

    // salon 2
    private Room salon2;

    private LinkingDoor salon2ToPasillo;

    private Key martillo;

    private GeneralMovableObject destornillador1;
    private GeneralMovableObject destornillador2;

    // salon 3
    private Room salon3;

    private LinkingDoor salon3ToPasillo;

    private Key llave;

    // acceso biblioteca
    private Room bibliotecaAcceso;

    private LinkingDoor bibliotecaAccesoToPasillo;
    private LinkingDoor bibliotecaAccesoToBiblioteca;

    private ConcreteGameObjectWithParent bibliotecario;
    private BooleanState noVioCredencialFalsa;
    private BooleanState vioCredencialFalsa;
    private BooleanState noPermiteAcceso;
    private BooleanState permiteAcceso;
    private BooleanState dormido;
    private BooleanState noDormido;
    private BooleanState talkedLastTurn;

    // biblioteca
    private Room biblioteca;

    private LinkingDoor bibliotecaToBibliotecaAcceso;
    private LinkingDoor bibliotecaToSotano;

    private GameObjectWithChildren estante;
    private GeneralMovableObject libro1;
    private GeneralMovableObject libro2;
    private GeneralMovableObject libro3;
    private GeneralMovableObject libro4;
    private GeneralMovableObject libro5;
    private GeneralMovableObject libro6;
    private GeneralMovableObject libro7;
    private GeneralMovableObject libro8;
    private GeneralMovableObject libro9;
    private GeneralMovableObject libroViejo;
    private BooleanState libroViejoRemoved;

    // sotano
    private Room sotano;

    private LinkingDoor sotanoToBiblioteca;
    private LinkingDoor barandaToSotanoAbajo;
    private LinkingDoor sotanoToCuartoDeLaMuerte;

    // muerte sotano
    private Room cuartoDeLosMuertos;

    // sotano abajo
    private Room sotanoAbajo;
    private LinkingLockedDoor sotanoAbajoToAfuera;
    private LinkingDoor sotanoAbajoToCuartoDeLaMuerte;

    // afuera
    private Room afuera;

    // player
    private GeneralMovableObject lapicera;
    private GeneralMovableObject fotoPlayer;

    @Override
    public Game build() {

        createRooms();

        populatePasillo();

        populateSalon1();

        populateSalon2();

        populateSalon3();

        populateAccesoBiblioteca();

        populateBiblioteca();

        populateSotano();

        populateSotanoAbajo();

        createPlayer();

        return this;
    }

    public TheEscape() {
        super("TheEscape");
    }

    @Override
    public boolean checkWinCondition() {

        if (noPermiteAcceso.isTrue() && talkedLastTurn.isTrue()) {
            if (noVioCredencialFalsa.isTrue() && player.contains(credencial.getName()) ) {
                if (credencial.contains(fotoPlayer.getName())) {
                    permiteAcceso.setTrue();
                    noPermiteAcceso.setFalse();
                } else {
                    vioCredencialFalsa.setTrue();
                    noVioCredencialFalsa.setFalse();
                }
            } else if (player.contains(licor.getName())) {
                dormido.setTrue();
                noDormido.setFalse();
                permiteAcceso.setTrue();
                noPermiteAcceso.setFalse();
            }
        }

        talkedLastTurn.setFalse();
        return afuera.contains(player.getName());
    }

    @Override
    public boolean checkLooseCondition() {
        return cuartoDeLosMuertos.contains(player.getName())
                || (sotanoAbajo.contains(player.getName()) && !player.contains(martillo.getName()));
    }

    private void createPlayer() {
        player.setParent(pasillo);
        pasillo.addChild(player);

        fotoPlayer = new GeneralMovableObject("fotoPlayer", player);
        objects.put(fotoPlayer.getName(), fotoPlayer);

        lapicera = new GeneralMovableObject("lapicera", player);
        objects.put(lapicera.getName(), lapicera);

        // talk
        ActionHandler talk = new Talk(player);
        player.addAction(talk);
        commands.add(talk.getName());

        // pick
        ActionHandler pick = new Pick(player);
        player.addAction(pick);
        commands.add(pick.getName());

        // open
        ActionHandler open = new Open(player);
        player.addAction(open);
        commands.add(open.getName());

        // dejar cosas en el piso
        ActionHandler leave = new Leave(player);
        player.addAction(new Leave(player));
        commands.add(leave.getName());

        // para que deje cosas
        ActionHandler haveMovedFrom = new HaveMovedFrom(player, player.getChildrenState());
        player.addAction(haveMovedFrom);

        // put
        ActionHandler moveFromInventory = new MoveFromInventory(player);
        player.addAction(moveFromInventory);
        commands.add(moveFromInventory.getName());
    }

    private void populateSotanoAbajo() {
        sotanoAbajoToAfuera = new LinkingLockedDoor("ventana", sotanoAbajo, 2, afuera, new BooleanState(true));
        objects.put(sotanoAbajoToAfuera.getName(), sotanoAbajoToAfuera);

        sotanoAbajoToCuartoDeLaMuerte = new LinkingDoor("escalera", sotanoAbajo, cuartoDeLosMuertos);
        objects.put(sotanoAbajoToCuartoDeLaMuerte.getName(), sotanoAbajoToCuartoDeLaMuerte);
    }

    private void populateSotano() {
        sotanoToBiblioteca = new LinkingDoor("doorSotanoToBiblioteca", sotano, biblioteca);
        objects.put(sotanoToBiblioteca.getName(), sotanoToBiblioteca);

        barandaToSotanoAbajo = new LinkingDoor("baranda", sotano, sotanoAbajo);
        objects.put(barandaToSotanoAbajo.getName(), barandaToSotanoAbajo);

        sotanoToCuartoDeLaMuerte = new LinkingDoor("escalera", sotano, cuartoDeLosMuertos);
        objects.put(sotanoToCuartoDeLaMuerte.getName(), sotanoToCuartoDeLaMuerte);
    }

    private void populateBiblioteca() {

        bibliotecaToBibliotecaAcceso = new LinkingDoor("doorBibliotecaToAcceso", biblioteca, bibliotecaAcceso);
        objects.put(bibliotecaToBibliotecaAcceso.getName(), bibliotecaToBibliotecaAcceso);

        ChildrenState estanteChildren = new ChildrenState();
        estante = new ConcreteGameObjectWithParentAndChildren("estante", biblioteca, estanteChildren);
        objects.put(estante.getName(), estante);
        estante.addAction(new BeLookedAtAndChildren(estante, estanteChildren));
        estante.addAction(new BeAskedWhat(estante));
        estante.addAction(new HaveMovedTo(estante, estanteChildren));
        estante.addAction(new HaveMovedFrom(estante, estanteChildren));

        libro1 = new GeneralMovableObject("libro1", estante);
        objects.put(libro1.getName(), libro1);
        libro2 = new GeneralMovableObject("libro2", estante);
        objects.put(libro2.getName(), libro2);
        libro3 = new GeneralMovableObject("libro3", estante);
        objects.put(libro3.getName(), libro3);
        libro4 = new GeneralMovableObject("libro4", estante);
        objects.put(libro4.getName(), libro4);
        libro5 = new GeneralMovableObject("libro5", estante);
        objects.put(libro5.getName(), libro5);
        libro6 = new GeneralMovableObject("libro6", estante);
        objects.put(libro6.getName(), libro6);
        libro7 = new GeneralMovableObject("libro7", estante);
        objects.put(libro7.getName(), libro7);
        libro8 = new GeneralMovableObject("libro8", estante);
        objects.put(libro8.getName(), libro8);
        libro9 = new GeneralMovableObject("libro9", estante);
        objects.put(libro9.getName(), libro9);

        libroViejo = new GeneralMovableObject("libroViejo", estante);
        objects.put(libroViejo.getName(), libroViejo);

        libroViejoRemoved = new BooleanState(false);
        List<BooleanState> triggers = new LinkedList<>();
        triggers.add(libroViejoRemoved);
        List<Boolean> triggeredValues = new LinkedList<>();
        triggeredValues.add(true);
        libroViejo.addAction(new TriggerActionHandler(libroViejo,
                new BeMoved(libroViejo, libroViejo.getParentState()), triggers, triggeredValues, "You see a hidden door appear!"));

        bibliotecaToSotano = new LinkingDoor("doorBibliotecaToSotano", biblioteca, sotano);
        objects.put(bibliotecaToSotano.getName(), bibliotecaToSotano);
        bibliotecaToSotano.addAction(new ConditionalActionHandlerFailsDummy(bibliotecaToSotano,
                new BeOpened(null, null), triggers));
        bibliotecaToSotano.addAction(new ConditionalActionHandlerFailsDummy(bibliotecaToSotano,
                new BeAskedWhat(null), triggers));
        bibliotecaToSotano.addAction(new ConditionalActionHandlerFailsDummy(bibliotecaToSotano,
                new BeLookedAt(null), triggers));
    }

    private void populatePasillo() {

        pasilloToSalon1 = new LinkingDoor("doorPasilloToSalon1", pasillo, salon1);
        objects.put(pasilloToSalon1.getName(), pasilloToSalon1);

        pasilloToSalon2 = new LinkingDoor("doorPasilloToSalon2", pasillo, salon2);
        objects.put(pasilloToSalon2.getName(), pasilloToSalon2);

        pasilloToSalon3 = new LinkingDoor("doorPasilloToSalon3", pasillo, salon3);
        objects.put(pasilloToSalon3.getName(), pasilloToSalon3);

        pasilloToBibliotecaAcceso = new LinkingDoor("doorPasilloToAccesoBiblioteca", pasillo, bibliotecaAcceso);
        objects.put(pasilloToBibliotecaAcceso.getName(), pasilloToBibliotecaAcceso);
    }

    private void populateAccesoBiblioteca() {

        bibliotecaAccesoToPasillo = new LinkingDoor("doorAccesoToPasillo", bibliotecaAcceso, pasillo);
        objects.put(bibliotecaAccesoToPasillo.getName(), bibliotecaAccesoToPasillo);

        bibliotecario = new ConcreteGameObjectWithParent("bibliotecario", bibliotecaAcceso);
        objects.put(bibliotecario.getName(), bibliotecario);

        noPermiteAcceso = new BooleanState(true);
        permiteAcceso = new BooleanState(false);
        noVioCredencialFalsa = new BooleanState(true);
        vioCredencialFalsa = new BooleanState(false);
        dormido = new BooleanState(false);
        noDormido = new BooleanState(true);
        talkedLastTurn = new BooleanState(false);

        bibliotecaAccesoToBiblioteca = new LinkingDoor("doorAccesoToBiblioteca", bibliotecaAcceso, biblioteca);
        List<BooleanState> condDoorABiblioteca = new LinkedList<>();
        condDoorABiblioteca.add(permiteAcceso);
        bibliotecaAccesoToBiblioteca.addAction(new ConditionalActionHandlerFails(bibliotecaAccesoToBiblioteca,
                new BeOpened(bibliotecaAccesoToBiblioteca, new BooleanState()), condDoorABiblioteca));
        objects.put(bibliotecaAccesoToBiblioteca.getName(), bibliotecaAccesoToBiblioteca);

        List<BooleanState> condicionesBibliotecarioAmigable = new LinkedList<>();
        condicionesBibliotecarioAmigable.add(noPermiteAcceso);
        condicionesBibliotecarioAmigable.add(noVioCredencialFalsa);
        condicionesBibliotecarioAmigable.add(noDormido);
        bibliotecario.addAction(new ConditionalActionHandlerChecks(bibliotecario,
                new BeTalkedTo(bibliotecario, "Hi! You need a credential to pass. Wait, what's that in your inventory?"),
                condicionesBibliotecarioAmigable));

        List<BooleanState> condicionesBibliotecarioFurioso = new LinkedList<>();
        condicionesBibliotecarioFurioso.add(vioCredencialFalsa);
        condicionesBibliotecarioFurioso.add(noPermiteAcceso);
        condicionesBibliotecarioAmigable.add(noDormido);
        bibliotecario.addAction(new ConditionalActionHandlerChecks(bibliotecario,
                new BeTalkedTo(bibliotecario, "I saw that fake credential, you shall not pass!"), condicionesBibliotecarioFurioso));

        List<BooleanState> condicionesBibliotecarioPermitePasar = new LinkedList<>();
        condicionesBibliotecarioPermitePasar.add(noVioCredencialFalsa);
        condicionesBibliotecarioPermitePasar.add(permiteAcceso);
        condicionesBibliotecarioPermitePasar.add(noDormido);
        bibliotecario.addAction(new ConditionalActionHandlerChecks(bibliotecario,
                new BeTalkedTo(bibliotecario, "Hi again! Feel free to enter"), condicionesBibliotecarioPermitePasar));

        List<BooleanState> condicionesBibliotecarioDormido = new LinkedList<>();
        condicionesBibliotecarioDormido.add(dormido);
        bibliotecario.addAction(new ConditionalActionHandlerChecks(bibliotecario,
                new BeTalkedTo(bibliotecario, "Zzzzz..."), condicionesBibliotecarioDormido));

        List<BooleanState> triggers = new LinkedList<>();
        triggers.add(talkedLastTurn);
        List<Boolean> tiggeredValues = new LinkedList<>();
        tiggeredValues.add(true);
        bibliotecario.addAction(new TriggerActionHandler(bibliotecario,
                new BeTalkedTo(bibliotecario, ""), triggers, tiggeredValues));

        bibliotecario.addAction(new BeLookedAt(bibliotecario));
        bibliotecario.addAction(new BeAskedWhat(bibliotecario));
    }

    private void populateSalon3() {

        salon3ToPasillo = new LinkingDoor("doorSalon3ToPasillo", salon3, pasillo);
        objects.put(salon3ToPasillo.getName(), salon3ToPasillo);

        llave = new Key("llave", salon3, 1);
        objects.put(llave.getName(), llave);
    }

    private void populateSalon2() {

        salon2ToPasillo = new LinkingDoor("doorSalon2ToPasillo", salon2, pasillo);
        objects.put(salon2ToPasillo.getName(), salon2ToPasillo);

        destornillador1 = new GeneralMovableObject("destornillador1", salon2);
        objects.put(destornillador1.getName(), destornillador1);

        destornillador2 = new GeneralMovableObject("destornillador2", salon2);
        objects.put(destornillador2.getName(), destornillador2);

        martillo = new Key("martillo", salon2, 2);
        objects.put(martillo.getName(), martillo);
    }

    private void populateSalon1() {

        salon1ToPasillo = new LinkingDoor("doorSalon1ToPasillo", salon1, pasillo);
        objects.put(salon1ToPasillo.getName(), salon1ToPasillo);

        mesa = new ConcreteGameObjectWithParentAndChildren("mesa", salon1 );
        mesa.addAction(new BeAskedWhat(mesa));
        mesa.addAction(new BeLookedAtAndChildren(mesa, mesa.getChildrenState()));
        mesa.addAction(new HaveMovedFrom(mesa, mesa.getChildrenState()));
        mesa.addAction(new HaveMovedTo(mesa, mesa.getChildrenState()));
        objects.put(mesa.getName(), mesa);

        vaso1 = new  GeneralMovableObject("vaso1", mesa);
        objects.put(vaso1.getName(), vaso1);
        vaso2 = new  GeneralMovableObject("vaso2", mesa);
        objects.put(vaso2.getName(), vaso2);
        licor = new  GeneralMovableObject("licor", mesa);
        objects.put(licor.getName(), licor);


        silla1 = new GeneralMovableObject("silla1", salon1);
        objects.put(silla1.getName(), silla1);
        silla2 = new GeneralMovableObject("silla2", salon1);
        objects.put(silla2.getName(), silla2);


        cuadroDeTren = new GeneralMovableObject("cuadroDeTren", salon1);
        objects.put(cuadroDeTren.getName(), cuadroDeTren);


        cuadroBarco = new Box("cuadroDeBarco", salon1);
        objects.put(cuadroBarco.getName(), cuadroBarco);

        cajaFuerte = new ConcreteGameObjectWithParentAndChildren("cajaFuerte", cuadroBarco);
        objects.put(cajaFuerte.getName(), cajaFuerte);
        cajaFuerteAbierta = new BooleanState(false);
        cajaFuerteConLlave = new BooleanState(true);
        cajaFuerte.addAction(new HaveMovedFromChangesPermission(cajaFuerte, cajaFuerte.getChildrenState(), cajaFuerteAbierta));
        cajaFuerte.addAction(new HaveMovedTo(cajaFuerte, cajaFuerte.getChildrenState()));
        cajaFuerte.addAction(new BeLookedAtAndChildrenChangeVisibility(cajaFuerte, cajaFuerte.getChildrenState(), cajaFuerteAbierta));
        cajaFuerte.addAction(new BeOpenedHasLock(cajaFuerte, cajaFuerteAbierta, cajaFuerteConLlave, 1));
        cajaFuerte.addAction(new BeAskedWhat(cajaFuerte));

        credencial = new Box("credential", cajaFuerte);
        objects.put(credencial.getName(), credencial);
        credencial.addAction(new BeMoved(credencial, credencial.getParentState()));
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

        bibliotecaAcceso = new Room("accesoBiblioteca");
        objects.put(bibliotecaAcceso.getName(), bibliotecaAcceso);

        biblioteca = new Room("biblioteca");
        objects.put(biblioteca.getName(),biblioteca);

        sotano = new Room("sotano");
        objects.put(sotano.getName(),sotano);

        cuartoDeLosMuertos = new Room("precipicio");
        objects.put(cuartoDeLosMuertos.getName(), cuartoDeLosMuertos);

        sotanoAbajo = new Room("sotanoAbajo");
        objects.put(sotanoAbajo.getName(),sotanoAbajo);

        afuera = new Room("afuera");
        objects.put(afuera.getName(),afuera);
    }
}
