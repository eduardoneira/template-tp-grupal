package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stack;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import org.junit.Before;

public class HanoiTests {

    private Room room;
    private Player player;
    private Stack stack1;
    private Stack stack2;
    private Stack stack3;

    @Before
    public void initialization() {
        room = new Room("room");
        player = new Player("player");
        player.placeInRoom(room);
        stack1 = new Stack("stack1");
        stack2 = new Stack("stack2");
        stack3 = new Stack("stack3");
        /*
        player.addAction(new CheckTop(player));
        player.addAction(new MoveTop(player));
        */
    }
}
