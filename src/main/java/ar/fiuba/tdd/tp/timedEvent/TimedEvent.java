package ar.fiuba.tdd.tp.timedevent;

import ar.fiuba.tdd.tp.model.Game;

/**
 * Created by gabriel on 6/9/2016.
 */
public abstract class TimedEvent {

    Game game;

    public TimedEvent(Game game) {
        this.game = game;
    }

    public boolean doEvent() {
        boolean temp = doEventStuff();
        game.updateGameState();
        return temp;
    }

    protected abstract boolean doEventStuff();
}
