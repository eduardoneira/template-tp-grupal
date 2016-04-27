package ar.fiuba.tdd.tp;

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
        if ( game.equals("Fetch Quest") ) {
            return new FetchQuest();
        } else {
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
