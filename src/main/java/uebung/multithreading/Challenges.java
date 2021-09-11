package uebung.multithreading;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Challenges {

    public class Chopstick{
        private Lock lock = new ReentrantLock();
        private String name;

        public Chopstick() { }

        public boolean tryAcquire(String name){
            boolean lockSucceeded = this.lock.tryLock();
            if (lockSucceeded){
                this.name = name;
            }
            return lockSucceeded;
        }

        public String printWhoHoldsLock(){
            return this.name;
        }

        public void putBack(){
            this.lock.unlock();
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
    lidia setzt sich an Tisch
    tanja setzt sich an Tisch
    olga setzt sich an Tisch
    waldemar setzt sich an Tisch
    waldemar hat ein Essstäbchen
    waldemar hat beide Essstäbchen und isst
    richard hat ein Essstäbchen
    richard hat beide Essstäbchen und isst
    lidia hat ein Essstäbchen
    lidia hat beide Essstäbchen und isst
    tanja hat ein Essstäbchen
    tanja hat beide Essstäbchen und isst
     */
    public void challenge153(){
        Chopstick[] chopsticks = new Chopstick[5];
        Arrays.fill(chopsticks, new Chopstick());

        EatingProcess richard = new EatingProcess(chopsticks, "richard");
        Thread richardT = new Thread(richard);
        richardT.start();

        EatingProcess olga = new EatingProcess(chopsticks, "olga");
        Thread olgaT = new Thread(olga);
        olgaT.start();

        EatingProcess lidia = new EatingProcess(chopsticks, "lidia");
        Thread lidiaT = new Thread(lidia);
        lidiaT.start();

        EatingProcess waldemar = new EatingProcess(chopsticks, "waldemar");
        Thread waldemarT = new Thread(waldemar);
        waldemarT.start();

        EatingProcess tanja = new EatingProcess(chopsticks, "tanja");
        Thread tanjaT = new Thread(tanja);
        tanjaT.start();

        EatingProcess henrietta = new EatingProcess(chopsticks, "henrietta");
        Thread henriettaT = new Thread(henrietta);
        henriettaT.start();

        try {
            richardT.join();
            olgaT.join();
            lidiaT.join();
            waldemarT.join();
            tanjaT.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
