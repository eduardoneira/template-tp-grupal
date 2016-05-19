package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.*;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingDoor;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingLockedDoor;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Master on 19/05/2016.
 */
public class TempleQuest extends Game {

    // cuarto inicial
    Room room1;

    Chest cofre;
    GeneralMovableObject fragmentoObjectoBarro;

    Box nichoOscuro;
    GeneralMovableObject veneno;
    BooleanState poisoned;

    Antidote antidoto;

    ConcreteGameObjectWithParent mono;
    BooleanState monoDespierto;

    LinkingDoor doorTo2;
    ConcreteGameObjectWithParent switch1;
    BooleanState switch1IsPressed;
    ConcreteGameObjectWithParent switch2;
    BooleanState switch2IsPressed;

    // acantilado
    Room acantilado;

    // cuarto discos
    Room room2shore1;
    Pile torre1;
    Pile torre2;
    Pile torre3;
    Disc disc1;
    Disc disc2;
    Disc disc3;
    Disc disc4;
    Disc disc5;
    Disc disc6;
    Disc disc7;
    Disc disc8;
    Disc disc9;

    Room room2shore2;
    Pile torre4;
    Pile torre5;
    Pile torre6;

    LinkingLockedDoor doorTo3;

    BooleanState estabaEnShore1TurnoAnterior;
    BooleanState estaEnShore1Ahora;

    // cuarto arqueologo

    Room room3;
    Player arqueologo;
    LinkingDoor salida;

    // salida

    Room room4;

    public TempleQuest() {
        super("Temple Quest");
    }

    @SuppressWarnings("CPD-START")

    @Override
    public boolean checkWinCondition() {
        if ((switch1IsPressed.isTrue() || switch2IsPressed.isTrue()) && monoDespierto.isTrue()) {
            switch1IsPressed.setTrue();
            switch2IsPressed.setTrue();
        }

        return room4.contains(player.getName()) && poisoned.isFalse() && arqueologo.contains(disc9.getName());
    }

    @Override
    public boolean checkLooseCondition() {

        // TODO: cambiar esta negrada
        estabaEnShore1TurnoAnterior.setValue(estaEnShore1Ahora.isTrue());
        estaEnShore1Ahora.setValue(room2shore1.contains(player.getName()));
        if ((estabaEnShore1TurnoAnterior.isFalse() && estaEnShore1Ahora.isTrue())
                || (estabaEnShore1TurnoAnterior.isTrue() && estaEnShore1Ahora.isFalse())) {
            if (player.getChildren().size() > 1) { // no puedo tener mas de un item cuando cruzo la soga, si no muero
                return true;
            }
        }

        return acantilado.contains(player.getName())
                || (room4.contains(player.getName()) && (poisoned.isTrue() || !arqueologo.contains(disc9.getName())));
    }

    private void createRooms() {
        // cuartos

        room1 = new Room("room1");
        objects.put(room1.getName(), room1);

        acantilado = new Room("cliffEdge");
        objects.put(acantilado.getName(), acantilado);

        room2shore1 = new Room("room2shore1");
        objects.put(room2shore1.getName(), room2shore1);

        room2shore2 = new Room("room2shore2");
        objects.put(room2shore2.getName(), room2shore2);

        room3 = new Room("room3");
        objects.put(room3.getName(), room3);

        room4 = new Room("room4");
        objects.put(room4.getName(), room4);
    }

    private void populateRoom1() {
        // cuarto 1

        cofre = new Chest("chest", room1);
        objects.put(cofre.getName(), cofre);
        fragmentoObjectoBarro = new GeneralMovableObject("mudObjectFragment", cofre);
        objects.put(fragmentoObjectoBarro.getName(), fragmentoObjectoBarro);

        nichoOscuro = new Box("nichoOscuro", room1);
        objects.put(nichoOscuro.getName(), nichoOscuro);


        crearVeneno();

        antidoto = new Antidote("antidoteFruit", room1, poisoned);
        objects.put(antidoto.getName(), antidoto);

        crearMono();

        crearDoorTo2();

        crearSwitch1();

        crearSwitch2();
    }

