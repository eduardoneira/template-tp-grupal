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
        southShore.addChild(player);
        wolf = new Wolf("wolf");
        sheep = new Sheep("sheep");
        cabbage = new Cabbage("cabbage");
        wolf.setParent(southShore);
        sheep.setParent(southShore);
        cabbage.setParent(southShore);
        southShore.addChild(wolf);
        southShore.addChild(sheep);
        southShore.addChild(cabbage);
    }

    public String cross(Room room) {
        return player.handleAction("cross", new ArrayList<GameObject>() {
            {
                add(room);
            }
        });
    }

    public String take(GameObject object) {
        return player.handleAction("take", new ArrayList<GameObject>() {
            {
                add(object);
            }
        });
    }

    public String leave(GameObject object) {
        return player.handleAction("leave", new ArrayList<GameObject>() {
            {
                add(object);
            }
        });
    }

    @Test
    public void gameTest() {
        assert (southShore.contains("wolf"));
        take(wolf);
        assert (!southShore.contains("wolf"));
        assertEquals("The boat is full!", take(sheep));
        leave(wolf);
        assert (southShore.contains("wolf"));
        take(sheep);
        assert (!southShore.contains("sheep"));
        assert (southShore.contains("player"));
        cross(northShore);
        assertEquals("north-shore", player.getParent().getName());
        secondGameTest();
    }

    public void secondGameTest() {
        leave(sheep);
        assert (northShore.contains("sheep"));
        assertFalse(player.contains("sheep"));
        assert (northShore.contains("player"));
        cross(southShore);
        take(wolf);
        cross(northShore);
        leave(wolf);
        assert (northShore.contains("wolf"));
        assert (northShore.contains("sheep"));
        assert (southShore.contains("cabbage"));
        take(sheep);
        cross(southShore);
        leave(sheep);
        take(cabbage);
        cross(northShore);
        leave(cabbage);
        cross(southShore);
        take(sheep);
        cross(northShore);
        System.out.println(leave(sheep));
        assert (northShore.contains("sheep"));
        assert (northShore.contains("wolf"));
        assert (northShore.contains("cabbage"));
        assert (southShore.isEmpty());
    }
}
