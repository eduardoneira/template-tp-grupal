package ar.fiuba.tdd.tp.objects.general;
import java.util.List;
import java.util.Random;
/**
 * Created by fernando on 08/06/16.
 */
public class ConcreteObjectRandom extends ConcreteGameObjectWithParent implements GameObjectWithParent{

    private static List<GameObjectWithChildren> parents;

    public ConcreteObjectRandom(String name, GameObjectWithChildren parent) {
        super(name, parent);
    }

    private static int getRandomNaturalNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    public void setOnePossibleParent(GameObjectWithChildren parent) { parents.add(parent); }

    public GameObjectWithChildren getActualParent() {
        GameObjectWithChildren parent;
        if ( parents.isEmpty()) { return null; }
        else {
            int randomNum = getRandomNaturalNumber(0,parents.size());
            return parents.get(randomNum);
        }
    }

}
