package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.cross.Cross;
import ar.fiuba.tdd.tp.actions.leave.Leave;
import ar.fiuba.tdd.tp.actions.take.Take;
import ar.fiuba.tdd.tp.objects.concrete.*;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class WolfSheepCabbageTests {

    private Room southShore;
    private Room northShore;

    private Wolf wolf;
    private Sheep sheep;
    private Cabbage cabbage;

    private Player player;

    @Before
    public void initialization() {
        southShore = new Room("south-shore");
        northShore = new Room("north-shore");
        player = new Player("player");
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
        player.addAction(new Take(player));
        player.addAction(new Leave(player));
        player.addAction(new Cross(player));
    }

    @Test
    public void gameTest(){
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
    }
}
