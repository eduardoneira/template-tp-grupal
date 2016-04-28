package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.Disc;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Torre;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import org.junit.Before;

public class HanoiTests {

    private Room room;
    private Player player;
    private Torre torre1;
    private Torre torre2;
    private Torre torre3;

    private Disc disc1;
    private Disc disc2;
    private Disc disc3;

    @Before
    public void initialization() {
        room = new Room("room");
        player = new Player("player");
        player.placeInRoom(room);
        torre1 = new Torre("torre1");
        torre2 = new Torre("torre2");
        torre3 = new Torre("torre3");
        disc1 = new Disc("disc1",1);
        disc2 = new Disc("disc2",2);
        disc3 = new Disc("disc3",3);


        /*
        player.addAction(new CheckTop(player));
        player.addAction(new MoveTop(player));
        */
    }
}
