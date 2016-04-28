package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class Pick extends Move {

    public Pick(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject whereToMove = this.instance;
        objectsInvolved.add(whereToMove);

        if (super.handleAction(getName(), objectsInvolved) == "invalid command") {
            return "invalid command";
        }

        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        return "picked " + objectToMove.getName();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        return super.canIHandleAction(objectsInvolved);
    }

    @Override
    public String getName() {
        return "pick";
    }
}
