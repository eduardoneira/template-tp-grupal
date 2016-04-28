package ar.fiuba.tdd.tp.command;

public class DefaultCommandProxy extends AbstractCommandProxy {

    public DefaultCommandProxy(String name, AbstractCommand command) {
        super(name, command);
    }

    @Override
    public boolean handle(String name) {
        if (canHandle(name)) {
            Object [] parametros = {name};
            this.command.handle(parametros);
            return true;
        }
        return false;
    }
}
