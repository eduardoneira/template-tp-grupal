package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.model.Game;

/**
 * Created by eduardo.neira on 19/05/2016.
 */
public class TheEscape extends Game {
    
    public TheEscape(String name) {
        super(name);
    }

    @Override
    public boolean checkWinCondition() {
        return false;
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }

    @Override
    public Game build() {
        return null;
    }
}
