package ar.fiuba.tdd.tp;


import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;
import ar.fiuba.tdd.tp.objects.concrete.Thief;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.concrete.player.PlayerCursedObject;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LadronTests {

    private Room room;
    private Player player;
    private Stick stick;
    private Thief thief;

    @Before
    public void initialization() {
        room = new Room("room");

        player = new PlayerCursedObject("player");
        player.placeInRoom(room);

        stick = new Stick("stick");

        thief = new Thief("thief");
        thief.setParent(room);
        room.addChild(thief);
    }

    @Test
    public void talkToThiefStickInRoom() {
        stick.setParent(room);
        room.addChild(stick);

        assert (room.contains("stick"));
        assert (!player.contains("stick"));

        player.handleAction("talk", new ArrayList<GameObject>() {
            {
                add(thief);
            }
        } );

        assert (room.contains("stick"));
        assert (!player.contains("stick"));
    }

    @Test
    public void talkToThiefStickOnPlayer() {
        stick.setParent(player);
        player.addChild(stick);

        assert (player.contains("stick"));

        player.handleAction("talk", new ArrayList<GameObject>() {
            {
                add(thief);
            }
        } );

        assert (!player.contains("stick"));
    }
}
