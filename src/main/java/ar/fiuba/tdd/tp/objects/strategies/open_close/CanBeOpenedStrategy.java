package ar.fiuba.tdd.tp.objects.strategies.open_close;

import ar.fiuba.tdd.tp.abilities.CanBeOpened;
import ar.fiuba.tdd.tp.abilities.CanOpen;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanOpen;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public class CanBeOpenedStrategy implements CanBeOpened {
    CanBeOpened instance;
    BooleanState open;

    public CanBeOpenedStrategy(){
        open = new BooleanState(false);
    }

    public CanBeOpenedStrategy(BooleanState state){
        open = state;
    }

    @Override
    public String beOpenedBy(GameObjectCanOpen opener) {
        // tal vez diferenciar si ya estaba abierto?
        open.setTrue();
        return ""; // VER
    }

    // funciones de control para ser usadas por clases que extiendan esta funcionalidad
    public void setOpen(){
        open.setTrue();
    }

    public void setClosed(){
        open.setFalse();
    }
}
