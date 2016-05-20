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

import java.util.LinkedList;
import java.util.List;

/**
 * Created by gabriel on 5/19/2016.
 */
public class TheEscape extends Game {

    Room accesoBiblioteca;
    GeneralMovableObject licor;
    GeneralMovableObject fotoPlayer;
    Box credencial;

    ConcreteGameObjectWithParent bibliotecario;
    BooleanState noVioCredencialFalsa;
    BooleanState vioCredencialFalsa;
    BooleanState noPermiteAcceso;
    BooleanState permiteAcceso;
    BooleanState dormido;
    BooleanState noDormido;
    BooleanState talkedLastTurn;

    LinkingDoor doorToBiblioteca;

    Room biblioteca;

    @Override
    public Game build() {

        accesoBiblioteca = new Room("accesoBiblioteca");
        objects.put(accesoBiblioteca.getName(), accesoBiblioteca);

        licor = new GeneralMovableObject("alcohol", accesoBiblioteca);
        objects.put(licor.getName(), licor);

        fotoPlayer = new GeneralMovableObject("fotoPlayer", accesoBiblioteca);
        objects.put(fotoPlayer.getName(), fotoPlayer);

        bibliotecario = new ConcreteGameObjectWithParent("bibliotecario", accesoBiblioteca);
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
                new BeTalkedTo(bibliotecario, "You shall not pass!"), condicionesBibliotecarioFurioso));

        List<BooleanState> condicionesBibliotecarioPermitePasar = new LinkedList<>();
        condicionesBibliotecarioPermitePasar.add(noVioCredencialFalsa);
        condicionesBibliotecarioPermitePasar.add(permiteAcceso);
        condicionesBibliotecarioAmigable.add(noDormido);
        bibliotecario.addAction(new ConditionalActionHandlerChecks(bibliotecario,
                new BeTalkedTo(bibliotecario, "Hi again! Feel free to enter"), condicionesBibliotecarioPermitePasar));

        List<BooleanState> condicionesBibliotecarioDormido = new LinkedList<>();
        condicionesBibliotecarioDormido.add(dormido);
        bibliotecario.addAction(new ConditionalActionHandlerChecks(bibliotecario,
                new BeTalkedTo(bibliotecario, "Zzzzz..."), condicionesBibliotecarioDormido));

        doorToBiblioteca = new LinkingDoor("doorToBiblioteca", accesoBiblioteca, biblioteca);
        List<BooleanState> condDoorABiblioteca = new LinkedList<>();
        condDoorABiblioteca.add(permiteAcceso);
        doorToBiblioteca.addAction(new ConditionalActionHandlerFails(doorToBiblioteca, new BeOpened(null, null), condDoorABiblioteca));

        player.setParent(accesoBiblioteca);
        accesoBiblioteca.addChild(player);

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
        commands.add(haveMovedFrom.getName());

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

        if (talkedLastTurn.isTrue() && dormido.isFalse() && noVioCredencialFalsa.isTrue() && noPermiteAcceso.isTrue()) {
            if (player.contains(credencial.getName()) ) {
                if(credencial.contains(fotoPlayer.getName())) {
                    permiteAcceso.setTrue();
                    noPermiteAcceso.setFalse();
                }
            } else if (player.contains(licor.getName())) {
                dormido.setTrue();
                noDormido.setFalse();
                permiteAcceso.setTrue();
                noPermiteAcceso.setFalse();
            }
        }

        return biblioteca.contains(player.getName());
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }
}
