package ar.fiuba.tdd.tp.timedevent;

import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

import static java.lang.Thread.sleep;

public class ActionGeneration implements Runnable{

    private List<ActionWithTime> actions;
    private List<Integer> timeToNextEvent;
    private BooleanState serverRunning;
    private Map<String, Socket> clientSockets;
    int lastTime;
    private TimerReference timer;

    public ActionGeneration(Map<String, Socket> clientSockets, TimerReference timer) {
        actions = new ArrayList<>();
        timeToNextEvent = new ArrayList<>();
        this.clientSockets = clientSockets;
        this.timer = timer;
        serverRunning = new BooleanState(true);
    }

    public void killActionGeneration() {
        serverRunning.setFalse();
    }

    public void addActionWithTime(ActionWithTime actionWithTime ) {
        actions.add(actionWithTime);
        timeToNextEvent.add(actionWithTime.getTime());
    }

    private void updateTimesToNextEvent(int elapsed) {
        for (int i = 0; i < timeToNextEvent.size(); ++i) {
            timeToNextEvent.set(i, timeToNextEvent.get(i) - elapsed);
        }
    }

    @Override
    public void run() {
        serverRunning.setTrue();
        lastTime = timer.currentTimeSeconds();

        while (serverRunning.isTrue()) {

            this.updateEvents();

            this.sleepUntilNextEvent();
        }
    }

    private void updateEvents() {
        int currTime = timer.currentTimeSeconds();
        int elapsed = currTime - lastTime;
        lastTime = currTime;
        updateTimesToNextEvent(elapsed);
        //System.out.println("veo timed events");

        for (int i = 0; i < timeToNextEvent.size(); ++i) {
            if (timeToNextEvent.get(i) <= 0) {
                ActionWithTime actual = actions.get(i);
                StringBuilder response = new StringBuilder();

                if (actual.getAction().doEvent(response)) {
                    // TODO: negrada para enviar el mensaje a todos los jugadores
                    forwardEventMessage(response);
                }
                //System.out.println(currTime + "/" + timeToNextEvent.get(i) + ": timed event: " + response.toString());
                timeToNextEvent.set(i, actual.getTime());
            }
        }
    }

    private void sleepUntilNextEvent() {
        int minTime = getMinTimeToNextEvent();

        try {
            sleep(timer.getSleepTime(minTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void forwardEventMessage(StringBuilder response) {
        try {
            for (Socket s : clientSockets.values()) {
                PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));
                out.println("juego: " + response.toString());
                out.flush();
                //out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getMinTimeToNextEvent() {
        if (timeToNextEvent.isEmpty()) {
            return 1;
        } else {
            return Collections.min(timeToNextEvent);
        }
    }
}
