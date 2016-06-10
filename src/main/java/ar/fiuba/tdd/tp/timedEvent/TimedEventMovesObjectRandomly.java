package ar.fiuba.tdd.tp.timedevent;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.general.ConcreteObjectChangesParentRandomly;

/**
 * Created by gabriel on 6/9/2016.
 */
public class TimedEventMovesObjectRandomly extends TimedEvent {

    protected ConcreteObjectChangesParentRandomly object;

    public TimedEventMovesObjectRandomly(Game game, ConcreteObjectChangesParentRandomly object) {
        super(game);
        this.object = object;
    }

    @Override
    protected boolean doEventStuff() {
        object.changeParent();
        return true;
    }
}
