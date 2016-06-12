package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by Master on 12/06/2016.
 */
public class ClientListener implements Runnable, ClientResponses {

    private BufferedReader serverInputStream;
    private BooleanState keepPlaying;

    public ClientListener(BufferedReader serverInputStream, BooleanState keepPlaying) {
        this.serverInputStream = serverInputStream;
        this.keepPlaying = keepPlaying;
    }

    @Override
    public void run() {
        // TODO: REVISAR ESTOOOO, es re cabeza
        String fromServer = "";
        while (keepPlaying.isTrue() && fromServer != null) {
            try {

                System.out.println(fromServer);
                fromServer = serverInputStream.readLine();

            } catch (IOException e) {

                keepPlaying.setFalse();
            }
        }
        keepPlaying.setFalse();
    }
}
