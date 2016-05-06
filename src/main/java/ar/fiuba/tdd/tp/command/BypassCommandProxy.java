package ar.fiuba.tdd.tp.command;

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
