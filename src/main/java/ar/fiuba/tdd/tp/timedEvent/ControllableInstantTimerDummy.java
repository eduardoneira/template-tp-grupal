package ar.fiuba.tdd.tp.timedevent;

/**
 * Created by Master on 14/06/2016.
 */
public class ControllableInstantTimerDummy extends InstantTimerDummy {

    boolean passTimeVar;

    public ControllableInstantTimerDummy() {
        passTimeVar = false;
    }

    @Override
    public long currentTimeMillis() {
        if (passTimeVar) {
            //passTime(false);
            return super.currentTimeMillis();
        } else {
            return time;
        }
    }

    @Override
    public int currentTimeSeconds() {
        if (passTimeVar) {
            //passTime(false);
            return super.currentTimeSeconds();
        } else {
            return time;
        }
    }

    public synchronized void passTime() {
        passTimeVar = true;
    }
}
