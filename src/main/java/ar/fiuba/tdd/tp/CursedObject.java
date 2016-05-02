package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.concrete.CursedKey;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Thief;
import ar.fiuba.tdd.tp.objects.concrete.door.AntiCurseLinkingDoor;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingLockedDoor;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.concrete.player.PlayerCursedObject;

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
        keywords.add(room1.getName());

        room2 = new Room("room2");
        objects.put(room2.getName(), room2);
        keywords.add(room2.getName());

        room3 = new Room("room3");
        objects.put(room3.getName(), room3);
        keywords.add(room3.getName());
    }

    private void createDoors() {
        door1to2 = new LinkingLockedDoor("door1to2", room1, keyNumber, room2);
        door1to2.setClosed();
        door1to2.setLocked();
        room1.addChild(door1to2);
        objects.put(door1to2.getName(), door1to2);
        keywords.add(door1to2.getName());

        door2to3 = new AntiCurseLinkingDoor("door2to3", room2, room3);
        door2to3.setClosed();
        room2.addChild(door2to3);
        objects.put(door2to3.getName(), door2to3);
        keywords.add(door2to3.getName());
    }

    private void createKey() {
        keyObject = new CursedKey("key", room1, keyNumber);
        keyObject.setParent(room1);
        room1.addChild(keyObject);
        objects.put(keyObject.getName(), keyObject);
        keywords.add(keyObject.getName());
    }

    private void createPlayer() {
        player.placeInRoom(room1);
        room1.addChild(player);

        Open openAction = new Open(player);
        player.addAction(openAction);
        keywords.add(openAction.getName());

        HaveEverythingStolen moveAction = new HaveEverythingStolen(player, player.getChildrenState());
        player.addAction(moveAction);

        HaveMovedFrom movedFrom = new HaveMovedFrom(player, player.getChildrenState());
        player.addAction(movedFrom);

        Pick pickAction = new Pick(player);
        player.addAction(pickAction);
        keywords.add(pickAction.getName());

        Talk talkAction = new Talk(player);
        player.addAction(talkAction);
        keywords.add(talkAction.getName());
    }

    private void createThief() {
        thief = new Thief("thief", room2);
        thief.setParent(room2);
        room2.addChild(thief);
        objects.put(thief.getName(), thief);
        keywords.add(thief.getName());
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
}
