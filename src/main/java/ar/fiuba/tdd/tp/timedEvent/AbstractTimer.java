package ar.fiuba.tdd.tp.timedevent;

/**
 * Created by Master on 11/06/2016.
 */
public interface AbstractTimer {

    long currentTimeMillis();
    int currentTimeSeconds();

    long getSleepTime(int needed);
}
