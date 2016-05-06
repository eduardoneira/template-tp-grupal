package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.actions.ConditionalActionHandlerFails;
import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConditionalActionHandlerTests {

    Room room;
    Player player;
    Stick stick;
    BooleanState cond1;
    BooleanState cond2;
    List<GameObject> objectsInvolved;

    @Before
    public void initialization() {
        room = new Room("room");

        stick = new Stick("stick", room);

        player = new Player("player", room);

        List<BooleanState> conds = new ArrayList<>();
        cond1 = new BooleanState();
        conds.add(cond1);
        cond2 = new BooleanState();
        conds.add(cond2);

        ActionHandler conditionalPick = new ConditionalActionHandlerFails(player, new Pick(player), conds);
        player.addAction(conditionalPick);

        objectsInvolved = new LinkedList<>();
        objectsInvolved.add(stick);
    }

    @Test
    public void cond1FailsCond2Fails() {
        cond1.setFalse();
        cond2.setFalse();
        System.out.println(player.handleAction("pick", objectsInvolved));
        assert (room.contains(stick.getName()));
    }

    @Test
    public void cond1FailsCond2Passes() {
        cond1.setFalse();
        cond2.setTrue();
        System.out.println(player.handleAction("pick", objectsInvolved));
        assert (room.contains(stick.getName()));
    }

    @Test
    public void cond1PassesCond2Fails() {
        cond1.setTrue();
        cond2.setFalse();
        System.out.println(player.handleAction("pick", objectsInvolved));
        assert (room.contains(stick.getName()));
    }

    @Test
    public void cond1PassesCond2Passes() {
        cond1.setTrue();
        cond2.setTrue();
        System.out.println(player.handleAction("pick", objectsInvolved));
        assert (!room.contains(stick.getName()));
    }
}
