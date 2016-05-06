package ar.fiuba.tdd.tp.command;

import ar.fiuba.tdd.tp.Motor;

public class LoadGameServerCommandProxy extends AbstractCommandProxy {

    private String notSuchGame = "There is no such game. Games Available are :";

    public LoadGameServerCommandProxy(String name, AbstractCommand command) {
        super(name, command);
    }

    @Override
    public boolean handle(String name) {
        if (canHandle(name)) {
            String gameName = name.replace(this.name + " ", "");
            if (isValidGame(gameName)) {
                Object [] parametros = {gameName};
                command.handle(parametros);
            }
            return true;
        }
        return false;
    }

    private boolean isValidGame(String gameName) {
        Motor motor = new Motor();
        if (motor.isValidGame(gameName)) {
            return true;
        } else {
            System.out.println(notSuchGame);
            motor.getNamesGames().forEach(System.out::println);
            return false;
        }
    }

}
