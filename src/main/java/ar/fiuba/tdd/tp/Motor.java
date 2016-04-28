package ar.fiuba.tdd.tp;
import java.util.*;

public class Motor {

    private LinkedList<Game> games;

    public Motor(String game) {

        games=new LinkedList<Game> ();
        games.add( new FetchQuest() );
        //games.add( new OpenDoor() );
        //games.add( new OpenDoor2() );
        //games.add( new CursedObject() );
        games.add( new WolfSheepCabbage() );
        //games.add( new HanoiTowers() );
        //games.add( new TreasureHunt() );
    }
    Game getGame(String game){
        for (Game actualGame: games){
            if ( game.equals(actualGame.getName()) ){
                return actualGame;
            }
        }
        return null;
    }

    // TODO: Refactor para evitar switch, cuando este la factory posta hay que meterlo ahi y sale
    public static Game createGame(String game) {
        if ( game.equals("Fetch Quest") ) {
            return new FetchQuest();
        } else {
            return new FetchQuest();
        }
    }

    private boolean isValidGame(String possibleGame) {
        for ( Game actualGame: games) {
            if ( possibleGame.toLowerCase().equals(actualGame.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
