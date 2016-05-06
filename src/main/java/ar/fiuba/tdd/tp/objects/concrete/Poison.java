package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.ArrayList;
import java.util.List;

public class Poison extends ConcreteGameObjectWithParent {

    final BooleanState poisonKills;
    final BooleanState poisoned;

    public Poison(String name, GameObjectWithChildren parent, BooleanState poisonKills, BooleanState poisoned) {
        super(name, parent);

        this.poisonKills = poisonKills;
        this.poisoned = poisoned;
        addAction(new BeAskedWhat(this));
        addAction(new BeLookedAt(this));
        addAction(new BeMoved(this, this.parent));

        List<BooleanState> triggers = new ArrayList<>();
        triggers.add(this.poisonKills);
        List<BooleanState> conditions = new ArrayList<>();
        conditions.add(this.poisoned);
        List<ActionHandler> actions = new ArrayList<>();
        List<Boolean> triggeredValues = new ArrayList<>();
        triggeredValues.add(true);
        actions.add(new ConditionalActionHandlerChecks(this,
                new TriggerActionHandler(this, new BeMoved(null, null), triggers, triggeredValues), conditions));
        parent.addAction(new BeOpenedAddsActionsToOpener(this, actions, "You feel weak!"));
        parent.addAction(new TriggerActionHandler(this, new BeOpened(null, null), conditions, triggeredValues));
    }
}
