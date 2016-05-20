package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class BeLookedAtAndChildren extends BeLookedAt {

    protected final ChildrenState children;

    public BeLookedAtAndChildren(GameObject instance, ChildrenState children) {
        super(instance);
        this.children = children;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        if (children.getChildren().size() == 0) {
            return super.handleAction(actionName, objectsInvolved);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(super.handleAction(actionName, objectsInvolved));
        //builder.append(" ");
        for (GameObject child : children.getChildren()) {
            if (child.canHandleAction(actionName, objectsInvolved, new StringBuilder())) {
                builder.append(" ");
                builder.append(child.handleAction(actionName, objectsInvolved));
            }
        }
        return builder.toString();
    }
}
