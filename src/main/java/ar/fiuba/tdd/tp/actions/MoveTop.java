package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.concrete.Disc;
import ar.fiuba.tdd.tp.objects.concrete.Pile;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class MoveTop extends Move {

    public MoveTop(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
    }

    /*@Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        List<GameObject> objectsInvolvedToMove = new ArrayList<GameObject>();
        objectsInvolvedToMove.add(((Pile) objectsInvolved.get(idObjectToMove)).getTopDisc());
        objectsInvolvedToMove.add(objectsInvolved.get(idWhereToMove));
        return super.handleAction(actionName, objectsInvolvedToMove);
    }*/

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {

        if (!(objectsInvolved.get(idObjectToMove) instanceof Pile) || !(objectsInvolved.get(idWhereToMove) instanceof Pile)) {
            setResponseError(objectsInvolved, response);
            return false;
        }

        GameObject topDisc = ((Pile) objectsInvolved.get(idObjectToMove)).getTopDisc();
        if (topDisc == null) {
            response.append(objectsInvolved.get(idObjectToMove).getName());
            response.append(" has no discs");
            return false;
        }

        GameObject pileToMoveTo = objectsInvolved.get(idWhereToMove);
        objectsInvolved.clear();
        objectsInvolved.add(topDisc);
        objectsInvolved.add(pileToMoveTo);

        return checkDiameters(objectsInvolved, response);
    }

    private boolean checkDiameters(List<GameObject> objectsInvolved, StringBuilder response) {
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
