package ar.fiuba.tdd.tp.timedevent;

public interface AbstractTimer {

    long currentTimeMillis();

    int currentTimeSeconds();

    long getSleepTime(int needed);
}
