package ar.fiuba.tdd.tp.command;

public interface AbstractCommand {
    final String FATALERROR = "Fatal Error";
    public void handle(Object[] param);
}