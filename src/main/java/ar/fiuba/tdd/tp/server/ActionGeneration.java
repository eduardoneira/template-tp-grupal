package ar.fiuba.tdd.tp.server;

import java.util.LinkedList;
import java.util.List;

public class ActionGeneration {

    private static List<ActionWithTime> actions;
    private boolean serverRunning;

    public void killActionGeneration(){ serverRunning = false; }

    public void startDoingActions(){
        serverRunning = true;
        //while (serverRunning){

        //}
    }
    public void setActionWithTime(ActionWithTime actionWithTime ){
        actions.add(actionWithTime);
    }
    private List<ActionWithTime> getActionsToDo (int currentTime){
        List<ActionWithTime> actionsInTime = new LinkedList<ActionWithTime>();
        if (actions.isEmpty()){
            return null;
        }
        for (int i=0;i<actions.size();i++){
            if (isDivisor(actions.get(i).getTime(),currentTime)){
                actionsInTime.add(actions.get(i));
            }
        }
        return actionsInTime;
    }
    //TODO
    private boolean isDivisor(int divisor, int numero) {
        return true;
    }
}
