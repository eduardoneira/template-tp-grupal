package ar.fiuba.tdd.tp.actions.take;

import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class Take extends Pick {

    public Take(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (super.handleAction(getName(), objectsInvolved) == "invalid command") {
            return "The boat is full!";
        }
        GameObject objectToMove = objectsInvolved.get(OBJECT_TO_MOVE);
        return "taken " + objectToMove.getName();
    }

    @Override
    public String getName() {
        return "take";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        return (super.canIHandleAction(objectsInvolved) && instance.isEmpty());
    }

}