    private void crearVeneno() {
        poisoned = new BooleanState(false);
        veneno = new GeneralMovableObject("venomousFruit", nichoOscuro);
        List<BooleanState> conditions = new LinkedList<>();
        conditions.add(this.poisoned);
        List<Boolean> triggeredValues = new LinkedList<>();
        triggeredValues.add(true);
        nichoOscuro.addAction(new TriggerActionHandler(nichoOscuro, new BeOpened(null, null), conditions, triggeredValues));
        List<ActionHandler> actions = new LinkedList<>();
        nichoOscuro.addAction(new BeOpenedAddsActionsToOpener(nichoOscuro, actions, "You feel weak!"));
        objects.put(veneno.getName(), veneno);

    }

    private void crearMono() {
        mono = new ConcreteGameObjectWithParent("monkey", room1);
        mono.addAction(new BeLookedAt(mono));
        mono.addAction(new BeAskedWhat(mono));
        monoDespierto = new BooleanState(false);
        List<BooleanState> monoDespiertoTriggers = new LinkedList<>();
        monoDespiertoTriggers.add(monoDespierto);
        List<Boolean> monoDespiertoTriggeredValues = new LinkedList<>();
        monoDespiertoTriggeredValues.add(true);
        mono.addAction(new BeTalkedTo(mono));
        mono.addAction(new TriggerActionHandler(mono, new BeTalkedTo(mono), monoDespiertoTriggers, monoDespiertoTriggeredValues));
        objects.put(mono.getName(), mono);
    }

    private void crearDoorTo2() {
        doorTo2 = new LinkingDoor("doorTo2", room1, room2shore1);
        switch1IsPressed = new BooleanState(false);
        switch2IsPressed = new BooleanState(false);
        List<BooleanState> switchesActivados = new LinkedList<>();
        switchesActivados.add(switch1IsPressed);
        switchesActivados.add(switch2IsPressed);
        doorTo2.addAction(new ConditionalActionHandlerFails(doorTo2, new BeOpened(doorTo2, new BooleanState()), switchesActivados));
        objects.put(doorTo2.getName(), doorTo2);
    }

    private void crearSwitch1() {
        switch1 = new ConcreteGameObjectWithParent("switch1", room1);
        switch1.addAction(new BeLookedAt(switch1));
        switch1.addAction(new BeAskedWhat(switch1));
        List<BooleanState> switch1ActivadoTriggers = new LinkedList<>();
        switch1ActivadoTriggers.add(switch1IsPressed);
        switch1ActivadoTriggers.add(switch2IsPressed);
        List<Boolean> switch1ActivadoTriggeredValues = new LinkedList<>();
        switch1ActivadoTriggeredValues.add(true);
        switch1ActivadoTriggeredValues.add(false);
        switch1.addAction(new BeTalkedTo(switch1, "pressed switch1"));
        switch1.addAction(new TriggerActionHandler(switch1, new BeTalkedTo(switch1),
                switch1ActivadoTriggers, switch1ActivadoTriggeredValues));
        objects.put(switch1.getName(), switch1);
    }

    private void crearSwitch2() {
        switch2 = new ConcreteGameObjectWithParent("switch2", room1);
        switch2.addAction(new BeLookedAt(switch2));
        switch2.addAction(new BeAskedWhat(switch2));
        List<BooleanState> switch2ActivadoTriggers = new LinkedList<>();
        switch2ActivadoTriggers.add(switch2IsPressed);
        switch2ActivadoTriggers.add(switch1IsPressed);
        List<Boolean> switch2ActivadoTriggeredValues = new LinkedList<>();
        switch2ActivadoTriggeredValues.add(true);
        switch2ActivadoTriggeredValues.add(false);
        switch2.addAction(new BeTalkedTo(switch2, "pressed switch2"));
        switch2.addAction(new TriggerActionHandler(switch2, new BeTalkedTo(switch2),
                switch2ActivadoTriggers, switch2ActivadoTriggeredValues));
        objects.put(switch2.getName(), switch2);
    }

