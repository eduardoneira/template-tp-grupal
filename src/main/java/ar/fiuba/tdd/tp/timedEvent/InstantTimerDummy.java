package ar.fiuba.tdd.tp.timedevent;

/**
 * Created by Master on 11/06/2016.
 */
public class InstantTimerDummy implements AbstractTimer {

    int time;

    public InstantTimerDummy() {
        time = 0;
    }

    @Override
    public long currentTimeMillis() {
        int temp = time;
        time += 30000;
        return temp;
    }

    @Override
    public int currentTimeSeconds() {
        int temp = time;
        time += 30;
        return temp;
    }

    @Override
    public long getSleepTime(int needed) {
        return 0;
    }
}
