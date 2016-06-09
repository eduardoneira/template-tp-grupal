package ar.fiuba.tdd.tp.objects.general;
import java.util.List;
import java.util.Random;
/**
 * Created by fernando on 08/06/16.
 */
public class ConcreteObjectChangesParentRandomly extends ConcreteGameObjectWithParent implements GameObjectWithParent{

    private static List<GameObjectWithChildren> parents;

    public ConcreteObjectChangesParentRandomly(String name, GameObjectWithChildren parent) {
        super(name, parent);
        parents.add(parent);
    }

    private static int getRandomNaturalNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    public void setOnePossibleParent(GameObjectWithChildren parent) { parents.add(parent); }

    public void changeParent() {
        GameObjectWithChildren parent;
        int randomNum = getRandomNaturalNumber(0,parents.size());
        this.setParent(parents.get(randomNum));
        parents.get(randomNum).addChild(this);
    }


}
