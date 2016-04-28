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

public class MainTests {

    private Room room;
    private Player player;
    private Stick stick;

    @Before
    public void initialization() {
        room = new Room("room");

        player = new PlayerCursedObject("player");
        player.placeInRoom(room);

        stick = new Stick("stick");
    }

    @Test
    public void lookAtEmptyRoomTest() {

        player.addAction(new Look(player));

        assertEquals((player.doAction("look", new ArrayList<String>() {
            {
                add(room.getName());
            }
        })),"a room");
    }

    @Test
    public void lookAtRoomWithStickTest() {

        room.addChild(stick);
        stick.setParent(room);

        assertEquals((player.handleAction("look", new ArrayList<GameObject>() {
            {
                add(room);
            }
        })).trim(),"there're a stick");
    }

    @Test
    public void lookAtRoomWithBoxChangingVisibilityTest() {

        Box box = new Box("a box");
        box.addChild(stick);
        stick.setParent(box);

        room.addChild(box);
        box.setParent(room);

        assertEquals((player.handleAction("look", new ArrayList<GameObject>() {
            {
                add(room);
            }
        })).trim(),"there're a room a box");

        box.setOpen();
        assertEquals((player.handleAction("look", new ArrayList<GameObject>() {
            {
                add(room);
            }
        })).trim(),"there're a room a box a stick");
    }

    @Test
    public void whatStick() {
        room.addChild(stick);
        stick.setParent(room);

        player.addAction(new What(player));

        assertEquals((player.handleAction("what", new ArrayList<GameObject>() {
            {
                add(stick);
            }
        })).trim(),"it can ");
    }
}
