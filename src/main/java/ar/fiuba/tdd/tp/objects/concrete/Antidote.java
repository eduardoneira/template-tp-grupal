package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.BeAskedWhat;
import ar.fiuba.tdd.tp.actions.BeLookedAt;
import ar.fiuba.tdd.tp.actions.BeMoved;
import ar.fiuba.tdd.tp.actions.TriggerActionHandler;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master on 03/05/2016.
 */
public class Antidote extends ConcreteGameObjectWithParent {

    BooleanState poisoned;

    public Antidote(String name, GameObjectWithChildren parent, BooleanState poisoned) {
        super(name, parent);

        this.poisoned = poisoned;
        addAction(new BeMoved(this, this.parent));
        addAction(new BeLookedAt(this));
        addAction(new BeAskedWhat(this));

        List<BooleanState> triggers = new ArrayList<>();
        triggers.add(poisoned);
        List<Boolean> triggeredValues = new ArrayList<>();
        triggeredValues.add(false);

        addAction(new TriggerActionHandler(this, new BeMoved(null, null), triggers, triggeredValues));
    }
}
