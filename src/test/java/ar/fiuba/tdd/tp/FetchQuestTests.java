package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FetchQuestTests {

    private Room room;
    private Player player;
    private Stick stick;

    @Before
    public void initialization() {
        room = new Room("room");
        player = new Player("player");
        player.placeInRoom(room);
        stick = new Stick("stick");
    }

    @Test
    public void gameTest() {
        System.out.println(stick.getName());
        System.out.println(room.haveMovedTo(stick));
        assert (room.contains("stick"));
        System.out.println(room.lookAt());
        player.addAction(new Pick());
        System.out.println(player.doAction("pick",
                new ArrayList<String>() {
                    {
                        add("stick");
                    }
                } ));
        assert (player.contains("stick"));
    }

    @Test
    public void placeObjectInRoom() {
        room.haveMovedTo(stick);
        assert (room.contains("stick"));
    }

    @Test
    public void playerPicksObject() {
        room.haveMovedTo(stick);
        player.addAction(new Pick());
        player.doAction("pick",
                new ArrayList<String>() {
                    {
                        add("stick");
                    }
                } );
        assert (player.contains("stick"));
    }
}

