package ar.fiuba.tdd.tp.timedEvent;

public interface AbstractTimer {

    long currentTimeMillis();

    int currentTimeSeconds();

    long getSleepTime(int needed);
}
