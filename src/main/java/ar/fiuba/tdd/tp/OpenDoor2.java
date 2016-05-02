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

    public OpenDoor2() {

        super("Open Door 2");
        room = new Room(nameRoom);
        box = new Box(nameBox, room);
        key = new Key(nameKey, box, 1);
        box.setClosed();
        door = new LockedDoor(nameDoor, room, 1);
        room.addChild(door);
        keywords.add(nameKey);
        keywords.add(nameRoom);
        player.placeInRoom(room);
        Open actionOpen = new Open(player);
        keywords.add(actionOpen.getName());
        Pick actionPickup = new Pick(player);
        keywords.add(actionPickup.getName());
        player.addAction(actionPickup);
        player.addAction(actionOpen);
        addObjects();
        addActionToKeywords();
    }

    public void addObjects() {
        door.setLocked();
        door.setClosed();
        room.addChild(player);
        box.addChild(key);
        room.addChild(box);
        objects.put(box.getName(), box);
        objects.put(room.getName(), room);
        keywords.add(box.getName());
        keywords.add(door.getName());
        objects.put(key.getName(), key);
        objects.put(door.getName(), door);
    }

    @Override
    public boolean checkWinCondition() {
        return door.isOpen();
    }
}
