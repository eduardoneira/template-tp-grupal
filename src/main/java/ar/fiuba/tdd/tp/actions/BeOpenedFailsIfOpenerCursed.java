package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

/**
 * Created by Master on 28/04/2016.
 */
public class BeOpenedFailsIfOpenerCursed extends BeOpened {
    public BeOpenedFailsIfOpenerCursed(GameObject instance, BooleanState open) {
        super(instance, open);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (!super.canIHandleAction(objectsInvolved)) {
            return false;
        }
        GameObject whoOpens = objectsInvolved.get(idObjectWhoOpens);
        return !whoOpens.getActionNames().contains("be cursed");
    }
}
