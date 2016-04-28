package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class BeLookedAtAndChildrenChangeVisibility extends BeLookedAt {

    protected BooleanState childrenVisible;
    protected BeLookedAtAndChildren lookWhenChildrenVisible;

    public BeLookedAtAndChildrenChangeVisibility(GameObject instance, ChildrenState children, BooleanState visible) {
        super(instance);
        this.childrenVisible = visible;
        lookWhenChildrenVisible = new BeLookedAtAndChildren(instance, children);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        if(!childrenVisible.getValue()) {
            return super.handleAction(actionName, objectsInvolved);
        } else {
            return lookWhenChildrenVisible.handleAction(actionName, objectsInvolved);
        }
    }
}
