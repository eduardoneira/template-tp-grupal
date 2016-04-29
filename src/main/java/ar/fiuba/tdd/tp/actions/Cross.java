package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.concrete.Cabbage;
import ar.fiuba.tdd.tp.objects.concrete.Sheep;
import ar.fiuba.tdd.tp.objects.concrete.Wolf;
import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.List;


public class Cross extends ChangeRoom {


    public Cross(ConcreteGameObjectWithParentAndChildren instance, ParentState parent) {
        super(instance, parent);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        boolean sheepInShore = false;
        boolean otherInShore = false;
        for (GameObject child:
                (((ConcreteGameObjectWithChildren)((ConcreteGameObjectWithParentAndChildren)instance).getParent()).getChildren())) {
            sheepInShore = (sheepInShore || child instanceof Sheep);
            otherInShore = (otherInShore || (child instanceof Cabbage || child instanceof Wolf));
        }
        return (!(sheepInShore && otherInShore) && super.canIHandleAction(objectsInvolved));
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (super.handleAction(getName(), objectsInvolved) == "invalid command") {
            return "You can't cross, things will be eaten";
        }
        return "crossed";
    }

    @Override
    public String getName() {
        return "cross";
    }
}
