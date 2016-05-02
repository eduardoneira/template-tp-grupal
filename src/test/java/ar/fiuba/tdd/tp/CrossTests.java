package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.ChangeRoom;
import ar.fiuba.tdd.tp.objects.concrete.Cabbage;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Sheep;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.concrete.player.PlayerCrossShores;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

public class CrossTests {

    PlayerCrossShores player;
    Room firstRoom;
    Room secondRoom;

    @Before
    public void initialization() {
        firstRoom = new Room("first room");
        secondRoom = new Room("second room");
        player = new PlayerCrossShores("player", firstRoom);
        firstRoom.addChild(player);
    }

    @Test
    public void crossPlayerTest() {
        assert (firstRoom.contains("player"));
        assertFalse(secondRoom.contains("player"));
        player.handleAction("cross", new ArrayList<GameObject>() {
                {
                    add(secondRoom);
                }
        });
        assert (secondRoom.contains("player"));
        assertFalse(firstRoom.contains("player"));
        assertEquals(player.getParent().getName(), secondRoom.getName());
    }

    @Test
    public void playerCantCrossTest() {
        Sheep sheep = new Sheep("sheep", firstRoom);
        Cabbage cabbage = new Cabbage("cabbage", secondRoom);
        firstRoom.addChild(sheep);
        firstRoom.addChild(cabbage);
        assert (firstRoom.contains("sheep"));
        assert (firstRoom.contains("cabbage"));
        assertEquals("You can't cross, things will be eaten", (player.handleAction("cross", new ArrayList<GameObject>() {
            {
                add(secondRoom);
            }
        })));
    }
}
