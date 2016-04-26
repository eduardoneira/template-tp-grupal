package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.Door;
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
        System.out.println(room.lookAt());
        player.addAction(new Pick());
        System.out.println(player.doAction("pick", new ArrayList<String>(){{add("stick");}}));
        Door door = new Door("door");
        assertEquals(0,0);
    }
}
