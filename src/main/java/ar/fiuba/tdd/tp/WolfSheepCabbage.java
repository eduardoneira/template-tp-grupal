package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.Cabbage;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Sheep;
import ar.fiuba.tdd.tp.objects.concrete.Wolf;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ChildrenStateLimitedSize;

import java.util.ArrayList;
import java.util.List;

public class WolfSheepCabbage extends Game {

    Room southShore;
    Room northShore;
    Wolf wolf;
    Sheep sheep;
    Cabbage cabbage;

    BooleanState sheepNotWithCabbage;
    BooleanState wolfNotWithSheep;

    public boolean checkWinCondition() {
        // TODO: cambiar esto a una forma general para todos los juegos donde se pueden definir "REGLAS" o "CONDICIONES" que se actualizan
        // TODO: en cada turno y cambian el comportamiento de los objetos del juego
        actualizarSheepWithCabbage();
        actualizarWolfWithSheep();

        return (northShore.contains(wolf.getName()) && northShore.contains(sheep.getName()) && northShore.contains(cabbage.getName()));
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }

    // TODO: mismo que antes
    private void actualizarSheepWithCabbage() {
        if (sheep.getParent().equals(cabbage.getParent())) {
            sheepNotWithCabbage.setFalse();
        } else {
            sheepNotWithCabbage.setTrue();
        }
    }

    private void actualizarWolfWithSheep() {
        if (wolf.getParent().equals(sheep.getParent())) {
            wolfNotWithSheep.setFalse();
        } else {
            wolfNotWithSheep.setTrue();
        }
    }

    public WolfSheepCabbage() {
        super("Wolf, Sheep and Cabbage");

    }

    private void createShores() {
        String southShoreName = "south-shore";
        southShore = new Room(southShoreName);
        objects.put(southShoreName, southShore);

        String northShoreName = "north-shore";
        northShore = new Room(northShoreName);
        objects.put(northShoreName, northShore);
    }

    private void createPlayer() {
        player = new Player("player", southShore, new ChildrenStateLimitedSize(1));

        ActionHandler leaveAction = new Leave(player);
        player.addAction(leaveAction);
        commands.add(leaveAction.getName());

        ActionHandler takeAction = new Take(player);
        player.addAction(takeAction);
        commands.add(takeAction.getName());

        ActionHandler haveMovedFromAction = new HaveMovedFrom(player, player.getChildrenState());
        player.addAction(haveMovedFromAction);

        sheepNotWithCabbage = new BooleanState();
        actualizarSheepWithCabbage();
        wolfNotWithSheep = new BooleanState();
        actualizarWolfWithSheep();
        List<BooleanState> conditions = new ArrayList<>();
        conditions.add(sheepNotWithCabbage);
        conditions.add(wolfNotWithSheep);
        ActionHandler crossAction = new ConditionalActionHandlerFails(player, new Cross(player), conditions);
        player.addAction(crossAction);
        commands.add(crossAction.getName());
    }

    private void createWolf() {
        String wolfName = "wolf";
        wolf = new Wolf(wolfName, southShore);
        objects.put(wolfName, wolf);
    }

    private void createSheep() {
        String sheepName = "sheep";
        sheep = new Sheep(sheepName, southShore);
        objects.put(sheepName, sheep);
    }

    private void createCabbage() {
        String cabbageName = "cabbage";
        cabbage = new Cabbage(cabbageName, southShore);
        objects.put(cabbageName, cabbage);
    }

    @Override
    public ar.fiuba.tdd.tp.model.Game build() {

        createShores();

        createWolf();

        createSheep();

        createCabbage();

        createPlayer();
        return null;
    }
}
