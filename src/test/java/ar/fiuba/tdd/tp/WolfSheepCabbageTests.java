package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import org.junit.Before;

public class WolfSheepCabbageTests {

    private Room southShore;
    private Room northShore;
    /*
    private Stick wolf;
    private Stick sheep;
    private Stick cabbage;
    */
    private Player player;

    @Before
    public void initialization() {
        southShore = new Room("south-shore");
        northShore = new Room("north-shore");
        player = new Player("player");
        /*wolf = new Stick("wolf");
        sheep = new Stick("sheep");
        cabbage = new Stick("cabbage");*/
    }
}
