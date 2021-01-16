package org.kodluyoruz;

import java.util.Arrays;
import java.util.Random;

public class Musteri implements Runnable{

    private int id;
    private Masa[] masalar;

    public Musteri(int id, Masa[] masalar) {
        this.id = id;
        this.masalar = masalar;
    }

    @Override
    public void run() {
        Random random = new Random();

        while(true) {
            int masaId = random.nextInt(Constants.MASA_SAYISI);

            try {
                masalar[masaId].otur(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Musteri #"+id;
    }
}

