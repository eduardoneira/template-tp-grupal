package ar.fiuba.tdd.tp;
import ar.fiuba.tdd.tp.actions.open.Open;
import ar.fiuba.tdd.tp.objects.concrete.door.LockedDoor;

import ar.fiuba.tdd.tp.actions.pick.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Room;

import java.util.concurrent.locks.Lock;

/**
 * Created by fernando on 28/04/16.
 */
public class OpenDoor extends Game {

    Room cuarto;
    LockedDoor door;
    String nameRoom = "room";
    String nameKey = "key";
    String nameDoor = "door";

    public OpenDoor() {
        super("Open Door");
        cuarto = new Room(nameRoom);
        door = new LockedDoor(nameDoor,1);
        keywords.add(nameKey);
        keywords.add(nameRoom);
        player.placeInRoom(cuarto);

        Open actionOpen = new Open (player);
        Pick actionPickup = new Pick(player);
        keywords.add(actionPickup.getName());
        player.addAction(actionPickup);
        player.addAction(actionOpen);
    }

    @Override
    public boolean checkWinCondition() { return door.isOpen(); }
}
