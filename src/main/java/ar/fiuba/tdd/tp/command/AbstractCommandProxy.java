package ar.fiuba.tdd.tp.command;

public abstract class AbstractCommandProxy {
    protected String name;
    protected AbstractCommand command;

    AbstractCommandProxy(String name, AbstractCommand command) {
		super();
        this.name = name;
        this.command = command;
    }

    public abstract boolean handle(String name);

    protected boolean canHandle(String name) {
        return name.toLowerCase().matches(this.name.toLowerCase().concat("(.)*"));
    }
}
