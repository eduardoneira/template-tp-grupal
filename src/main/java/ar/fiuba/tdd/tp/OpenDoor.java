package ar.fiuba.tdd.tp;
import ar.fiuba.tdd.tp.actions.open.Open;
import ar.fiuba.tdd.tp.objects.concrete.Key;
import ar.fiuba.tdd.tp.objects.concrete.door.LockedDoor;

import ar.fiuba.tdd.tp.actions.pick.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Room;

import java.util.concurrent.locks.Lock;

/**
 * Created by fernando on 28/04/16.
 */
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
        key = new Key(nameKey);
        door = new LockedDoor(nameDoor,1);
        keywords.add(nameKey);
        keywords.add(nameRoom);
        player.placeInRoom(room);

        Open actionOpen = new Open (player);
        Pick actionPickup = new Pick(player);
        keywords.add(actionPickup.getName());
        player.addAction(actionPickup);
        player.addAction(actionOpen);

        room.addChild(key);
    }

    @Override
    public boolean checkWinCondition() { return door.isOpen(); }
}
