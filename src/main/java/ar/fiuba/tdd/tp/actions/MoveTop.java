package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.concrete.Pile;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class MoveTop extends Move {

    public MoveTop(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
    }

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

        return super.canIHandleAction(objectsInvolved, response);
    }

    @Override
    public String getName() {
        return "moveTop";
    }
}
