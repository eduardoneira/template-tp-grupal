package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.*;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.server.BuilderLoader;

import java.io.IOException;
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
        games.add( new TempleQuest() );
    }

    public Game createGame(String game) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
       /* for (Game actualGame: games) {
            if ( game.equals(actualGame.getName()) ) {
                return actualGame;
            }
        }*/
        String path = "build/classes/main/ar/fiuba/tdd/tp/";
        GameBuilder builder = BuilderLoader.load(path.concat(game).concat(".jar"));
        builder.build();
        return (Game) builder;
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
