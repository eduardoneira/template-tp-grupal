package ar.fiuba.tdd.tp.objects.strategies.move;

import ar.fiuba.tdd.tp.abilities.CanBeMoved;
import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedFrom;
import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveChildren;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveThingsMovedTo;
import ar.fiuba.tdd.tp.objects.states.ParentState;

public class CanBeMovedStrategy implements CanBeMoved, ParentControlFunctions {
    ParentState parent;
    GameObjectCanBeMoved instance;

    public CanBeMovedStrategy(GameObjectCanBeMoved instance) {
        this.instance = instance;
        this.parent = new ParentState();
    }

    @Override
    public String moveTo(GameObjectCanHaveThingsMovedTo whereTo) {
        // el requisito para 'parent' es que pueda tener cosas, pero no necesariamente que me deje salir despues
        if (this.parent.getParent() != null && !(this.parent.getParent() instanceof CanHaveThingsMovedFrom)) {
            return "invalid command";
        }
        if (this.parent.getParent() != null) {
            ((CanHaveThingsMovedFrom)this.parent.getParent()).haveMovedFrom(instance);
        }
        this.parent.setParent(whereTo);
        return whereTo.haveMovedTo(instance);
    }

    @Override
    public GameObjectCanHaveChildren getParent() {
        return parent.getParent();
    }

    @Override
    public void setParent(GameObjectCanHaveChildren parent) {
        this.parent.setParent(parent);
    }
}
