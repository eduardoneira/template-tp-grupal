package ar.fiuba.tdd.tp.command;

/**
 * Created by eduu on 27/04/16.
 */
public class BypassCommandProxy extends AbstractCommandProxy {


    public BypassCommandProxy( AbstractCommand command) {
        super(null,command);
    }

    @Override
    public boolean handle(String name) {
        Object[] parameters = {name};
        command.handle(parameters);
        return true;
    }
}
