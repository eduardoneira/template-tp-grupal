package ar.fiuba.tdd.tp;


import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;
import ar.fiuba.tdd.tp.objects.concrete.Thief;
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

        player = new Player("player", room);
        player.addAction(new Open(player));
        player.addAction(new Move(player));
        player.addAction(new Pick(player));
        player.addAction(new Talk(player));
        player.addAction(new HaveEverythingStolen(player, player.getChildrenState()));
        player.addAction(new HaveMovedFrom(player, player.getChildrenState()));
        thief = new Thief("thief", room);
    }

    @Test
    public void talkToThiefStickInRoom() {
        stick = new Stick("stick", room);
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
        stick = new Stick("stick", player);
        assert (player.contains("stick"));

        player.handleAction("talk", new ArrayList<GameObject>() {
            {
                add(thief);
            }
        } );

        assert (!player.contains("stick"));
    }
}
