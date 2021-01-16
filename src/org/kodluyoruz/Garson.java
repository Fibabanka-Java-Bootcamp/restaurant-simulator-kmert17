package org.kodluyoruz;

import java.util.Random;

public class Garson implements Runnable{

    private int id;
    private Masa[] masalar;

    public Garson(int id, Masa[] masalar) {
        this.id = id;
        this.masalar = masalar;
    }

    @Override
    public void run() {
        Random random = new Random();

        while(true) {
            int masaId = random.nextInt(Constants.MASA_SAYISI);

            try {
                masalar[masaId].siparisAl(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Garson #"+id;
    }
}