package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.LookAt;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MainTests {

    @Test
    public void dummy() {
        assertEquals(0, 0);
    }

    @Test
    public void lookAtTest() {
        final Player player = new Player("player");
        final Room room = new Room("room");
        player.placeInRoom(room);
        player.addAction(new LookAt());
        System.out.println(player.doAction("look", new ArrayList<String>() {
            {
                add(room.getName());
            }
        }));
    }
}
