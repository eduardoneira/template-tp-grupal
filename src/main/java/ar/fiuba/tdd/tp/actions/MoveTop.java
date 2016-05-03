package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.concrete.Disc;
import ar.fiuba.tdd.tp.objects.concrete.Pile;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.ArrayList;
import java.util.List;

public class MoveTop extends Move {

    public MoveTop(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        List<GameObject> objectsInvolvedToMove = new ArrayList<GameObject>();
        objectsInvolvedToMove.add(((Pile) objectsInvolved.get(idObjectToMove)).getTopDisc());
        objectsInvolvedToMove.add(objectsInvolved.get(idWhereToMove));
        return super.handleAction(actionName, objectsInvolvedToMove);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        if (!(objectsInvolved.get(idObjectToMove) instanceof Disc) || !(objectsInvolved.get(idWhereToMove) instanceof Pile)) {
            setResponseError(objectsInvolved, response);
            return false;
        }
        int diameter = ((Disc) objectsInvolved.get(idObjectToMove)).getDiameter();
        int smallest = ((Pile) objectsInvolved.get(idWhereToMove)).getSmallestDiamater();
        if (diameter >= smallest) {
            response.append("You can only put a disc on top of a bigger one");
            return false;
        }
        return super.canIHandleAction(objectsInvolved, response);
    }

    @Override
    public String getName() {
        return "moveTop";
    }
}
