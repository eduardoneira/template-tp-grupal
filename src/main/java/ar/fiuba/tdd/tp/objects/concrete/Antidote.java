package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.ArrayList;
import java.util.List;

public class Antidote extends ConcreteGameObjectWithParent {

    BooleanState poisoned;

    public Antidote(String name, GameObjectWithChildren parent, BooleanState poisoned) {
        super(name, parent);

        this.poisoned = poisoned;
        addAction(new BeMoved(this, this.parent));
        addAction(new BeLookedAt(this));
        addAction(new BeAskedWhat(this));

        List<BooleanState> triggers = new ArrayList<>();
        triggers.add(this.poisoned);
        List<Boolean> triggeredValues = new ArrayList<>();
        triggeredValues.add(false);

        BeUsed usedAction = new BeUsed(this, this.parent, 1);
        addAction(new TriggerActionHandler(this, usedAction, triggers, triggeredValues, "All your ailments are healed!"));
        addAction(usedAction);
    }
}
