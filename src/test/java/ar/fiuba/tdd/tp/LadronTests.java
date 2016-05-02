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

        player = new PlayerCursedObject("player", room);

        stick = new Stick("stick", player);

        thief = new Thief("thief", room);
        room.addChild(thief);
    }

    @Test
    public void talkToThiefStickInRoom() {
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
