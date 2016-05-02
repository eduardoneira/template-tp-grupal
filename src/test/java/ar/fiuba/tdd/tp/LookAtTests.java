package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Look;
import ar.fiuba.tdd.tp.actions.What;
import ar.fiuba.tdd.tp.objects.concrete.Box;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;
import ar.fiuba.tdd.tp.objects.concrete.Thief;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.concrete.player.PlayerCursedObject;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LookAtTests {

    private Room room;
    private Player player;
    private Stick stick;

    @Before
    public void initialization() {
        room = new Room("room");

        player = new PlayerCursedObject("player", room);
        room.addChild(player);

    }

    @Test
    public void lookAtEmptyRoomTest() {

        assertEquals("there're room", (player.handleAction("look", new ArrayList<GameObject>() {
            {
                add(room);
            }
        }))
                .trim());
    }

    @Test
    public void lookAtRoomWithStickTest() {

        stick = new Stick("stick", room);
        room.addChild(stick);

        assertEquals("there're room stick", (player.handleAction("look", new ArrayList<GameObject>() {
            {
                add(room);
            }
        }))
                .trim());
    }

    @Test
    public void lookAtRoomWithBoxChangingVisibilityTest() {


        Box box = new Box("a box", room);
        stick = new Stick("stick", box);
        box.addChild(stick);

        room.addChild(box);

        assertEquals("there're room a box", (player.handleAction("look", new ArrayList<GameObject>() {
            {
                add(room);
            }
        }))
                .trim());

        box.setOpen();
        assertEquals("there're room a box stick", (player.handleAction("look", new ArrayList<GameObject>() {
            {
                add(room);
            }
        }))
                .trim());
    }
}
