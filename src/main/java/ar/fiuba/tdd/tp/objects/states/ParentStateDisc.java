package ar.fiuba.tdd.tp.objects.states;

import ar.fiuba.tdd.tp.objects.concrete.Pile;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

/**
 * Created by Master on 19/05/2016.
 */
public class ParentStateDisc extends ParentState {

    @Override
    public boolean canBeChangedTo(GameObjectWithChildren parent) {
        if (parent instanceof Pile || parent instanceof Player) {
            return true;
        }
        return false;
    }
}
