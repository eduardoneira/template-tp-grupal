package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.LookAt;
import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Box;
import ar.fiuba.tdd.tp.objects.concrete.Key;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoxTests {

    Room room;
    Player player;
    Box box;
    Key key;


    @Before
    public void initialization() {
        room = new Room("room");
        player = new Player("player");
        box = new Box("box");
        player.placeInRoom(room);
    }

    @Test
    public void placeBoxInRoom() {
        box.setParent(room);
        room.addChild(box);
        assert (room.contains("box"));
        assertEquals(box.getParent().getName(), "room");
    }

    @Test
    public void placeObjectInBox() {
        placeBoxInRoom();
        key = new Key("key");
        box.haveMovedTo(key);
        key.setParent(box);
        assert (box.contains("key"));
        assertEquals(key.getParent().getName(), "box");
    }

    @Test
    public void takeObjectFromBox() {
        placeObjectInBox();
        box.haveMovedFrom(key);
        assertFalse(box.contains("Key"));
    }

    /*@Test
    public void playerPicksObjectFromBox() {
        placeObjectInBox();
        player.addAction(new Pick());
        assert(room.contains("key"));
        assertEquals(player.doAction("pick",
                new ArrayList<String>() {
                    {
                        add(key.getName());
                    }
                }), "");
    }*/
}
