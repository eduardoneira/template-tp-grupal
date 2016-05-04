package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

/**
 * Created by Master on 03/05/2016.
 */
public class BeOpenedAddsActionsToOpener extends BeOpened {

    protected List<ActionHandler> actionsGranted;
    protected String addToResponse;

    public BeOpenedAddsActionsToOpener(GameObject instance, List<ActionHandler> actionsGranted, String addToResponse) {
        super(instance, new BooleanState()); // medio feo, pero bueno
        this.actionsGranted = actionsGranted;
        this.addToResponse = addToResponse;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        String ret = super.handleAction(actionName, objectsInvolved);
        GameObject opener = objectsInvolved.get(idObjectWhoOpens);

        for (ActionHandler action : actionsGranted) {
            opener.addAction(action);
            action.instance = opener;
        }
        actionsGranted.clear();

        return ret + addToResponse;
    }
}
