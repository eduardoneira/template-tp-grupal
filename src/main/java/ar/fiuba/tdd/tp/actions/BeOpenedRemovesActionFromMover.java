package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

public class BeOpenedRemovesActionFromMover extends BeOpened {

    private String actionToRemove;

    public BeOpenedRemovesActionFromMover(GameObject instance, BooleanState open, String actionToRemove) {
        super(instance, open);
        this.actionToRemove = actionToRemove;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        super.handleAction(actionName, objectsInvolved);

        GameObject opener = objectsInvolved.get(idObjectWhoOpens);
        // TODO: hacer que el jugador no pueda irse del cuarto
        return "done";
    }
}
