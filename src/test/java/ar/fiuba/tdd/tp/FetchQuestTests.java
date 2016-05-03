package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;
import ar.fiuba.tdd.tp.objects.general.GameObject;
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
        player = new Player("player", room);
        stick = new Stick("stick", room);
    }

    @Test
    public void gameTest() {
        room.addChild(stick);
        assert (room.contains("stick"));

        System.out.println(room.getName());
        player.addAction(new Pick(player));
        System.out.println(player.handleAction("pick",
                new ArrayList<GameObject>() {
                    {
                        add(stick);
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

        player.addAction(new Pick(player));
        player.handleAction("pick",
                new ArrayList<GameObject>() {
                    {
                        add(stick);
                    }
                } );
        assert (player.contains("stick"));
    }
}

