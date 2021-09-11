package uebung.multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Challenges {

    public class Chopstick {
        /*  ein und derselbe thread, kann 1, 2 und x locks auf dem selben Objekt haben
         */
        private Lock lock = new ReentrantLock();
        private String name;

        public Chopstick() {
        }

        public synchronized boolean tryAcquire(String name) throws InterruptedException {
            boolean lockSucceeded = false;
            if (!name.equals(this.name)) {
                lockSucceeded = this.lock.tryLock(1000l, TimeUnit.MILLISECONDS);
            }
            if (lockSucceeded) {
                this.name = name;
            }
            return lockSucceeded;
        }

        public String printWhoHoldsLock() {
            return this.name;
        }

        public synchronized void putBack() {
            this.lock.unlock();
            this.name = "";
        }
    }

    /*
   Dining Philosophers: In the famous dining philosophers problem,
   a bunch of philosophers are sitting around a circular table with one chopstick between each of them.
   A philosopher needs both chopsticks to eat, and always picks up the left chopstick before the right one.
   A deadlock could potentially occur if all the philosophers reached for the left chopstick at the same time.
   Using threads and locks, implement a simulation of the dining philosophers problem that prevents dead­ locks.
    */

    /*
    richard setzt sich an Tisch
    olga setzt sich an Tisch
    lidia setzt sich an Tisch
    tanja setzt sich an Tisch
    henrietta setzt sich an Tisch
    waldemar setzt sich an Tisch
    tanja hat ein Essstäbchen
    olga hat ein Essstäbchen
    waldemar hat ein Essstäbchen
    henrietta hat ein Essstäbchen
    lidia hat ein Essstäbchen
     */
    public void challenge153() {
        Chopstick[] chopsticks = {new Chopstick(),new Chopstick(),new Chopstick(),new Chopstick(),new Chopstick()};

        EatingProcess richard = new EatingProcess(chopsticks, "richard");
        Thread richardT = new Thread(richard, "richard");
        richardT.start();

        EatingProcess olga = new EatingProcess(chopsticks, "olga");
        Thread olgaT = new Thread(olga, "olga");
        olgaT.start();

        EatingProcess lidia = new EatingProcess(chopsticks, "lidia");
        Thread lidiaT = new Thread(lidia, "lidia");
        lidiaT.start();

        EatingProcess waldemar = new EatingProcess(chopsticks, "waldemar");
        Thread waldemarT = new Thread(waldemar, "waldemar");
        waldemarT.start();

        EatingProcess tanja = new EatingProcess(chopsticks, "tanja");
        Thread tanjaT = new Thread(tanja, "tanja");
        tanjaT.start();

        EatingProcess henrietta = new EatingProcess(chopsticks, "henrietta");
        Thread henriettaT = new Thread(henrietta, "henrietta");
        henriettaT.start();

        try {
            richardT.join();
            olgaT.join();
            lidiaT.join();
            waldemarT.join();
//            tanjaT.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
