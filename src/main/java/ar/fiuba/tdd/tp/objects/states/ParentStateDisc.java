package ar.fiuba.tdd.tp.objects.states;

import ar.fiuba.tdd.tp.objects.concrete.Pile;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

public class ParentStateDisc extends ParentState {

    @Override
    public boolean canBeChangedTo(GameObjectWithChildren parent) {
        return parent instanceof Pile || parent instanceof Player;
    }
}
