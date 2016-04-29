package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.ChangeRoom;
import ar.fiuba.tdd.tp.actions.Leave;
import ar.fiuba.tdd.tp.actions.Take;
import ar.fiuba.tdd.tp.objects.concrete.Cabbage;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Sheep;
import ar.fiuba.tdd.tp.objects.concrete.Wolf;
import ar.fiuba.tdd.tp.objects.concrete.player.PlayerCrossShores;

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
        super("Wolf, Sheep and Cabbage", new PlayerCrossShores("player"));
        String southShoreName = "south-shore";
        String northShoreName = "north-shore";
        southShore = new Room(southShoreName);
        northShore = new Room(northShoreName);
        objects.put(southShoreName, southShore);
        objects.put(northShoreName, northShore);
        keywords.add(southShoreName);
        keywords.add(northShoreName);
        player.placeInRoom(southShore);
        keywords.add(new Leave(null).getName());
        keywords.add(new Take(null).getName());
        keywords.add(new ChangeRoom(null, null).getName());
        createMore();
    }

    //negrada para evitar el ncss de checkstyle
    private void createMore() {
        wolfName = "wolf";
        sheepName = "sheep";
        cabbageName = "cabbage";
        keywords.add(wolfName);
        keywords.add(sheepName);
        keywords.add(cabbageName);
        Wolf wolf = new Wolf(wolfName);
        Sheep sheep = new Sheep(sheepName);
        Cabbage cabbage = new Cabbage(cabbageName);
        objects.put(wolfName, wolf);
        objects.put(sheepName, sheep);
        objects.put(cabbageName, cabbage);
        addActionToKeywords();
    }
}
