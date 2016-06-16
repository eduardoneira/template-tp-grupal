package ar.fiuba.tdd.tp.timedEvent;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by gabriel on 6/9/2016.
 */
public class TimedEvent {

    AtomicReference<Game> game;
    String eventDescription;
    List<ActionHandler> actions;
    List<GameObject> objectsInvolved;
    int cantRepeticiones;
    int currRepeticiones;

    public TimedEvent(Game game, List<ActionHandler> actions, List<GameObject> objectsInvolved, String eventDescription) {
        this(game, actions, objectsInvolved, eventDescription, -1, -1);
    }

    public TimedEvent(Game game, List<ActionHandler> actions, List<GameObject> objectsInvolved,
                      String eventDescription, int cantRepeticiones, int valorInicialRepeticiones) {
        this.game = new AtomicReference<>(game);
        this.actions = actions;
        this.eventDescription = eventDescription;
        this.objectsInvolved = objectsInvolved;
        this.cantRepeticiones = cantRepeticiones;
        this.currRepeticiones = valorInicialRepeticiones;
    }

    public void resetRepeticiones(int cant) {
        currRepeticiones = cant;
    }

    public boolean doEvent(StringBuilder response) {
        if (cantRepeticiones == -1 || currRepeticiones < cantRepeticiones) {
            for (ActionHandler action : actions) {
                action.handleAction(action.getName(), objectsInvolved);
                game.get().updateGameState();
            }
            //System.out.println("timed event: " + eventDescription);
            response.append(/*"timed event: " + */eventDescription);
            currRepeticiones++;
            return true;
        } else {
            return false;
        }
    }
}
