package ar.fiuba.tdd.tp.timedevent;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by gabriel on 6/9/2016.
 */
public class TimedEvent {

    Game game;
    String eventDescription;
    ActionHandler action;
    List<GameObject> objectsInvolved;
    int cantRepeticiones;
    int currRepeticiones;

    public TimedEvent(Game game, ActionHandler action, List<GameObject> objectsInvolved, String eventDescription) {
        this(game, action, objectsInvolved, eventDescription, -1, -1);
    }

    public TimedEvent(Game game, ActionHandler action, List<GameObject> objectsInvolved, String eventDescription, int cantRepeticiones, int valorInicialRepeticiones) {
        this.game = game;
        this.action = action;
        this.eventDescription = eventDescription;
        this.objectsInvolved = objectsInvolved;
        this.cantRepeticiones = cantRepeticiones;
        this.currRepeticiones = valorInicialRepeticiones;
    }

    public void resetRepeticiones(int cant) {
        currRepeticiones = cant;
    }

    public boolean doEvent(StringBuilder response) {
        if (currRepeticiones == -1 || currRepeticiones < cantRepeticiones) {
            action.handleAction(action.getName(), objectsInvolved);
            game.updateGameState();
            //System.out.println("timed event: " + eventDescription);
            response.append("timed event: " + eventDescription);
            currRepeticiones++;
            return true;
        } else {
            return false;
        }
    }
}
