package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.actions.ActionHandler;

import javax.swing.*;

/**
 * Created by fernando on 08/06/16.
 */
public class ActionWithTime {
    //tiempo en minutos
    private Action action;
    private int time;
    public ActionWithTime(Action action, int time ) {
            this.action = action;
            this.time = time;
    }
    public int getTime() { return time; }
    public Action getAction() { return action; }
}
