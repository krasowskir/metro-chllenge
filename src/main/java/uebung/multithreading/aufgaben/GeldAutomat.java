package uebung.multithreading.aufgaben;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GeldAutomat {

    int guthaben = 100;
    int anz = 0;
    Lock lock;

    public GeldAutomat() {
        this.lock = new ReentrantLock();
    }

    public GeldAutomat(int betrag, int anz) {
        this.guthaben = betrag;
        this.anz = anz;
        this.lock = new ReentrantLock();
    }

    public int getAnz() {
        return anz;
    }

    public int getGuthaben() {
        return guthaben;
    }

    public synchronized int einzahle(int betrag){
//        lock.lock(); //kann auch mit lock gelöst werden, ohne synchronized. Das Ergebnis ist dasselbe
        try {
            int tmp = guthaben;
            tmp = tmp + betrag;
            Thread.sleep(1000);
            this.guthaben = tmp;
            this.anz += 1;
//            lock.unlock();
            return this.guthaben;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
