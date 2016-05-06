package ar.fiuba.tdd.tp.objects.states;

public class ChildrenStateLimitedSize extends ChildrenState {

    protected int maxSize;

    public ChildrenStateLimitedSize(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    @Override
    public boolean canAddChild() {
        return children.size() < maxSize;
    }
}
