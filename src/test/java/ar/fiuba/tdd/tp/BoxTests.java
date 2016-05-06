package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Box;
import ar.fiuba.tdd.tp.objects.concrete.Key;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.general.GameObject;
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
        player = new Player("player", room);
        box = new Box("box", room);
    }

    @Test
    public void placeBoxInRoom() {
        assert (room.contains("box"));
        assertEquals(box.getParent().getName(), "room");
    }

    @Test
    public void placeObjectInBox() {
        placeBoxInRoom();
        key = new Key("key", box, 2);
        assert (box.contains("key"));
        assertEquals(key.getParent().getName(), "box");
    }

    @Test
    public void takeObjectFromBox() {
        placeObjectInBox();
        box.removeChild(key);
        assertFalse(box.contains("Key"));
    }

    @Test
    public void playerPicksObjectFromOpenBox() {
        placeObjectInBox();
        box.setOpen();

        player.addAction(new Pick(player));
        assert (box.contains("key"));
        player.handleAction("pick",
                new ArrayList<GameObject>() {
                    {
                        add(key);
                    }
                });
        assert (!box.contains("key"));
        assert (player.contains("key"));
    }

    @Test
    public void playerPicksObjectFromClosedBox() {
        placeObjectInBox();
        box.setClosed();

        player.addAction(new Pick(player));
        assert (box.contains("key"));
        player.handleAction("pick",
                new ArrayList<GameObject>() {
                    {
                        add(key);
                    }
                });
        assert (box.contains("key"));
        assert (!player.contains("key"));
    }
}
