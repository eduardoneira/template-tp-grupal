package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Look;
import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.actions.What;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WhatTests {

    private Room room;
    private Player player;
    private Stick stick;

    @Before
    public void initialization() {
        room = new Room("room");

        player = new Player("player");
        player.placeInRoom(room);
        player.addAction(new Pick(player));

        stick = new Stick("stick");
        stick.setParent(room);
        room.addChild(stick);
    }

    @Test
    public void whatStick() {
        assertEquals((player.handleAction("what", new ArrayList<GameObject>() {
            {
                add(stick);
            }
        }))
                .trim(),"You can pick look");
    }
}
