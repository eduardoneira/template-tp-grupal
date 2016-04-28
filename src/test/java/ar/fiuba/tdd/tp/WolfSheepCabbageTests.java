package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Cross;
import ar.fiuba.tdd.tp.actions.Leave;
import ar.fiuba.tdd.tp.actions.Take;
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
    public void gameTest(){
        assert (southShore.contains("wolf"));
        System.out.println(player.handleAction("take", new ArrayList<GameObject>(){
            {
                add(wolf);
            }
        }));
        assert(!southShore.contains("wolf"));
        System.out.println(player.handleAction("take", new ArrayList<GameObject>(){
            {
                add(sheep);
            }
        }));
        System.out.println(player.handleAction("leave", new ArrayList<GameObject>(){
            {
                add(wolf);
            }
        }));
        assert(southShore.contains("wolf"));
        System.out.println(player.handleAction("take", new ArrayList<GameObject>(){
            {
                add(sheep);
            }
        }));
        assert(!southShore.contains("sheep"));
        System.out.println(player.handleAction("cross", new ArrayList<GameObject>(){
            {
                add(northShore);
            }
        }));
        System.out.println(player.currentRoom().getName());
        assertEquals("north-shore", player.currentRoom().getName());

    }
}
