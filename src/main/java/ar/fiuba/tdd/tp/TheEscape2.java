package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.model.*;
import ar.fiuba.tdd.tp.objects.concrete.*;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingDoor;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingLockedDoor;
import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;
import ar.fiuba.tdd.tp.timedevent.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TheEscape2 extends Game {
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
    private BooleanState dormido;
    private BooleanState cazandoJugadores;
    private BooleanState bibliotecarioCambioCuarto;
    private List<GameObject> objetosParaBibliotecarioCazando;
    private TimedEvent despertarBibliotecarioEvent;

    private List<BooleanState> bibliotecarioAmigableConds;
    private List<Boolean> bibliotecarioAmigableCondsValues;
    private List<BooleanState> bibliotecarioFuriosoConds;
    private List<Boolean> bibliotecarioFuriosoCondsValues;
    private List<BooleanState> bibliotecarioPermitePasarConds;
    private List<Boolean> bibliotecarioPermitePasarCondsValues;
    private List<BooleanState> puertaPermiteAcceso;
    private List<Boolean> puertaPermiteAccesoValues;

    private int bibliotecarioCredencialFalsa = 0;
    //private int BIBLIOTECARIO_DORMIDO = 1;
    private int bibliotecarioCredencialVerdadera = 2;

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
    private List<GeneralMovableObject> lapicera;
    private List<GeneralMovableObject> fotoPlayer;
    private List<String> playerNames;
    private List<String> playerNamesBibliotecario;

    private List<BooleanState> losIlegales;
    private List<BooleanState> conBibliotecario;
    private List<BooleanState> talkedLastTurn;
    private List<Boolean> talkedLastTurnTiggeredValues;

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

        //createPlayer();

        return this;
    }

    public TheEscape2() {
        super("TheEscape2");
    }

    @Override
    protected void updateGameAfterHandle(String playerId) {
        Player player = players.get(playerId);
        String playerName = players.get(playerId).getName();
        int indexInPlayerNames = playerNames.indexOf(playerName);
        int indexInPlayerNamesBibliotecario = playerNamesBibliotecario.indexOf(playerName);

        if (indexInPlayerNames == -1 || indexInPlayerNamesBibliotecario == -1) {
            return;
        }

        BooleanState permiteAcceso = puertaPermiteAcceso.get(indexInPlayerNames);
        BooleanState vioCredencialFalsa = bibliotecarioFuriosoConds.get(indexInPlayerNamesBibliotecario + bibliotecarioCredencialFalsa);
        BooleanState vioCredencialVerdadera = bibliotecarioPermitePasarConds.get(
                indexInPlayerNamesBibliotecario + bibliotecarioCredencialVerdadera);

        // comportamiento de bibliotecario cuando le hablan
        if (permiteAcceso.isFalse() && talkedLastTurn.get(indexInPlayerNames).isTrue()) {
            if (vioCredencialFalsa.isFalse() && vioCredencialVerdadera.isFalse()) {
                if (player.contains(credencial.getName()) ) {
                    if (credencial.contains(fotoPlayer.get(indexInPlayerNames).getName())) {
                        permiteAcceso.setTrue();
                        vioCredencialVerdadera.setTrue();
                    } else {
                        vioCredencialFalsa.setTrue();
                    }
                }
            }

            if (player.contains(licor.getName())) {
                dormido.setTrue();
                despertarBibliotecarioEvent.resetRepeticiones(0);
                permiteAcceso.setTrue();
            }
        }
        talkedLastTurn.get(indexInPlayerNames).setFalse();

        // si bibliotecario no esta en biblioteca, todos pueden pasar (esto podria ir solo cuando cambia de cuarto)
        if (dormido.isTrue() || !bibliotecaAcceso.contains(bibliotecario.getName())) {
            permiteAcceso.setTrue();
        }

        if (bibliotecarioCambioCuarto.isTrue()) {
            bibliotecarioCambioCuarto.setFalse();

            // TODO: hacer que en algun momento se actualice la lista segun las habitaciones adyacentes a la actual del bibliotecario
            objetosParaBibliotecarioCazando.remove(objetosParaBibliotecarioCazando.size() - 1);
            objetosParaBibliotecarioCazando.add(bibliotecarioGetNextCuarto());
            //System.out.println("bibliotecario cambio cuarto");
        }

        // si bibliotecario volvio a la biblioteca, los que no tenian permiso ya no tienen
        if (permiteAcceso.isTrue() && vioCredencialVerdadera.isFalse()
                && dormido.isFalse() && bibliotecaAcceso.contains(bibliotecario.getName())) {
            permiteAcceso.setFalse();

            // esto va a ocurrir la 1ra vez que se despierta
            if (cazandoJugadores.isFalse()) {
                //System.out.println("cazando jugadores");
                cazandoJugadores.setTrue();
                objetosParaBibliotecarioCazando.add(bibliotecarioGetNextCuarto());

                List<ActionHandler> actionsBibliotecarioCazando = new ArrayList<>();
                actionsBibliotecarioCazando.add(new Move(bibliotecario));

                List<BooleanState> cond = new ArrayList<>();
                cond.add(bibliotecarioCambioCuarto);
                List<Boolean> trigg = new ArrayList<>();
                trigg.add(true);
                actionsBibliotecarioCazando.add(new TriggerActionHandler(bibliotecario, new Move(bibliotecario), cond, trigg));

                actionGeneration.addActionWithTime(new ActionWithTime(
                        new TimedEvent(this, actionsBibliotecarioCazando, objetosParaBibliotecarioCazando,
                                "El bibliotecario esta cazando wonejos"), 10));
            }
        }

        if (player.getParent().equals(bibliotecario.getParent())) {
            conBibliotecario.get(indexInPlayerNames).setTrue();
        } else {
            conBibliotecario.get(indexInPlayerNames).setFalse();
        }

        if (biblioteca.contains(player.getName()) && vioCredencialVerdadera.isFalse()) {
            losIlegales.get(indexInPlayerNames).setTrue();
        }
    }

    private Room bibliotecarioGetNextCuarto() {

        List<Room> rooms = new ArrayList<>();
        rooms.add(salon1);
        rooms.add(salon2);
        rooms.add(salon3);
        rooms.add(pasillo);
        rooms.add(bibliotecaAcceso);
        rooms.add(biblioteca);

        int chosenRoom = this.random.getRandomNumber(0, rooms.size() - 1);

        return rooms.get(chosenRoom);
    }

    @Override
    protected Player configPlayer(String playerId, String type) {
        Player player = new Player("player" + Integer.toString(players.size() + 1), null, new ChildrenState());
        Set<String> commands = commandsPerPlayer.get(playerId);

        player.setParent(pasillo);
        pasillo.addChild(player);

        GeneralMovableObject myFotoPlayer = new GeneralMovableObject("fotoPlayer" + Integer.toString(players.size() + 1), player);
        objects.put(myFotoPlayer.getName(), myFotoPlayer);
        fotoPlayer.add(myFotoPlayer);

        GeneralMovableObject myLapicera = new GeneralMovableObject("lapicera" + Integer.toString(players.size() + 1), player);
        objects.put(myLapicera.getName(), myLapicera);
        lapicera.add(myLapicera);

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

        return keepCreatingPlayer(playerId, player);
    }

    private Player keepCreatingPlayer(String playerId, Player player) {
        Set<String> commands = commandsPerPlayer.get(playerId);
        List<AbstractCondition> winConds = winConditionsPerPlayer.get(playerId);
        List<AbstractCondition> looseConds = looseConditionsPerPlayer.get(playerId);

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

        BooleanState myTalkedLastTrun = new BooleanState(false);
        Boolean myTalkedLastTurnTriggeredValue = Boolean.valueOf(true);

        BooleanState vioCredencialFalsa = new BooleanState(false);
        BooleanState vioCredencialVerdadera = new BooleanState(false);
        BooleanState permiteAcceso = new BooleanState(false);

        BooleanState myIlegal = new BooleanState(false);
        losIlegales.add(myIlegal);
        BooleanState myConBibliotecario = new BooleanState(false);
        conBibliotecario.add(myConBibliotecario);

        winConds.add(new ConditionCheckContains(afuera.getChildrenState(), player.getName(), true));

        looseConds.add(new ConditionCheckContains(cuartoDeLosMuertos.getChildrenState(), player.getName(), true));
        looseConds.add(new ConditionCompound(new ConditionCheckContains(sotanoAbajo.getChildrenState(), player.getName(), true),
                new ConditionCheckContains(player.getChildrenState(), martillo.getName(), false)));
        looseConds.add(new ConditionCompound(new ConditionCheckBoolean(myIlegal, true),
                new ConditionCheckBoolean(myConBibliotecario, true),
                new ConditionCheckBoolean(cazandoJugadores, true)));

        playerNames.add(player.getName());
        playerNamesBibliotecario.add(player.getName());
        playerNamesBibliotecario.add(player.getName());
        playerNamesBibliotecario.add(player.getName());

        talkedLastTurn.add(myTalkedLastTrun);
        talkedLastTurnTiggeredValues.add(myTalkedLastTurnTriggeredValue);

        puertaPermiteAcceso.add(permiteAcceso);
        puertaPermiteAccesoValues.add(Boolean.valueOf(true));

        bibliotecarioAmigableConds.add(vioCredencialFalsa);
        bibliotecarioAmigableConds.add(dormido);
        bibliotecarioAmigableConds.add(vioCredencialVerdadera);
        bibliotecarioAmigableCondsValues.add(Boolean.valueOf(false));
        bibliotecarioAmigableCondsValues.add(Boolean.valueOf(false));
        bibliotecarioAmigableCondsValues.add(Boolean.valueOf(false));

        bibliotecarioFuriosoConds.add(vioCredencialFalsa);
        bibliotecarioFuriosoConds.add(dormido);
        bibliotecarioFuriosoConds.add(vioCredencialVerdadera);
        bibliotecarioFuriosoCondsValues.add(Boolean.valueOf(true));
        bibliotecarioFuriosoCondsValues.add(Boolean.valueOf(false));
        bibliotecarioFuriosoCondsValues.add(Boolean.valueOf(false));

        bibliotecarioPermitePasarConds.add(vioCredencialFalsa);
        bibliotecarioPermitePasarConds.add(dormido);
        bibliotecarioPermitePasarConds.add(vioCredencialVerdadera);
        bibliotecarioPermitePasarCondsValues.add(Boolean.valueOf(false));
        bibliotecarioPermitePasarCondsValues.add(Boolean.valueOf(false));
        bibliotecarioPermitePasarCondsValues.add(Boolean.valueOf(true));

        return player;
    }

    @Override
    protected void removePlayerItems(String playerId) {
        Player player = players.get(playerId);
        for (GameObjectWithParent o : player.getChildren()) {
            o.setParent(player.getParent());
            player.getParent().addChild(o);
        }

        int indexInPlayerNames = playerNames.indexOf(player.getName());
        int indexInPlayerNamesBibliotecario = playerNamesBibliotecario.indexOf(player.getName());

        playerNames.remove(indexInPlayerNames);
        puertaPermiteAcceso.remove(indexInPlayerNames);
        puertaPermiteAccesoValues.remove(indexInPlayerNames);

        losIlegales.remove(indexInPlayerNames);
        conBibliotecario.remove(indexInPlayerNames);

        playerNamesBibliotecario.remove(indexInPlayerNamesBibliotecario);
        playerNamesBibliotecario.remove(indexInPlayerNamesBibliotecario);
        playerNamesBibliotecario.remove(indexInPlayerNamesBibliotecario);

        bibliotecarioAmigableConds.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioAmigableConds.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioAmigableConds.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioAmigableCondsValues.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioAmigableCondsValues.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioAmigableCondsValues.remove(indexInPlayerNamesBibliotecario);

        bibliotecarioFuriosoConds.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioFuriosoConds.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioFuriosoConds.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioFuriosoCondsValues.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioFuriosoCondsValues.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioFuriosoCondsValues.remove(indexInPlayerNamesBibliotecario);

        bibliotecarioPermitePasarConds.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioPermitePasarConds.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioPermitePasarConds.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioPermitePasarCondsValues.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioPermitePasarCondsValues.remove(indexInPlayerNamesBibliotecario);
        bibliotecarioPermitePasarCondsValues.remove(indexInPlayerNamesBibliotecario);

        talkedLastTurn.remove(indexInPlayerNames);
        talkedLastTurnTiggeredValues.remove(indexInPlayerNames);

        fotoPlayer.remove(indexInPlayerNames);
        lapicera.remove(indexInPlayerNames);
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

        populateLibros();
        populateLibroViejo();
    }

    private void populateLibroViejo() {

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
                                                                            new BeOpened(null, null), triggers, triggeredValues));
        bibliotecaToSotano.addAction(new ConditionalActionHandlerFailsDummy(bibliotecaToSotano,
                                                                            new BeAskedWhat(null), triggers, triggeredValues));
        bibliotecaToSotano.addAction(new ConditionalActionHandlerFailsDummy(bibliotecaToSotano,
                                                                            new BeLookedAt(null), triggers, triggeredValues));

    }

    private void populateLibros() {
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

        bibliotecarioAmigableConds = new ArrayList<>();
        bibliotecarioAmigableCondsValues = new ArrayList<>();
        bibliotecarioFuriosoConds = new ArrayList<>();
        bibliotecarioFuriosoCondsValues = new ArrayList<>();
        bibliotecarioPermitePasarConds = new ArrayList<>();
        bibliotecarioPermitePasarCondsValues = new ArrayList<>();
        puertaPermiteAcceso = new ArrayList<>();
        puertaPermiteAccesoValues = new ArrayList<>();

        dormido = new BooleanState(false);
        cazandoJugadores = new BooleanState(false);
        bibliotecarioCambioCuarto = new BooleanState(false);
        objetosParaBibliotecarioCazando = new ArrayList<>();
        objetosParaBibliotecarioCazando.add(bibliotecario);

        talkedLastTurn = new ArrayList<>();
        talkedLastTurnTiggeredValues = new ArrayList<>();
        losIlegales = new ArrayList<>();
        conBibliotecario = new ArrayList<>();

        playerNames = new ArrayList<>();
        playerNamesBibliotecario = new ArrayList<>();

        //actionGeneration = new ActionGeneration(this.clientSockets, this.timer);
        configActionGeneration();

        List<BooleanState> triggerablesDormido = new ArrayList<>();
        triggerablesDormido.add(dormido);
        List<Boolean> triggeredDormido = new ArrayList<>();
        triggeredDormido.add(false);
        List<ActionHandler> actions = new ArrayList<>();
        actions.add(new TriggerActionHandler(null, new BeTalkedTo(null), triggerablesDormido, triggeredDormido));
        despertarBibliotecarioEvent = new TimedEvent(this, actions,
                null, "Se desperto el bibliotecario! Cuidado con que te atrape", 1, 1);
        ActionWithTime despertarBibliotecario = new ActionWithTime(despertarBibliotecarioEvent, 20);

        actionGeneration.addActionWithTime(despertarBibliotecario);

        bibliotecaAccesoToBiblioteca = new LinkingDoor("doorAccesoToBiblioteca", bibliotecaAcceso, biblioteca);
        bibliotecaAccesoToBiblioteca.addAction(new ConditionalActionHandlerFailsByName(bibliotecaAccesoToBiblioteca,
                new BeOpened(bibliotecaAccesoToBiblioteca, new BooleanState()),
                puertaPermiteAcceso, puertaPermiteAccesoValues, playerNames, 0));
        objects.put(bibliotecaAccesoToBiblioteca.getName(), bibliotecaAccesoToBiblioteca);

        configureBibliotecario();
    }


    private void configureBibliotecario() {

        bibliotecario.addAction(new ConditionalActionHandlerChecksByName(bibliotecario,
                new BeTalkedTo(bibliotecario, "Hi! You need a credential to pass. Wait, what's that in your inventory?"),
                bibliotecarioAmigableConds, bibliotecarioAmigableCondsValues, playerNamesBibliotecario, 0));

        bibliotecario.addAction(new ConditionalActionHandlerChecksByName(bibliotecario,
                new BeTalkedTo(bibliotecario, "I saw that fake credential, you shall not pass!"),
                bibliotecarioFuriosoConds, bibliotecarioFuriosoCondsValues, playerNamesBibliotecario, 0));

        bibliotecario.addAction(new ConditionalActionHandlerChecksByName(bibliotecario,
                new BeTalkedTo(bibliotecario, "Hi again! Feel free to enter"),
                bibliotecarioPermitePasarConds, bibliotecarioPermitePasarCondsValues, playerNamesBibliotecario, 0));

        List<BooleanState> condicionesBibliotecarioDormido = new LinkedList<>();
        condicionesBibliotecarioDormido.add(dormido);
        List<Boolean> condicionesBibliotecarioDormidoValues = new LinkedList<>();
        condicionesBibliotecarioDormidoValues.add(true);
        bibliotecario.addAction(new ConditionalActionHandlerChecks(bibliotecario,
                new BeTalkedTo(bibliotecario, "Zzzzz..."), condicionesBibliotecarioDormido, condicionesBibliotecarioDormidoValues));

        bibliotecario.addAction(new BeMoved(bibliotecario, bibliotecario.getParentState()));

        makeTriggers();

    }


    private void makeTriggers() {

        /*List<BooleanState> triggers = new LinkedList<>();
        triggers.add(talkedLastTurn);
        List<Boolean> tiggeredValues = new LinkedList<>();
        tiggeredValues.add(true);*/
        bibliotecario.addAction(new TriggerActionHandlerByName(bibliotecario,
                new BeTalkedTo(bibliotecario, ""), talkedLastTurn, playerNames, talkedLastTurnTiggeredValues, 0));

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


        keepPopulatingSalon1();
    }

    private void keepPopulatingSalon1() {

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

        credencial = new Box("credencial", cajaFuerte);
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

        fotoPlayer = new ArrayList<>();
        lapicera = new ArrayList<>();

        keepCreatingRooms();
    }

    private void keepCreatingRooms() {

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
