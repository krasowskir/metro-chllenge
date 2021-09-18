package uebung.multithreading.challenges;

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

    /*
    Call In Order: Suppose we have the following code:
    public class Foo {
        public Foo() { ... }
        public void first() { ... }
        public void second() { ... }
        public void third() { ... }
    }
    The same instance of Foo will be passed to three different threads.
    ThreadA will call first, threadB will call second, and threadC will call third.
    Design a mechanism to ensure that first is called before second and second is called before third.

     */
    public void challenge155(){
        Foo foo = new Foo();
        Thread t1 = new Thread(new FooRunnable(foo, 0));

        Thread t2 = new Thread(new FooRunnable(foo, 1));
        t2.start();
        t1.start();

        Thread t3 = new Thread(new FooRunnable(foo, 2));
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    Synchronized Methods: You are given a class with synchronized method A and a normal method B.
    If you have two threads in one instance of a program, can they both execute A at the same time?
    Can they execute A and B at the same time?
     */
    public void challenge156(){
        SynchronizedClass synchronizedClass = new SynchronizedClass();

        SynchronizedRunnable run1 = new SynchronizedRunnable(synchronizedClass, 5);
//        SynchronizedRunnable run2 = new SynchronizedRunnable(synchronizedClass,7);
        SynchrBRunnable run3 = new SynchrBRunnable(synchronizedClass, 3);

        Thread t1 = new Thread(run1);
//        Thread t2 = new Thread(run2);
        Thread t3 = new Thread(run3);

        t1.start();
        t3.start();

        try {
            Thread.sleep(7000);
            run3.setDone(true);
            run1.setDone(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t1.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    FizzBuzz: In the classic problem FizzBuzz, you are told to print the numbers from 1 to n.
    However, when the number is divisible by 3, print"Fizz''. When it is divisible by 5, print"Buzz''.
    When it is divis­ ible by 3 and 5, print "FizzBuzz''.
    In this problem, you are asked to do this in a multithreaded way.
    Implement a multithreaded version of FizzBuzz with four threads.
    One thread checks for divisibility of 3 and prints "Fizz''.
    Another thread is responsible for divisibility of 5 and prints"Buzz''.
    A third thread is responsible for divisibility of 3 and 5 and prints"FizzBuzz''.
    A fourth thread does the numbers.
     */
    public void challenge157() {

        //producer erzeugen und starten
        FizzBuzzManager fizzBuzzManager = new FizzBuzzManager();
        new Thread(fizzBuzzManager).start();


        // 3 threads consumer erzeugen und starten
        Divider fizz, buzz;
        SpecialDivider fizzBuzz;

        fizzBuzz = new SpecialDivider(fizzBuzzManager);
        fizz = new Divider(3, fizzBuzzManager);
        buzz = new Divider(5, fizzBuzzManager);

        new Thread(fizz).start();
        new Thread(buzz).start();
        new Thread(fizzBuzz).start();

        try {
            Thread.sleep(20000);
            fizzBuzzManager.setReady(true);
            fizz.setDone(true);
            buzz.setDone(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
