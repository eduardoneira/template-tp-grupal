package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Open;
import ar.fiuba.tdd.tp.actions.Pick;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AbrirPuertaTests {

    private Room room;
    private Player player;
    private Key key;
    private Door door;

    @Before
    public void initialization() {
        room = new Room("room");
        player = new Player("player");
        player.placeInRoom(room);
        key = new Key("key");
        door = new Door("door");
    }

    @Test
    public void placeDoorInRoom() {
        room.place(door);
        assert(room.contains("door"));
    }

    @Test
    public void playerOpensDoor() {
        room.place(door);
        player.addAction(new Open());
        player.doAction("open", new ArrayList<String>(){{add("stick");}});
        //assert(door.isOpen());
    }
}
