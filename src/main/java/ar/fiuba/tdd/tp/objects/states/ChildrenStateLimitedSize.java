package ar.fiuba.tdd.tp.objects.states;

import ar.fiuba.tdd.tp.objects.general.GameObjectWithParent;

public class ChildrenStateLimitedSize extends ChildrenState {

    protected final int maxSize;

    public ChildrenStateLimitedSize(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    @Override
    public boolean canAddChild(GameObjectWithParent child) {
        return children.size() < maxSize;
    }
}
