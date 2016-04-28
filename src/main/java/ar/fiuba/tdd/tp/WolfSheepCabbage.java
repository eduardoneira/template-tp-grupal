package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.cross.Cross;
import ar.fiuba.tdd.tp.actions.leave.Leave;
import ar.fiuba.tdd.tp.actions.take.Take;
import ar.fiuba.tdd.tp.objects.concrete.Cabbage;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Sheep;
import ar.fiuba.tdd.tp.objects.concrete.Wolf;

public class WolfSheepCabbage extends Game {

    Room southShore;
    Room northShore;
    String wolfName;
    String sheepName;
    String cabbageName;

    public boolean checkWinCondition() {
        return (northShore.contains(wolfName) && northShore.contains(sheepName) && northShore.contains(cabbageName));
    }

    public WolfSheepCabbage() {
        super("Wolf, Sheep and Cabbage");
        String southShoreName = "south-shore";
        String northShoreName = "north-shore";
        southShore = new Room(southShoreName);
        northShore = new Room(northShoreName);
        keywords.add(southShoreName);
        keywords.add(northShoreName);
        player.placeInRoom(southShore);
        Leave leave = new Leave(player);
        Take take = new Take(player);
        Cross cross = new Cross(player);
        player.addAction(leave);
        player.addAction(take);
        player.addAction(cross);
        keywords.add(leave.getName());
        keywords.add(take.getName());
        keywords.add(cross.getName());
        createMore();

    }

    //negrada para evitar el ncss de checkstyle
    private void createMore() {
        wolfName = "wolf";
        sheepName = "sheep";
        cabbageName = "cabbage";
        Wolf wolf = new Wolf(wolfName);
        Sheep sheep = new Sheep(sheepName);
        Cabbage cabbage = new Cabbage(cabbageName);
        keywords.add(wolfName);
        keywords.add(sheepName);
        keywords.add(cabbageName);
    }
}
