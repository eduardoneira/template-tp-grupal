package ar.fiuba.tdd.tp.objects.general;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Created by fernando on 08/06/16.
 */
public class ConcreteObjectChangesParentRandomly extends ConcreteGameObjectWithParent implements GameObjectWithParent{

    private List<GameObjectWithChildren> parents;

    public ConcreteObjectChangesParentRandomly(String name, GameObjectWithChildren parent) {
        super(name, parent);
        parents = new ArrayList<>();
        parents.add(parent);
    }

    private static int getRandomNaturalNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    public void setOnePossibleParent(GameObjectWithChildren parent) { parents.add(parent); }

    public void changeParent() {
        List<GameObjectWithChildren> parentsToJump = new ArrayList<GameObjectWithChildren>(parents);
        parentsToJump.remove(this.getParent());

        int randomNum = getRandomNaturalNumber(0,parentsToJump.size());
        this.getParent().removeChild(this);
        this.setParent(parentsToJump.get(randomNum));
        this.getParent().addChild(this);
    }


}
