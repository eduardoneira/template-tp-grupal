package ar.fiuba.tdd.tp;

import java.util.*;
import java.util.stream.Collectors;

public class Motor {

    private LinkedList<Game> games;

    public Motor(String game) {

        games = new LinkedList<>();
        games.add( new FetchQuest() );
        games.add( new OpenDoor() );
        games.add( new OpenDoor2() );
        games.add( new CursedObject());
        games.add( new WolfSheepCabbage() );
        games.add( new TorresHanoi() );
        games.add( new TreasureHunt() );
    }

    Game createGame(String game) {
        for (Game actualGame: games) {
            if ( game.equals(actualGame.getName()) ) {
                return actualGame;
            }
        }
        return null;
    }

    public LinkedList<String> getNamesGames() {
        LinkedList<String> names = games.stream().map(Game::getName).collect(Collectors.toCollection(LinkedList::new));
        return names;
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
