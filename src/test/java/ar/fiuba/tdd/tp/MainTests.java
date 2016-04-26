package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Open;
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
        System.out.println(stick.getName());
        System.out.println(room.haveMovedTo(stick));
        assert(room.contains("new stick"));
        System.out.println(room.lookAt());
        player.addAction(new Pick());
        System.out.println(player.doAction("pick", new ArrayList<String>(){{add("new stick");}}));
        Door door = new Door("door");
        door.setParent(room);
        assert(room.contains("door"));
        assert(player.has("new stick"));
        assertEquals(0, 0);
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
        assert(room.contains("door"));
    }

    @Test
    public void playerPicksObject() {
        Room room = new Room("room");
        Stick stick = new Stick("stick");
        room.haveMovedTo(stick);
        Player player = new Player("player");
        player.placeInRoom(room);
        player.addAction(new Pick());
        player.doAction("pick", new ArrayList<String>(){{add("stick");}});
        assert(player.has("stick"));
    }

    @Test
    public void playerOpensDoor() {
        Room room = new Room("room");
        Door door = new Door("door");
        room.place(door);
        Player player = new Player("player");
        player.placeInRoom(room);
        player.addAction(new Open());
        player.doAction("open", new ArrayList<String>(){{add("stick");}});
        //assert(door.isOpen());
    }
}
