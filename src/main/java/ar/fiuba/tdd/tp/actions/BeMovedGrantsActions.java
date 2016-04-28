package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Master on 28/04/2016.
 */
public class BeMovedGrantsActions extends BeMoved {

    protected List<ActionHandler> actionsGranted;

    public BeMovedGrantsActions(GameObject instance, ParentState parent, List<ActionHandler> actionsGranted) {
        super(instance, parent);
        this.actionsGranted = actionsGranted;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        GameObject oldParent = parent.getParent();
        if(oldParent != null) {
            for( ActionHandler action : actionsGranted) {
                oldParent.removeAction(action.getName());
                action.instance = this.instance;
            }
        }

        String ret = super.handleAction(actionName, objectsInvolved);

        GameObject newParent = parent.getParent();
        for( ActionHandler action : actionsGranted) {
            newParent.addAction(action);
            action.instance = newParent;
        }

        return ret;
    }
}
