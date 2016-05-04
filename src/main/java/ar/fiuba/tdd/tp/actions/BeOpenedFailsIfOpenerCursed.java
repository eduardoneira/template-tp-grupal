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
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        /*if (!super.canIHandleAction(objectsInvolved, response)) {
            return false;
        }*/
        GameObject whoOpens = objectsInvolved.get(idObjectWhoOpens);
        if (whoOpens.getActionNames().contains("be cursed")) {
            response.append(this.instance.getName());
            response.append(" can't be opened while holding the cursed item");
            return false;
        }
        return true;
    }
}
