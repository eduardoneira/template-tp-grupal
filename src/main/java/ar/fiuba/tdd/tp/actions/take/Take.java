package ar.fiuba.tdd.tp.actions.take;

import ar.fiuba.tdd.tp.actions.pick.Pick;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class Take extends Pick {

    public Take(ConcreteGameObjectWithChildren instance) {
        super(instance);
    }

    @Override
    public String getName() {
        return "take";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        return (super.canIHandleAction(objectsInvolved) && instance.isEmpty());
    }

}
