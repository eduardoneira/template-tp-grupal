package ar.fiuba.tdd.tp;
/**
 * Created by Master on 21/04/2016.
 */
public class LookAround implements Action {

    @Override
    public String doAction(CanDoActions doer, CanHaveActionsDoneOn doee, Object[] args) {
        if (!(doee instanceof CanBeLookedAt)) {
            return "invalid argument";
        }
        return ((CanBeLookedAt) doee).lookAt();
    }

    @Override
    public String getName() {
        return "look";
    }
}
