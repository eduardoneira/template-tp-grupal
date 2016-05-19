package ar.fiuba.tdd.tp;

import java.util.*;
import java.util.stream.Collectors;

public class Motor {

    private final LinkedList<Game> games;

    public Motor() {

        games = new LinkedList<>();
        games.add( new FetchQuest() );
        games.add( new OpenDoor() );
        games.add( new OpenDoor2() );
        games.add( new CursedObject());
        games.add( new WolfSheepCabbage() );
        games.add( new TorresHanoi() );
        games.add( new TreasureHunt() );
    }

    public Game createGame(String game) {
        for (Game actualGame: games) {
            if ( game.equals(actualGame.getName()) ) {
                return actualGame;
            }
        }
        return null;
    }

    public LinkedList<String> getNamesGames() {
        return games.stream().map(Game::getName).collect(Collectors.toCollection(LinkedList::new));
    }

    public boolean isValidGame(String possibleGame) {
        for ( Game actualGame: games) {
            if ( possibleGame.toLowerCase().equals(actualGame.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
