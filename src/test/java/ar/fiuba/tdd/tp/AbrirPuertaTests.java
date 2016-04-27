package ar.fiuba.tdd.tp;

import org.junit.Before;

package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Open;
import ar.fiuba.tdd.tp.actions.Pick;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AbrirPuertaTests {

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
    public void placeDoorInRoom() {
        room.place(door);
        assert(room.contains("door"));
    }


}
