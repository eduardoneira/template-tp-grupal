package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.LinkedList;
import java.util.List;

public class HaveEverythingStolen extends HaveMovedFrom {

    private static final int objectWhoSteals = 0;
    //private int argsSize = 1;

    public HaveEverythingStolen(GameObject instance, ChildrenState children) {
        super(instance, children);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject thief = objectsInvolved.get(objectWhoSteals);
        //List<GameObject> whereTo = new LinkedList<>();
        //whereTo.add(thief);

        for (GameObject child : children.getChildren() ) {
            //child.handleAction("be moved", whereTo);
            //children.removeChild(child);
            List<GameObject> listForMover = new LinkedList<>();
            listForMover.add(child);
            listForMover.add(thief);
            thief.handleAction("move", listForMover);
        }

        return "You lost all your items!";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {

        /*if (!(objectsInvolved.size() == argsSize)) {
            setResponseError(objectsInvolved, response);
            return false;
        }*/
        return true;
    }

    @Override
    public String getName() {
        return "have everything stolen";
    }
}
