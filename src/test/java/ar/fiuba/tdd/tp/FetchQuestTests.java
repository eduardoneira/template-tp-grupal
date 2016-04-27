package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.pick.Pick;
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
        room.addChild(stick);
        stick.setParent(room);
        assert (room.contains("stick"));

        System.out.println(room.getName());
        player.addAction(new Pick(player));
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
        room.addChild(stick);
        assert (room.contains("stick"));
    }

    @Test
    public void playerPicksObject() {
        room.addChild(stick);
        stick.setParent(room);

        player.addAction(new Pick(player));
        player.doAction("pick",
                new ArrayList<String>() {
                    {
                        add("stick");
                    }
                } );
        assert (player.contains("stick"));
    }
}

