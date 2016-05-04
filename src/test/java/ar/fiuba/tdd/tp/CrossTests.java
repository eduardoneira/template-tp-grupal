package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.concrete.Cabbage;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Sheep;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ChildrenStateLimitedSize;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

public class CrossTests {

    Player player;
    Room firstRoom;
    Room secondRoom;
    Sheep sheep;
    Cabbage cabbage;
    BooleanState sheepSeparateFromCabbage;

    @Before
    public void initialization() {
        firstRoom = new Room("first room");
        secondRoom = new Room("second room");

        sheep = new Sheep("sheep", firstRoom);
        cabbage = new Cabbage("cabbage", secondRoom);
        firstRoom.addChild(sheep);
        firstRoom.addChild(cabbage);

        sheepSeparateFromCabbage = new BooleanState();
        sheepSeparateFromCabbage.setFalse();

        List<BooleanState> conditions = new ArrayList<>();
        conditions.add(sheepSeparateFromCabbage);

        player = new Player("player", firstRoom, new ChildrenStateLimitedSize(1));
        player.addAction(new ConditionalActionHandlerFails(player, new Cross(player), conditions));
        player.addAction(new Leave(player));
        player.addAction(new Take(player));
        player.addAction(new HaveMovedFrom(player, player.getChildrenState()));
        firstRoom.addChild(player);
    }

    @Test
    public void playerCantCrossWhenSheepWithCabbageTest() {
        assert (firstRoom.contains("sheep"));
        assert (firstRoom.contains("cabbage"));
        assert (firstRoom.contains("player"));
        player.handleAction("cross", new ArrayList<GameObject>() {
            {
                add(secondRoom);
            }
        });
        assert (firstRoom.contains("player"));
    }

    @Test
    public void playerCanCrossWhenSheepSeparateFromCabbageTest() {
        assert (firstRoom.contains("sheep"));
        assert (firstRoom.contains("cabbage"));
        assert (firstRoom.contains("player"));
        System.out.println(player.handleAction("take", new ArrayList<GameObject>() {
            {
                add(sheep);
            }
        }));
        assert (player.contains("sheep"));
        sheepSeparateFromCabbage.setTrue();
        System.out.println(player.handleAction("cross", new ArrayList<GameObject>() {
            {
                add(secondRoom);
            }
        }));
        assert (secondRoom.contains("player"));
    }
}
