package ar.fiuba.tdd.tp.command;

import ar.fiuba.tdd.tp.Motor;

public class LoadGameServerCommandProxy extends AbstractCommandProxy {

    private final String notSuchGame = "There is no such game. Games Available are :";

    public LoadGameServerCommandProxy(String name, AbstractCommand command) {
		super(name, command);
    }

    @Override
    public boolean handle(String name) {
        if (canHandle(name)) {
            String gameName = ((String) name).replace(this.name + " ", "");
            if (isValidGame(gameName)) {
                Object [] parametros = {gameName};
                command.handle(parametros);
            }
            return true;
        }
        return false;
    }

    private boolean isValidGame(String gameName) {
        Motor motor = new Motor(gameName);
        if (motor.isValidGame(gameName)) {
            return true;
        } else {
            System.out.println(notSuchGame);
            for ( String name: motor.getNamesGames()) {
                System.out.println(name);
            }
            return false;
        }
    }

}
