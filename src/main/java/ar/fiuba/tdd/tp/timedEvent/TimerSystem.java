package ar.fiuba.tdd.tp.timedevent;

/**
 * Created by Master on 11/06/2016.
 */
public class TimerSystem implements AbstractTimer {

    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override
    public int currentTimeSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    @Override
    public long getSleepTime(int needed) {
        return needed * 1000;
    }
}
