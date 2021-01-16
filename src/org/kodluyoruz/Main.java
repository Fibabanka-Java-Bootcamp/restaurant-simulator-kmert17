package org.kodluyoruz;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        Musteri[] musteriler = null;
        Masa[] masalar = null;
        Garson[] garsonlar = null;
        Sef[] sefler = null;
        ExecutorService executorService1 = Executors.newFixedThreadPool(Constants.MUSTERI_SAYISI);
        ExecutorService executorService2 = Executors.newFixedThreadPool(Constants.GARSON_SAYISI);
        ExecutorService executorService3 = Executors.newFixedThreadPool(Constants.SEF_SAYISI);



        try {
            masalar = new Masa[Constants.MASA_SAYISI];
            musteriler = new Musteri[Constants.MUSTERI_SAYISI];
            garsonlar = new Garson[Constants.GARSON_SAYISI];
            sefler = new Sef[Constants.SEF_SAYISI];

            for(int i = 1; i<Constants.MASA_SAYISI+1; i++) {
                masalar[i-1] = new Masa(i);
            }

            for(int i = 1; i<Constants.MUSTERI_SAYISI+1; i++) {
                musteriler[i-1] = new Musteri(i, masalar);
                executorService1.execute(musteriler[i-1]);
            }
            for(int i = 1; i<Constants.GARSON_SAYISI+1; i++) {
                garsonlar[i-1] = new Garson(i, masalar);
                executorService2.execute(garsonlar[i-1]);
            }
            for(int i = 1; i<Constants.SEF_SAYISI+1; i++) {
                sefler[i-1] = new Sef(i, masalar);
                executorService3.execute(sefler[i-1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            executorService1.shutdown();
            executorService2.shutdown();
            executorService3.shutdown();
        } finally {
            executorService1.shutdown();
            executorService2.shutdown();
            executorService3.shutdown();
        }

    }

}

