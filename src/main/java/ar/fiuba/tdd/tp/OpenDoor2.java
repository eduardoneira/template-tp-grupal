package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.open.Open;
import ar.fiuba.tdd.tp.actions.pick.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Box;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.LockedDoor;

/**
 * Created by fernando on 28/04/16.
 */
public class OpenDoor2 extends Game {

    Room cuarto;
    Box box;
    LockedDoor door;
    String nameRoom = "room";
    String nameKey = "key";
    String nameDoor = "door";
    String nameBox = "box";

    public OpenDoor2() {

        super("Open Door 2");
        box = new Box(nameBox);
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
    public boolean checkWinCondition() {return door.isOpen();}
}
