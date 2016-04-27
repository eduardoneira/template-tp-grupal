package ar.fiuba.tdd.tp.newactions.lookat;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public class BeLookedAtAndChildren extends BeLookedAt{

    protected ChildrenState children;

    public BeLookedAtAndChildren(GameObject instance, ChildrenState children) {
        super(instance);
        this.children = children;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        if(children.getChildren().size() == 0) {
            return super.handleAction(actionName, objectsInvolved);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(super.handleAction(actionName, objectsInvolved));
        builder.append(" ");
        for(GameObject child : children.getChildren()) {
            builder.append(child.handleAction(actionName, objectsInvolved));
            builder.append(" ");
        }
        return builder.toString();
    }
}
