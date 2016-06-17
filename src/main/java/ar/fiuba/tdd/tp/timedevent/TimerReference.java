package ar.fiuba.tdd.tp.timedevent;

public class TimerReference implements AbstractTimer {

    private AbstractTimer instance;

    public TimerReference(AbstractTimer instance) {
        setTimer(instance);
    }

    @Override
    public long currentTimeMillis() {
        return instance.currentTimeMillis();
    }

    @Override
    public int currentTimeSeconds() {
        return instance.currentTimeSeconds();
    }

    @Override
    public long getSleepTime(int needed) {
        return instance.getSleepTime(needed);
    }

    public void setTimer(AbstractTimer instance) {
        this.instance = instance;
    }
}
