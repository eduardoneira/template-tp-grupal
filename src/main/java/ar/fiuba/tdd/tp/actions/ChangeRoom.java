package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class ChangeRoom extends Move {

    private int idWhereTo = 0;

    public ChangeRoom(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance, 1);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        GameObject whereToCross = objectsInvolved.get(idWhereTo);

        /*StringBuilder response = new StringBuilder();
        if (!canHandleAction(actionName, objectsInvolved, response)) {
            return response.toString();
        }*/

        super.handleAction(getName(), objectsInvolved);
        return "changed room to " + whereToCross.getName();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        GameObject whereToCross = objectsInvolved.get(idWhereTo);
        objectsInvolved.clear();
        objectsInvolved.add(this.instance);
        objectsInvolved.add(whereToCross);
        return super.canIHandleAction(objectsInvolved, response);
    }

    @Override
    public String getName() {
        return "change room";
    }
}
