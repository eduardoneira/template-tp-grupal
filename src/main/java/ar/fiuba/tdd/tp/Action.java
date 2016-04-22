package ar.fiuba.tdd.tp;
/**
 * Created by Master on 21/04/2016.
 */
public interface Action {

    String doAction(CanDoActions doer, CanHaveActionsDoneOn doee, Object[] args);

    String getName();
}
