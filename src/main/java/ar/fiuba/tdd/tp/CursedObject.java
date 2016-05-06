package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.concrete.CursedKey;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Thief;
import ar.fiuba.tdd.tp.objects.concrete.door.AntiCurseLinkingDoor;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingLockedDoor;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public class CursedObject extends Game {

    private Room room1;
    private LinkingLockedDoor door1to2;
    private CursedKey keyObject;
    int keyNumber = 2;

    private Room room2;
    private AntiCurseLinkingDoor door2to3;

    private Room room3;

    private Thief thief;

    @SuppressWarnings("CPD-START")

    private void initialization() {

        createRooms();

        createDoors();

        createKey();

        createPlayer();

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

    private void createPlayer() {
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
    }

    private void createThief() {
        thief = new Thief("thief", room2);
        objects.put(thief.getName(), thief);
    }

    @SuppressWarnings("CPD-END")

    public CursedObject() {
        super("Cursed Object");
        initialization();
    }

    @Override
    public boolean checkWinCondition() {
        return room3.contains("player");
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }
}
