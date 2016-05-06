package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Open;
import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Key;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.LockedDoor;

public class OpenDoor extends Game {

    Room room;
    LockedDoor door;
    Key key;
    String nameRoom = "room";
    String nameKey = "key";
    String nameDoor = "door";

    @SuppressWarnings("CPD-START")

    public OpenDoor() {
        super("Open Door");

        createRoom();

        createDoor();

        createKey();

        createPlayer();
    }

    private void createRoom() {
        room = new Room(nameRoom);
        objects.put(room.getName(), room);
    }

    private void createDoor() {
        door = new LockedDoor(nameDoor, room, 1);
        door.setClosed();
        door.setLocked();
        objects.put(door.getName(), door);
    }

    private void createKey() {
        key = new Key(nameKey, room, 1);
        objects.put(key.getName(), key);
    }

    private void createPlayer() {
        player.setParent(room);
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
