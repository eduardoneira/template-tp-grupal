package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Open;
import ar.fiuba.tdd.tp.objects.concrete.Door;
import ar.fiuba.tdd.tp.objects.concrete.Key;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
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
        room.addChild(door);
        assert(room.contains("door"));
    }

    @Test
    public void playerOpensDoor() {
        room.addChild(door);
        player.addAction(new Open());
        player.doAction("open", new ArrayList<String>(){{add("stick");}});
        //assert(door.isOpen());
    }
}
