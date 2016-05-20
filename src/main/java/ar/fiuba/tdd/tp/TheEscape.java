package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.Box;
import ar.fiuba.tdd.tp.objects.concrete.Chest;
import ar.fiuba.tdd.tp.objects.concrete.GeneralMovableObject;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingDoor;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.LinkedList;
import java.util.List;

public class TheEscape extends Game {
    @SuppressWarnings("CPD-START")

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

    // ------------

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

    private GeneralMovableObject licor;
    private GeneralMovableObject fotoPlayer;
    private Box credencial;

    // ------------

    private Room biblioteca;
    private LinkingDoor bibliotecaToBibliotecaAcceso;
    private LinkingDoor bibliotecaToSotano;

    private Room sotano;
    private LinkingDoor sotanoToBiblioteca;
    private LinkingDoor sotanoToSotanoAbajo;

    private Room sotanoAbajo;
    private LinkingDoor sotanoAbajoToAfuera;

    private Room afuera;

    @Override
    public Game build() {

        createRooms();

        biblioteca = new Room("biblioteca");
        objects.put(biblioteca.getName(), biblioteca);

        licor = new GeneralMovableObject("alcohol", bibliotecaAcceso);
        objects.put(licor.getName(), licor);

        fotoPlayer = new GeneralMovableObject("fotoPlayer", bibliotecaAcceso);
        objects.put(fotoPlayer.getName(), fotoPlayer);

        credencial = new Box("credential", bibliotecaAcceso);
        objects.put(credencial.getName(), credencial);
        credencial.addAction(new BeMoved(credencial, credencial.getParentState()));

        bibliotecario = new ConcreteGameObjectWithParent("bibliotecario", bibliotecaAcceso);
        objects.put(bibliotecario.getName(), bibliotecario);

        noPermiteAcceso = new BooleanState(true);
        permiteAcceso = new BooleanState(false);
        noVioCredencialFalsa = new BooleanState(true);
        vioCredencialFalsa = new BooleanState(false);
        dormido = new BooleanState(false);
        noDormido = new BooleanState(true);
        talkedLastTurn = new BooleanState(false);

        List<BooleanState> condicionesBibliotecarioAmigable = new LinkedList<>();
        condicionesBibliotecarioAmigable.add(noPermiteAcceso);
        condicionesBibliotecarioAmigable.add(noVioCredencialFalsa);
        condicionesBibliotecarioAmigable.add(noDormido);
        bibliotecario.addAction(new ConditionalActionHandlerChecks(bibliotecario,
                new BeTalkedTo(bibliotecario, "Hi! You need a credential to pass. Wait, what's that in your inventory?"), condicionesBibliotecarioAmigable));

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

        bibliotecaAccesoToBiblioteca = new LinkingDoor("doorToBiblioteca", bibliotecaAcceso, biblioteca);
        List<BooleanState> condDoorABiblioteca = new LinkedList<>();
        condDoorABiblioteca.add(permiteAcceso);
        bibliotecaAccesoToBiblioteca.addAction(new ConditionalActionHandlerFails(bibliotecaAccesoToBiblioteca, new BeOpened(bibliotecaAccesoToBiblioteca, new BooleanState()), condDoorABiblioteca));
        objects.put(bibliotecaAccesoToBiblioteca.getName(), bibliotecaAccesoToBiblioteca);

        player.setParent(bibliotecaAcceso);
        bibliotecaAcceso.addChild(player);

        ActionHandler talk = new Talk(player);
        player.addAction(talk);
        commands.add(talk.getName());

        ActionHandler pick = new Pick(player);
        player.addAction(pick);
        commands.add(pick.getName());

        ActionHandler open = new Open(player);
        player.addAction(open);
        commands.add(open.getName());

        ActionHandler leave = new Leave(player);
        player.addAction(new Leave(player));
        commands.add(leave.getName());

        ActionHandler haveMovedFrom = new HaveMovedFrom(player, player.getChildrenState());
        player.addAction(haveMovedFrom);

        ActionHandler moveFromInventory = new MoveFromInventory(player);
        player.addAction(moveFromInventory);
        commands.add(moveFromInventory.getName());

        return this;
    }

    public TheEscape() {
        super("TheEscape");
    }

    @Override
    public boolean checkWinCondition() {

        if (noPermiteAcceso.isTrue() && talkedLastTurn.isTrue()) {
            if (noVioCredencialFalsa.isTrue() && player.contains(credencial.getName()) ) {
                if(credencial.contains(fotoPlayer.getName())) {
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

        return biblioteca.contains(player.getName());
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
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

            sotanoAbajo = new Room("sotanoAbajo");
            objects.put(sotanoAbajo.getName(),sotanoAbajo);

            afuera = new Room("afuera");
            objects.put(afuera.getName(),afuera);
        }

}
