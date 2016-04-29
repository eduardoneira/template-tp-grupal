package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class Leave extends Move {

    public Leave(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject whereToMove = ((ConcreteGameObjectWithParentAndChildren)this.instance).getParent();
        objectsInvolved.add(whereToMove);

        if (super.handleAction(getName(), objectsInvolved) == "invalid command") {
            return "invalid command";
        }

        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        return "left " + objectToMove.getName();
    }

    @Override
    public String getName() {
        return "leave";
    }

}
