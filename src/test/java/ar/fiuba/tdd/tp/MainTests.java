package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.CanHavePlaced;
import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.Door;
import ar.fiuba.tdd.tp.objects.Room;
import ar.fiuba.tdd.tp.objects.Stick;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MainTests {

    @Test
    public void dummy() {
        assertEquals(0, 0);
    }

    @Test
    public void gameTest() {
        Room room = new Room("new room");
        Player player = new Player("new player");
        player.placeInRoom(room);
        Stick stick = new Stick("new stick");
        System.out.println(room.haveMovedTo(stick));
        assert(room.contains("new stick"));
        System.out.println(room.lookAt());
        player.addAction(new Pick());
        System.out.println(player.doAction("pick", new ArrayList<String>(){{add("new stick");}}));
        Door door = new Door("door");
        door.setParent((CanHavePlaced) room);
        assert(room.contains("door"));
        assertEquals(0,0);
    }

    @Test
    public void placeObjectInRoom() {
        Room room = new Room("room");
        Stick stick = new Stick("stick");
        room.haveMovedTo(stick);
        assert(room.contains("stick"));
    }

    @Test
    public void placeDoorInRoom() {
        Room room = new Room("room");
        Door door = new Door("door");
        room.place(door);
        assert (room.contains("door"));
    }
}
