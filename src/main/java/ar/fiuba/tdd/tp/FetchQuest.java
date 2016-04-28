package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;

public class FetchQuest extends Game {

    Room room;
    Stick stick;

    public boolean checkWinCondition() {
        return player.contains("stick");
    }

    public FetchQuest() {
        super("Fetch Quest");

        room = new Room("room");
        player.placeInRoom(room);

        stick = new Stick("stick");
        room.addChild(stick);
        stick.setParent(room);

        Pick actionPickup = new Pick(player);
        player.addAction(actionPickup);

        keywords.add(actionPickup.getName());
        keywords.add(room.getName());
        keywords.add(stick.getName());

        objects.put(this.room.getName(), this.room);
        objects.put(stick.getName(), stick);
    }
}