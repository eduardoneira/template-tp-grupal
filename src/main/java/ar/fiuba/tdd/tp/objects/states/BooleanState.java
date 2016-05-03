package ar.fiuba.tdd.tp.objects.states;

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

    public boolean isTrue() {
        return (state == true);
    }

    public boolean isFalse() {
        return (state == false);
    }
}
