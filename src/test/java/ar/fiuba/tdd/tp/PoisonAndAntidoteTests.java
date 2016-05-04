package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Leave;
import ar.fiuba.tdd.tp.actions.Open;
import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.actions.Use;
import ar.fiuba.tdd.tp.objects.concrete.*;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingDoor;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Master on 03/05/2016.
 */
public class PoisonAndAntidoteTests {

    private Room room1;
    private LinkingDoor doorTo2;
    private Room room2;
    private Player player;
    private Poison poison;
    private Poison poison2;
    private Antidote antidote;
    private Antidote antidote2;
    private Box box;
    private Box box2;
    private BooleanState killedByPoison;
    private BooleanState poisoned;

    private List<GameObject> objectsInvolved;

    @Before
    public void initialization() {
        room1 = new Room("room1");
        room2 = new Room("room2");

        player = new Player("player", room1);
        player.addAction(new Pick(player));
        player.addAction(new Open(player));
        player.addAction(new Use(player));
        room1.addChild(player);

        box = new Box("box", room1);
        room1.addChild(box);

        box2 = new Box("box2", room1);
        room1.addChild(box2);

        initialization2();
    }

    public void initialization2() {
        killedByPoison = new BooleanState();
        killedByPoison.setFalse();
        poisoned = new BooleanState();
        poisoned.setFalse();

        poison = new Poison("poison", box, killedByPoison, poisoned);
        box.addChild(poison);
        poison2 = new Poison("poison2", box2, killedByPoison, poisoned);
        box2.addChild(poison2);

        antidote = new Antidote("antidote", room1, poisoned);
        room1.addChild(antidote);

        antidote2 = new Antidote("antidote2", room1, poisoned);
        room1.addChild(antidote2);

        doorTo2 = new LinkingDoor("door", room1, room2);
        room1.addChild(doorTo2);

        objectsInvolved = new ArrayList<>();
    }

    @Test
    public void playerDiesIfPoisonedAndLeavesRoom() {

        assert (killedByPoison.isFalse());
        assert (poisoned.isFalse());
        // abro la caja
        objectsInvolved.add(box);
        player.handleAction("open", objectsInvolved);
        objectsInvolved.clear();
        // esta envenenado
        assert (poisoned.isTrue());
        // deberia morir al salir porque estoy envenenado
        objectsInvolved.add(doorTo2);
        System.out.println(player.handleAction("open", objectsInvolved));
        objectsInvolved.clear();
        assert (room2.contains(player.getName()));
        assert (killedByPoison.isTrue());
    }

    @Test
    public void antidoteCuresPoison() {
        assert (killedByPoison.isFalse());
        assert (poisoned.isFalse());
        // abro la caja
        objectsInvolved.add(box);
        player.handleAction("open", objectsInvolved);
        objectsInvolved.clear();
        // esta envenenado
        assert (poisoned.isTrue());
        // agarro antidoto
        objectsInvolved.add(antidote);
        player.handleAction("pick", objectsInvolved);
        objectsInvolved.clear();
        // uso antidoto
        objectsInvolved.add(antidote);
        player.handleAction("use", objectsInvolved);
        objectsInvolved.clear();
        assert (poisoned.isFalse());
        // me puedo ir sin morir
        objectsInvolved.add(doorTo2);
        System.out.println(player.handleAction("open", objectsInvolved));
        objectsInvolved.clear();
        assert (room2.contains(player.getName()));
        assert (killedByPoison.isFalse());
    }

    @Test
    public void secondPoisonStillKillsIfGottenAfterAntidote() {
        assert (killedByPoison.isFalse());
        assert (poisoned.isFalse());
        // abro la caja
        objectsInvolved.add(box);
        player.handleAction("open", objectsInvolved);
        objectsInvolved.clear();
        // esta envenenado
        assert (poisoned.isTrue());
        // agarro antidoto
        objectsInvolved.add(antidote);
        player.handleAction("pick", objectsInvolved);
        objectsInvolved.clear();
        // uso antidoto
        objectsInvolved.add(antidote);
        player.handleAction("use", objectsInvolved);
        objectsInvolved.clear();
        assert (poisoned.isFalse());
        // abro la caja2
        objectsInvolved.add(box2);
        player.handleAction("open", objectsInvolved);
        objectsInvolved.clear();
        assert (poisoned.isTrue());
        // deberia morir al salir porque estoy envenenado
        objectsInvolved.add(doorTo2);
        System.out.println(player.handleAction("open", objectsInvolved));
        objectsInvolved.clear();
        assert (room2.contains(player.getName()));
        assert (killedByPoison.isTrue());
    }

    @Test
    public void secondAntidoteStillCures() {
        assert (killedByPoison.isFalse());
        assert (poisoned.isFalse());
        // abro la caja
        objectsInvolved.add(box);
        player.handleAction("open", objectsInvolved);
        objectsInvolved.clear();
        // esta envenenado
        assert (poisoned.isTrue());
        // agarro antidoto
        objectsInvolved.add(antidote);
        player.handleAction("pick", objectsInvolved);
        objectsInvolved.clear();
        // uso antidoto
        objectsInvolved.add(antidote);
        player.handleAction("use", objectsInvolved);
        objectsInvolved.clear();
        assert (poisoned.isFalse());
        // abro la caja2
        objectsInvolved.add(box2);
        player.handleAction("open", objectsInvolved);
        objectsInvolved.clear();
        assert (poisoned.isTrue());
        // agarro antidoto
        objectsInvolved.add(antidote2);
        player.handleAction("pick", objectsInvolved);
        objectsInvolved.clear();
        // uso antidoto
        objectsInvolved.add(antidote2);
        player.handleAction("use", objectsInvolved);
        objectsInvolved.clear();
        assert (poisoned.isFalse());
        // me puedo ir sin morir
        objectsInvolved.add(doorTo2);
        System.out.println(player.handleAction("open", objectsInvolved));
        objectsInvolved.clear();
        assert (room2.contains(player.getName()));
        assert (killedByPoison.isFalse());
    }
}
