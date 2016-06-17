package ar.fiuba.tdd.tp.timedevent;

public class ActionWithTime {
    //tiempo en minutos
    private TimedEvent action;
    private int time;

    public ActionWithTime(TimedEvent action, int time) {
        this.action = action;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public TimedEvent getAction() {
        return action;
    }
}
