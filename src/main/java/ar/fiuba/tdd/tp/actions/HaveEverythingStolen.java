package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class HaveEverythingStolen extends HaveMovedFrom {

    private int objectWhoSteals = 0;
    private int argsSize = 1;

    public HaveEverythingStolen(GameObject instance, ChildrenState children) {
        super(instance, children);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject thief = objectsInvolved.get(objectWhoSteals);

        for (GameObject child : children.getChildren() ) {
            children.removeChild(child);
        }

        return "done";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {

        return objectsInvolved.size() == argsSize;
    }

    @Override
    public String getName() {
        return "have everything stolen";
    }
}
