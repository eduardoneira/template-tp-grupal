package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.model.AbstractCondition;
import ar.fiuba.tdd.tp.model.ConditionCheckContains;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.CursedKey;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Thief;
import ar.fiuba.tdd.tp.objects.concrete.door.AntiCurseLinkingDoor;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingLockedDoor;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;
import java.util.Set;

public class CursedObject extends Game {

    private Room room1;
    private LinkingLockedDoor door1to2;
    private CursedKey keyObject;
    private static final int keyNumber = 2;

    private Room room2;
    private AntiCurseLinkingDoor door2to3;

    private Room room3;

    private Thief thief;

    @SuppressWarnings("CPD-START")
    //BEGIN GENERATED CODE

    private void initialization() {

        createRooms();

        createDoors();

        createKey();

        //createPlayer();

        createThief();
    }

    private void createRooms() {
        room1 = new Room("room1");
        objects.put(room1.getName(), room1);

        room2 = new Room("room2");
        objects.put(room2.getName(), room2);
        room3 = new Room("room3");
        objects.put(room3.getName(), room3);
    }

    private void createDoors() {
        door1to2 = new LinkingLockedDoor("door1to2", room1, keyNumber, room2, new BooleanState());
        door1to2.setClosed();
        door1to2.setLocked();
        objects.put(door1to2.getName(), door1to2);

        door2to3 = new AntiCurseLinkingDoor("door2to3", room2, room3);
        door2to3.setClosed();
        objects.put(door2to3.getName(), door2to3);
    }

    private void createKey() {
        keyObject = new CursedKey("key", room1, keyNumber);
        objects.put(keyObject.getName(), keyObject);
    }

    @Override
    protected Player configPlayer(String playerId, String type) {
        Player player = new Player("player" + Integer.toString(players.size() + 1), null);
        Set<String> commands = commandsPerPlayer.get(playerId);
        List<AbstractCondition> winConds = winConditionsPerPlayer.get(playerId);

        player.setParent(room1);
        room1.addChild(player);

        Open openAction = new Open(player);
        player.addAction(openAction);
        commands.add(openAction.getName());

        HaveEverythingStolen moveAction = new HaveEverythingStolen(player, player.getChildrenState());
        player.addAction(moveAction);

        HaveMovedFrom movedFrom = new HaveMovedFrom(player, player.getChildrenState());
        player.addAction(movedFrom);

        Pick pickAction = new Pick(player);
        player.addAction(pickAction);
        commands.add(pickAction.getName());

        Talk talkAction = new Talk(player);
        player.addAction(talkAction);
        commands.add(talkAction.getName());

        winConds.add(new ConditionCheckContains(room3.getChildrenState(), player.getName(), true));

        return player;
    }

    @Override
    protected void updateGameAfterHandle(String playerId) {

    }

    private void createThief() {
        thief = new Thief("thief", room2);
        objects.put(thief.getName(), thief);
    }

    @SuppressWarnings("CPD-END")

    public CursedObject() {
        super("CursedObject");
    }

    /*@Override
    public boolean checkWinCondition() {
        return room3.contains("player");
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }*/

    @Override
    public ar.fiuba.tdd.tp.model.Game build() {
        initialization();
        return this;
    }
    //END GENERATED CODE
}
