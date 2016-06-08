package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.model.AbstractCondition;
import ar.fiuba.tdd.tp.model.ConditionCheckContains;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;

import java.util.List;
import java.util.Set;

public class FetchQuest extends Game {

    Room room;
    Stick stick;



    @SuppressWarnings("CPD-START")

    public FetchQuest() {
        super("FetchQuest");
    }

    /*public boolean checkWinCondition() {
        //return player.contains("stick");
        return cond.checkCondition();
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }*/

    @Override
    public ar.fiuba.tdd.tp.model.Game build() {

        room = new Room("room");
        objects.put(room.getName(), this.room);

        stick = new Stick("stick", room);
        stick.setParent(room);
        objects.put(stick.getName(), stick);

        return this;
    }

    @Override
    protected Player configPlayer(String playerId, String type) {
        Player player = new Player("player" + Integer.toString(players.size()+1), null);
        Set<String> commands = commandsPerPlayer.get(playerId);
        List<AbstractCondition> winConds = winConditionsPerPlayer.get(playerId);
        List<AbstractCondition> looseConds = looseConditionsPerPlayer.get(playerId);

        player.setParent(room);
        room.addChild(player);

        Pick actionPickup = new Pick(player);
        player.addAction(actionPickup);
        commands.add(actionPickup.getName());

        ConditionCheckContains cond = new ConditionCheckContains(player.getChildrenState(), stick.getName(), true);
        winConds.add(cond);

        return player;
    }

    @Override
    protected void updateGameAfterHandle(String playerId) {

    }
}