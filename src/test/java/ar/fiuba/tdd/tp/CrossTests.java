package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.ChangeRoom;
import ar.fiuba.tdd.tp.objects.concrete.Room;
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
        player = new PlayerCrossShores("player");
        firstRoom = new Room("first room");
        secondRoom = new Room("second room");
        player.placeInRoom(firstRoom);
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
}
