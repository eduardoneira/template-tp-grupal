package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.List;


public class BeMovedAddsActionToMover extends BeMoved {

    private ActionHandler actionToAdd;

    public BeMovedAddsActionToMover(ConcreteGameObjectWithParent instance, ParentState parent, ActionHandler actionToAdd) {
        super(instance, parent);
        this.actionToAdd = actionToAdd;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        this.parent.getParent().removeAction(actionToAdd.getName());
        super.handleAction(actionName, objectsInvolved);
        this.parent.getParent().addAction(actionToAdd);
        actionToAdd.instance = this.parent.getParent();
        return "done";
    }
}
