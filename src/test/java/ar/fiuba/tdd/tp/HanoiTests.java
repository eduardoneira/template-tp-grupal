package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.CheckTop;
import ar.fiuba.tdd.tp.actions.MoveTop;
import ar.fiuba.tdd.tp.objects.concrete.Disc;
import ar.fiuba.tdd.tp.objects.concrete.Pile;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HanoiTests {

    private Room room;
    private Player player;
    private Pile pile1;
    private Pile pile2;
    private Pile pile3;

    private Disc disc1;
    private Disc disc2;
    private Disc disc3;

    public String checkTop(Pile pile) {
        return player.handleAction("checkTop", new ArrayList<GameObject>() {
            {
                add(pile);
            }
        });
    }

    public String moveTop(Pile first, Pile second) {
        return player.handleAction("moveTop", new ArrayList<GameObject>() {
            {
                add(first);
                add(second);
            }
        });
    }

    @Before
    public void initialization() {
        room = new Room("room");
        player = new Player("player", room);
        pile1 = new Pile("Stack1", room);
        pile2 = new Pile("Stack2", room);
        pile3 = new Pile("Stack3", room);
        setGame();
        player.addAction(new CheckTop(player));
        player.addAction(new MoveTop(player));
    }

    public void setGame() {
        room.addChild(pile1);
        room.addChild(pile2);
        room.addChild(pile3);
        disc1 = new Disc("disc1", pile1, 1);
        disc2 = new Disc("disc2", pile1, 2);
        disc3 = new Disc("disc3", pile1, 3);
        pile1.addChild(disc1);
        pile1.addChild(disc2);
        pile1.addChild(disc3);
    }

    @Test
    public void hanoiGameTest() {
        assert (pile1.contains("disc1"));
        assert (pile1.contains("disc2"));
        assert (pile1.contains("disc3"));
        assertEquals("Size of top from Stack1 is 1", checkTop(pile1));
        assertEquals("moved disc1 to Stack2", moveTop(pile1, pile2));
        assertEquals("Size of top from Stack1 is 2", checkTop(pile1));
        assertEquals("Size of top from Stack2 is 1", checkTop(pile2));
        assertEquals("moved disc2 to Stack3", moveTop(pile1, pile3));
        assertEquals("Size of top from Stack1 is 3", checkTop(pile1));
        assertEquals("Size of top from Stack3 is 2", checkTop(pile3));
    }
}
