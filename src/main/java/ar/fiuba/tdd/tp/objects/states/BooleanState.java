package ar.fiuba.tdd.tp.objects.states;

/**
 * Created by Master on 26/04/2016.
 */
public class BooleanState {
    private boolean state;

    public BooleanState() {
        setTrue();
    }

    public BooleanState(boolean state) {
        this.state = state;
    }

    public void setTrue() {
        state = true;
    }

    public void setFalse() {
        state = false;
    }

    public boolean getValue() {
        return state;
    }
}
