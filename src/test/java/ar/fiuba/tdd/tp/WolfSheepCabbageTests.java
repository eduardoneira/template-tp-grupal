package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.*;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import org.junit.Before;

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
        wolf.setParent(southShore);
        sheep.setParent(southShore);
        cabbage.setParent(southShore);
        /*
        player.addAction(new Take(player));
        player.addAction(new Leave(player));
        player.addAction(new Cross(player));
        */
    }
}
