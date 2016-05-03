package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Move;
import ar.fiuba.tdd.tp.actions.Open;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.Door;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingDoor;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Master on 28/04/2016.
 */
public class ChangeRoomTests {

    private Room room1;
    private Room room2;
    private Door door1;
    private Door door2;
    private Player player;

    @Before
    public void initialization() {
        room1 = new Room("room1");
        room2 = new Room("room2");

        door1 = new LinkingDoor("door1", room1, room2);
        door1.setClosed();
        room1.addChild(door1);

        door2 = new LinkingDoor("door2", room2, room1);
        door2.setClosed();
        room2.addChild(door2);

        player = new Player("player", room1);
        player.addAction(new Open(player));
        player.addAction(new Move(player));
        room1.addChild(player);
    }

    @Test
    public void openDoorMovesRoom() {

        List<GameObject> involved = new LinkedList<>();
        involved.add(door1);

        assert (player.getParent().equals(room1));
        player.handleAction("open", involved);
        assert (player.getParent().equals(room2));
    }
}
