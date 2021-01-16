package org.kodluyoruz;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Masa {

    private int id;
    private Lock lock1;
    private Lock lock2;
    private Lock lock3;
    private boolean yemekYapildiMi = false;
    private boolean siparisAlindiMi = false;




    public Masa(int id) {
        this.id = id;
        this.lock1 = new ReentrantLock();
        this.lock2 = new ReentrantLock();
        this.lock3 = new ReentrantLock();
    }

    public void otur(Musteri musteri) throws InterruptedException {
        if(lock1.tryLock(1, TimeUnit.SECONDS) && siparisAlindiMi && yemekYapildiMi){
            System.out.println(musteri + " at " + this + " sipariş verdi." );
            Thread.sleep(200);
            lock1.unlock();
            System.out.println(musteri + " at " + this + " yemeğini bitirdi");
            siparisAlindiMi = false;
            yemekYapildiMi = false;
        }
    }

    public boolean siparisAl(Garson garson) throws InterruptedException {
        if(lock2.tryLock(10, TimeUnit.MILLISECONDS) && !siparisAlindiMi) {
            System.out.println(garson + " siparişi almaya gitti" + this);
            Thread.sleep(10);
            lock2.unlock();
            System.out.println(garson + " siparişi aldı " + this);
            siparisAlindiMi = true;
            return siparisAlindiMi;
        }

        return siparisAlindiMi;


    }

    public boolean yemekYap(Sef sef) throws InterruptedException {
        if(lock3.tryLock(1, TimeUnit.MILLISECONDS) && siparisAlindiMi && !yemekYapildiMi) {
            System.out.println(sef + " yemeğe başladı." + this);
            Thread.sleep(30);
            lock3.unlock();
            System.out.println(sef + " yemeği yaptı. " + this);
            yemekYapildiMi = true;
            return yemekYapildiMi;
        }

        return yemekYapildiMi;
    }

    @Override
    public String toString() {
        return " Masa #" + id;
    }
}
