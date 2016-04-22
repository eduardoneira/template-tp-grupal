package ar.fiuba.tdd.tp;
/**
 * Created by Master on 21/04/2016.
 */
public interface CanHaveItemsTakenFrom extends CanHaveActionsDoneOn {
    public TakeableItem takeItem(String name);
}
