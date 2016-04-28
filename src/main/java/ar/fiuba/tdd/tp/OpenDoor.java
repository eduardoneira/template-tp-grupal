package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.open.Open;
import ar.fiuba.tdd.tp.actions.pick.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.LockedDoor;


import java.util.concurrent.locks.Lock;

public class OpenDoor extends Game {

    public OpenDoor() {
        super("Open Door");
        String nameRoom = "room";
        String nameKey = "key";
        String nameDoor = "door";

        Room cuarto = new Room(nameRoom);
        LockedDoor door1 = new LockedDoor(nameDoor,1);
        player.placeInRoom(cuarto);
        keywords.add(nameKey);

        Open actionOpen = new Open(player);
        Pick actionPickup = new Pick(player);
        //LookAt actionLookaround = new LookAt();

        player.addAction(actionPickup);
        player.addAction(actionOpen);

        //player.addAction(actionLookaround);

        keywords.add(actionPickup.getName());

        keywords.add(nameRoom);

    }

    @Override
    public boolean checkWinCondition() {
        return false;
    }
}
