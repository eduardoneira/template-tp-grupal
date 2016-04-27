package ar.fiuba.tdd.tp;

/**
 * Created by Edu on 4/27/2016.
 */
public class Motor {

    public static String[] GAMESAVAILABLE = {"Fetch Quest",
            "Open Door",
            "Open Door 2",
            "Cursed Object",
            "Wolf, Sheep and Cabbage",
            "Hanoi Towers",
            "Treasure Hunt"};

    // TODO: Refactor para evitar switch, cuando este la factory posta hay que meterlo ahi y sale
    public static Game createGame(String game) {
        switch ( game ) {
            case "Fetch Quest" :
                return new FetchQuest();
            default :
                return new FetchQuest();
        }
    }

    public static boolean isValidGame(String possibleGame) {
        for ( String game : GAMESAVAILABLE) {
            if ( possibleGame.toLowerCase().equals(game.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
