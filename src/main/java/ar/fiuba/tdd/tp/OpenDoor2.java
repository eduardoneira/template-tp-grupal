package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Open;
import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Box;
import ar.fiuba.tdd.tp.objects.concrete.Key;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.LockedDoor;

public class OpenDoor2 extends Game {

    Room room;
    Box box;
    Key key;
    LockedDoor door;
    String nameRoom = "room";
    String nameKey = "key";
    String nameDoor = "door";
    String nameBox = "box";

    @SuppressWarnings("CPD-START")

    public OpenDoor2() {

        super("Open Door 2");

        createRoom();

        createDoor();

        createBox();

        createKey();

        createPlayer();
    }

    private void createRoom() {
        room = new Room(nameRoom);
        objects.put(room.getName(), room);
    }

    private void createDoor() {
        door = new LockedDoor(nameDoor, room, 1);
        door.setLocked();
        door.setClosed();
        door.setParent(room);
        room.addChild(door);
        objects.put(door.getName(), door);
    }

    private void createBox() {
        box = new Box(nameBox, room);
        box.setClosed();
        box.setParent(room);
        room.addChild(box);
        objects.put(box.getName(), box);
    }

    private void createKey() {
        key = new Key(nameKey, room, 1);
        key.setParent(box);
        box.addChild(key);
        objects.put(key.getName(), key);
    }

    private void createPlayer() {
        player.placeInRoom(room);
        room.addChild(player);

        Open actionOpen = new Open(player);
        player.addAction(actionOpen);
        commands.add(actionOpen.getName());

        Pick actionPickup = new Pick(player);
        player.addAction(actionPickup);
        commands.add(actionPickup.getName());
    }

    @SuppressWarnings("CPD-END")

    @Override
    public boolean checkWinCondition() {
        return door.isOpen();
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }
}
