package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.*;
import ar.fiuba.tdd.tp.objects.concrete.player.PlayerCrossShores;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WolfSheepCabbageTests {

    private Room southShore;
    private Room northShore;

    private Wolf wolf;
    private Sheep sheep;
    private Cabbage cabbage;

    private PlayerCrossShores player;

    @Before
    public void initialization() {
        southShore = new Room("south-shore");
        northShore = new Room("north-shore");
        player = new PlayerCrossShores("player");
        player.placeInRoom(southShore);
        wolf = new Wolf("wolf");
        sheep = new Sheep("sheep");
        cabbage = new Cabbage("cabbage");
        southShore.addChild(wolf);
        southShore.addChild(sheep);
        southShore.addChild(cabbage);
        wolf.setParent(southShore);
        sheep.setParent(southShore);
        cabbage.setParent(southShore);
    }

    @Test
    public void gameTest() {
        assert (southShore.contains("wolf"));
        System.out.println(player.handleAction("take", new ArrayList<GameObject>() {
            {
                add(wolf);
            }
        }));
        assert (!southShore.contains("wolf"));
        System.out.println(player.handleAction("take", new ArrayList<GameObject>() {
            {
                add(sheep);
            }
        }));
        System.out.println(player.handleAction("leave", new ArrayList<GameObject>() {
            {
                add(wolf);
            }
        }));
        assert (southShore.contains("wolf"));
        System.out.println(player.handleAction("take", new ArrayList<GameObject>() {
            {
                add(sheep);
            }
        }));
        assert (!southShore.contains("sheep"));
        System.out.println(player.handleAction("change room", new ArrayList<GameObject>() {
            {
                add(northShore);
            }
        }));
        assertEquals("north-shore", player.getParent().getName());
        secondGameTest();
    }

    public void secondGameTest() {
        assert (northShore.contains("sheep"));
        System.out.println(player.handleAction("leave", new ArrayList<GameObject>() {
            {
                add(sheep);
            }
        }));
        assertFalse(player.contains("sheep"));
        System.out.println(player.handleAction("change room", new ArrayList<GameObject>() {
            {
                add(southShore);
            }
        }));
        System.out.println(player.handleAction("take", new ArrayList<GameObject>() {
            {
                add(wolf);
            }
        }));
        System.out.println(player.handleAction("change room", new ArrayList<GameObject>() {
            {
                add(northShore);
            }
        }));
        System.out.println(player.handleAction("leave", new ArrayList<GameObject>() {
            {
                add(wolf);
            }
        }));
        assert (northShore.contains("wolf"));
        assert (northShore.contains("sheep"));
        assert (southShore.contains("cabbage"));
    }
}
