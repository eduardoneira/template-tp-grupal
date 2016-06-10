package ar.fiuba.tdd.tp.timedEvent;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

/**
 * Created by gabriel on 6/9/2016.
 */
public class TimedEventSetBoolean extends TimedEvent {

    BooleanState estado;
    boolean triggeredValue;

    public TimedEventSetBoolean(Game game, BooleanState estado, boolean triggeredValue) {
        super(game);
        this.estado = estado;
        this.triggeredValue = triggeredValue;
    }

    @Override
    public boolean _doEvent() {
        if (estado.getValue() != triggeredValue) {
            //System.out.println("Cambio un booleano");
            estado.setValue(triggeredValue);
            return true;
        }
        return false;
    }
}