    private void populateRoom2() {
        // cuarto 2 shore 1

        createPilesAndDiscs();

        List<ActionHandler> actionsGrantedDisc9 = new LinkedList<>();
        actionsGrantedDisc9.add(new Unlock(player, 9));
        disc9.removeAction("be moved");
        disc9.addAction(new BeMovedGrantsActions(disc9, disc9.getParentState(), actionsGrantedDisc9));

        // simulacion de soga que se rompe si tenes mas de dos discos

        estabaEnShore1TurnoAnterior = new BooleanState(false);
        estaEnShore1Ahora = new BooleanState(false);

        // cuarto 2 shore 2

        torre4 = new Pile("pile4", room2shore2);
        objects.put(torre4.getName(), torre4);
        torre5 = new Pile("pile5", room2shore2);
        objects.put(torre5.getName(), torre5);
        torre6 = new Pile("pile6", room2shore2);
        objects.put(torre6.getName(), torre6);

        doorTo3 = new LinkingLockedDoor("doorTo3", room2shore2, 9, room3, new BooleanState());
        objects.put(doorTo3.getName(), doorTo3);
    }

    private void createPilesAndDiscs() {
        torre1 = new Pile("pile1", room2shore1);
        objects.put(torre1.getName(), torre1);
        disc3 = new Disc("disc3", torre1, 3);
        objects.put(disc3.getName(), disc3);
        disc2 = new Disc("disc2", torre1, 2);
        objects.put(disc2.getName(), disc2);
        disc1 = new Disc("disc1", torre1, 1);
        objects.put(disc1.getName(), disc1);

        torre2 = new Pile("pile2", room2shore1);
        objects.put(torre2.getName(), torre2);
        disc6 = new Disc("disc6", torre2, 6);
        objects.put(disc6.getName(), disc6);
        disc5 = new Disc("disc5", torre2, 5);
        objects.put(disc5.getName(), disc5);
        disc4 = new Disc("disc4", torre2, 4);
        objects.put(disc4.getName(), disc4);

        createPile3();
    }

    private void createPile3() {
        torre3 = new Pile("pile3", room2shore1);
        objects.put(torre3.getName(), torre3);
        disc9 = new Disc("disc9", torre3, 9);
        objects.put(disc9.getName(), disc9);
        disc8 = new Disc("disc8", torre3, 8);
        objects.put(disc8.getName(), disc8);
        disc7 = new Disc("disc7", torre3, 7);
        objects.put(disc7.getName(), disc7);
    }

    private void populateRoom3() {
        // room3

        arqueologo = new Player("archeologist", room3);
        objects.put(arqueologo.getName(), arqueologo);
        arqueologo.addAction(new HaveMovedTo(arqueologo, arqueologo.getChildrenState()));
        String dialogoArqueologo = "I'm looking for an ancient artifact called 'disc9'. Unfortunately, I'm to old to actively look for it."
                + " If you find it would you be so kind as to 'put' it inside my backpack?";
        arqueologo.removeAction("be moved");
        arqueologo.addAction(new BeTalkedTo(arqueologo, dialogoArqueologo));
        arqueologo.addAction(new BeAskedWhat(arqueologo));

        salida = new LinkingDoor("doorExit", room3, room4);
        objects.put(salida.getName(), salida);
    }

    private void createPlayer() {
        // player

        player.setParent(room1);
        room1.addChild(player);

        ActionHandler talk = new Talk(player);
        player.addAction(talk);
        commands.add(talk.getName());

        ActionHandler pick = new Pick(player);
        player.addAction(pick);
        commands.add(pick.getName());

        ActionHandler open = new Open(player);
        player.addAction(open);
        commands.add(open.getName());

        ActionHandler cross = new Cross(player);
        player.addAction(cross);
        commands.add(cross.getName());

        createPlayerCont();
    }

    private void createPlayerCont() {
        ActionHandler use = new Use(player);
        player.addAction(use);
        commands.add(use.getName());

        ActionHandler leave = new Leave(player);
        player.addAction(new Leave(player));
        commands.add(leave.getName());

        ActionHandler haveMovedFrom = new HaveMovedFrom(player, player.getChildrenState());
        player.addAction(haveMovedFrom);
        commands.add(haveMovedFrom.getName());

        ActionHandler moveFromInventory = new MoveFromInventory(player);
        player.addAction(moveFromInventory);
        commands.add(moveFromInventory.getName());

        ActionHandler checkTop = new CheckTop(player);
        player.addAction(checkTop);
        commands.add(checkTop.getName());
    }

    @SuppressWarnings("CPD-END")

    @Override
    public Game build() {

        createRooms();

        populateRoom1();

        populateRoom2();

        populateRoom3();

        createPlayer();

        return this;
    }
}
