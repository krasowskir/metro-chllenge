package uebung.multithreading;

import java.util.concurrent.CountDownLatch;

public class Challenges {

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
    waldemar setzt sich an Tisch
    henrietta setzt sich an Tisch
    tanja setzt sich an Tisch
    =================================
    lidia hat ein Essstäbchen
    richard hat ein Essstäbchen
    henrietta hat ein Essstäbchen
    waldemar hat ein Essstäbchen
    lidia hat beide Essstäbchen und isst
    tanja hat ein Essstäbchen
    lidia ist fertig mit Essen :)
    tanja hat beide Essstäbchen und isst
    tanja ist fertig mit Essen :)
    richard hat beide Essstäbchen und isst
    henrietta hat beide Essstäbchen und isst
    richard ist fertig mit Essen :)
    waldemar hat beide Essstäbchen und isst
    olga hat ein Essstäbchen
    henrietta ist fertig mit Essen :)
    olga hat beide Essstäbchen und isst
    waldemar ist fertig mit Essen :)
    olga ist fertig mit Essen :)
     */
    public void challenge153() {
        TableManager manager = new TableManager();

        CountDownLatch fertig = new CountDownLatch(6);
        EatingProcess richard = new EatingProcess(manager, "richard", fertig);
        EatingProcess olga = new EatingProcess(manager, "olga", fertig);
        EatingProcess lidia = new EatingProcess(manager, "lidia", fertig);
        EatingProcess waldemar = new EatingProcess(manager, "waldemar", fertig);
        EatingProcess tanja = new EatingProcess(manager, "tanja", fertig);
        EatingProcess henrietta = new EatingProcess(manager, "henrietta", fertig);

        Thread t1 = new Thread(richard);
        t1.start();
        new Thread(olga).start();
        new Thread(lidia).start();
        new Thread(waldemar).start();
        new Thread(tanja).start();
        new Thread(henrietta).start();

        try {
            fertig.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}