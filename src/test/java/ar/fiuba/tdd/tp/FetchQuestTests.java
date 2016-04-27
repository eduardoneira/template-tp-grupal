package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Open;
import ar.fiuba.tdd.tp.actions.Pick;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FetchQuestTests {

    private Room room;
    private Player player;
    private Stick stick;
    private Door door;

    @Before
    public void initialization() {
        room = new Room("room");
        player = new Player("player");
        player.placeInRoom(room);
        stick = new Stick("stick");
        door = new Door("door");
    }

    @Test
    public void gameTest() {
        System.out.println(stick.getName());
        System.out.println(room.haveMovedTo(stick));
        assert(room.contains("stick"));
        System.out.println(room.lookAt());
        player.addAction(new Pick());
        System.out.println(player.doAction("pick", new ArrayList<String>(){{add("stick");}}));
        door.setParent(room);
        assert(room.contains("door"));
        assert(player.has("stick"));
    }

    @Test
    public void placeObjectInRoom() {
        room.haveMovedTo(stick);
        assert(room.contains("stick"));
    }

    @Test
    public void placeDoorInRoom() {
        room.place(door);
        assert(room.contains("door"));
    }

    @Test
    public void playerPicksObject() {
        room.haveMovedTo(stick);
        player.addAction(new Pick());
        player.doAction("pick", new ArrayList<String>(){{add("stick");}});
        assert(player.has("stick"));
    }

    @Test
    public void playerOpensDoor() {
        room.place(door);
        player.addAction(new Open());
        player.doAction("open", new ArrayList<String>(){{add("stick");}});
        //assert(door.isOpen());
    }
}

