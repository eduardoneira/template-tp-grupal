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
        box = new Box(nameBox);
        room = new Room(nameRoom);
        key = new Key(nameKey, 1);
        door = new LockedDoor(nameDoor,1);
        keywords.add(nameKey);
        keywords.add(nameKey);
        keywords.add(nameRoom);
        player.placeInRoom(room);
        room.addChild(player);

        Open actionOpen = new Open(player);
        Pick actionPickup = new Pick(player);
        keywords.add(actionPickup.getName());
        keywords.add(actionOpen.getName());
        player.addAction(actionPickup);
        player.addAction(actionOpen);

        box.addChild(key);
        room.addChild(box);

        objects.put(box.getName(), box);
        objects.put(room.getName(), room);
        objects.put(key.getName(), key);
        objects.put(door.getName(), door);
    }

    @Override
    public boolean checkWinCondition() {
        return door.isOpen();
    }
}
