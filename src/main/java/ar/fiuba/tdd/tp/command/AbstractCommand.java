package ar.fiuba.tdd.tp.command;

public interface AbstractCommand {
    String FATALERROR = "Fatal Error";
    void handle(Object[] param);
}