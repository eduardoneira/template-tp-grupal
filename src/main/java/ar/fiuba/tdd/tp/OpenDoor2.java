package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Open;
import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.model.AbstractCondition;
import ar.fiuba.tdd.tp.model.ConditionCheckBoolean;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.Box;
import ar.fiuba.tdd.tp.objects.concrete.Key;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.LockedDoor;

import java.util.List;
import java.util.Set;

public class OpenDoor2 extends Game {

    private Room room;
    private Box box;
    private Key key;
    private LockedDoor door;
    private String nameRoom = "room";
    private String nameKey = "key";
    private String nameDoor = "door";
    private String nameBox = "box";

    @SuppressWarnings("CPD-START")

    public OpenDoor2() {

        super("OpenDoor2");
    }

    private void createRoom() {
        room = new Room(nameRoom);
        objects.put(room.getName(), room);
    }

    private void createDoor() {
        door = new LockedDoor(nameDoor, room, 1);
        door.setLocked();
        door.setClosed();
        objects.put(door.getName(), door);
    }

    private void createBox() {
        box = new Box(nameBox, room);
        box.setClosed();
        objects.put(box.getName(), box);
    }

    private void createKey() {
        key = new Key(nameKey, box, 1);
        objects.put(key.getName(), key);
    }

    @Override
    protected void configPlayer(String playerId) {
        Player player = players.get(playerId);
        Set<String> commands = commandsPerPlayer.get(playerId);
        List<AbstractCondition> winConds = winConditionsPerPlayer.get(playerId);
        List<AbstractCondition> looseConds = looseConditionsPerPlayer.get(playerId);

        player.setParent(room);
        room.addChild(player);

        Open actionOpen = new Open(player);
        player.addAction(actionOpen);
        commands.add(actionOpen.getName());

        Pick actionPickup = new Pick(player);
        player.addAction(actionPickup);
        commands.add(actionPickup.getName());

        winConds.add(new ConditionCheckBoolean(door.getOpenState(), true));
    }

    @Override
    protected void updateGameAfterHandle() {

    }

    @SuppressWarnings("CPD-END")

    /*@Override
    public boolean checkWinCondition() {
        return door.isOpen();
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }*/

    @Override
    public ar.fiuba.tdd.tp.model.Game build() {

        createRoom();

        createDoor();

        createBox();

        createKey();

        //createPlayer();
        return this;
    }
}
