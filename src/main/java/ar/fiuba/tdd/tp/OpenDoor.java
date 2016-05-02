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

    public OpenDoor() {
        super("Open Door");
        room = new Room(nameRoom);
        key = new Key(nameKey, room, 1);
        door = new LockedDoor(nameDoor, room, 1);
        door.setClosed();
        door.setLocked();
        keywords.add(nameKey);
        Pick actionPickup = new Pick(player);
        keywords.add(actionPickup.getName());

        keywords.add(nameRoom);
        keywords.add(door.getName());
        player.placeInRoom(room);
        room.addChild(player);
        Open actionOpen = new Open(player);
        player.addAction(actionPickup);
        player.addAction(actionOpen);
        setGame();
        addActionToKeywords();
    }

    public void setGame() {
        objects.put(room.getName(), room);
        objects.put(door.getName(), door);
        room.addChild(door);
        room.addChild(key);

        objects.put(key.getName(), key);
    }

    @Override
    public boolean checkWinCondition() {
        return door.isOpen();
    }
}
