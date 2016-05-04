package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.concrete.Pile;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class CheckTop extends ActionHandler {

    private int idObjectToCheck = 0;
    //private int argsSize = 1;

    public CheckTop(GameObject instance) {
        super(instance, 1);
    }

    @Override
    public String getName() {
        return "checkTop";
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        int diameter = ((Pile) objectsInvolved.get(idObjectToCheck)).getSmallestDiamater();
        String diam = Integer.toString(diameter);
        return "Size of top from " + objectsInvolved.get(idObjectToCheck).getName() + " is " + diam;
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        /*if (objectsInvolved.size() != argsSize) {
            setResponseError(objectsInvolved, response);
            return false;
        }*/
        GameObject objectToCheck = objectsInvolved.get(idObjectToCheck);
        if (!(objectToCheck instanceof Pile)) {
            setResponseError(objectsInvolved, response);
            return false;
        }
        return true;
    }
}
