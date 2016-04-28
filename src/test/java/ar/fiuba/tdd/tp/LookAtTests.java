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

        player = new PlayerCursedObject("player");
        player.placeInRoom(room);
        player.addAction(new Look(player));

        stick = new Stick("stick");
    }

    @Test
    public void lookAtEmptyRoomTest() {

        assertEquals("a room", (player.handleAction("look", new ArrayList<GameObject>() {
            {
                add(room);
            }
        })));
    }

    @Test
    public void lookAtRoomWithStickTest() {

        room.addChild(stick);
        stick.setParent(room);

        assertEquals("there're a stick", (player.handleAction("look", new ArrayList<GameObject>() {
            {
                add(room);
            }
        })).trim());
    }

    @Test
    public void lookAtRoomWithBoxChangingVisibilityTest() {

        Box box = new Box("a box");
        box.addChild(stick);
        stick.setParent(box);

        room.addChild(box);
        box.setParent(room);

        assertEquals("there're a room a box", (player.handleAction("look", new ArrayList<GameObject>() {
            {
                add(room);
            }
        })).trim());

        box.setOpen();
        assertEquals("there're a room a box a stick", (player.handleAction("look", new ArrayList<GameObject>() {
            {
                add(room);
            }
        })).trim());
    }
}
