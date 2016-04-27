package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.lookat.Look;
import ar.fiuba.tdd.tp.objects.concrete.Box;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;
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
    public void lookAtEmptyRoomTest() {

        final Player player = new Player("player");
        final Room room = new Room("a room");

        player.placeInRoom(room);
        player.addAction(new Look(player));

        assertEquals((player.doAction("look", new ArrayList<String>() {
            {
                add(room.getName());
            }
        })),"a room");
    }

    @Test
    public void lookAtRoomWithStickTest() {

        final Player player = new Player("player");
        final Room room = new Room("a room");

        final Stick stick = new Stick("a stick");
        room.addChild(stick);
        stick.setParent(room);

        player.placeInRoom(room);
        player.addAction(new Look(player));

        assertEquals((player.doAction("look", new ArrayList<String>() {
            {
                add(room.getName());
            }
        })).trim(),"there're a stick");
    }

    @Test
    public void lookAtRoomWithBoxChangingVisibilityTest() {

        final Player player = new Player("player");
        final Room room = new Room("a room");

        final Box box = new Box("a box");
        final Stick stick = new Stick("a stick");
        box.addChild(stick);
        stick.setParent(box);

        room.addChild(box);
        box.setParent(room);

        player.placeInRoom(room);
        player.addAction(new Look(player));
        assertEquals((player.doAction("look", new ArrayList<String>() {
            {
                add(room.getName());
            }
        })).trim(),"there're a room a box");

        box.setOpen();
        assertEquals((player.doAction("look", new ArrayList<String>() {
            {
                add(room.getName());
            }
        })).trim(),"there're a room a box a stick");
    }
}
