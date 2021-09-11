package uebung.multithreading.aufgaben;


import java.nio.file.Paths;

public class Tasks {

    /*
    Erstellen Sie ein Programm mit zwei Threads, das die beiden Wörter "Hip" und "HOP"
    in einer Endlosschleife unterschiedlich schnell ausgibt. Realisieren Sie zwei Programmvarianten:
    -Ableitung von der Klasse Thread
    -Implementierung des Interfaces Runnable.
     */
    public void aufgabe951(){

        HipHopThread thread1 = new HipHopThread("Hip thread", "Hip", 500);
        HipHopThread thread2 = new HipHopThread("Hop thread", "Hop", 700);

        thread1.start();
        thread2.start();
        try {
            Thread.sleep(5000);
            thread2.setDone(true);
            thread2.setDone(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("======== with runnables ========");
        Thread runThread1 = new Thread(() -> {
            int counter = 7;
            while (counter-- > 0){
                System.out.println("Hip");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        runThread1.start();

        Thread runThread2 = new Thread(() -> {
            int counter = 5;
            while (counter-- > 0){
                System.out.println("Hop");
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        runThread2.start();

        try {
            runThread1.join();
            runThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    Schreiben Sie ein Programm, das den Namen und die Priorität des Threads ausgibt, der die Methode main ausführt.
     */

    /*
    Entwickeln Sie eine Klasse, die das Interface Runnable implementiert sowie die Methoden main, start und stop.
    start erzeugt einen neuen Thread und startet ihn, stop beendet ihn.
    Für jeden neu erzeugten Thread ist auch ein neuer Name zur Unterscheidung zu wählen.
    Der Thread soll seinen Namen in Abständen von zwei Sekunden ausgeben.
    In main können die Methoden start und stop, gesteuert über Tastatureingabe, aufgerufen werden.
     */
    public static void main(String[] args){

        System.out.println("main thread: " + Thread.currentThread().getName() + " mit Priorität: " +
                Thread.currentThread().getPriority());

    }

    /*
    Zwei parallel laufende Threads sollen Nachrichten in dieselbe Protokolldatei schreiben.
    Entwickeln Sie die Klasse LogFile, die die Protokolldatei verwaltet. Die Klasse soll die Methode
    public synchronized void writeLine(String msg) enthalten,
    die eine Zeile bestehend aus Systemzeit und msg schreibt.
    Erstellen Sie ein Testprogramm, das die Protokolldatei erzeugt, zwei Threads startet,
    die in diese Datei mehrere Nachrichten schreiben, und das die Datei schließt,
    nachdem die Ausführung der beiden Threads beendet ist.
     */
    public void aufgabe954(){
        LogFile myLogFile = new LogFile(Paths.get("/tmp/uebungsLogFile.log"));
        Thread writer1 = new Thread(() -> {
            int counter = 5;
            while (counter-- > 0){

                myLogFile.writeLine("Thread1 schreibt dies");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread writer2 = new Thread(() -> {
            int counter = 5;
            while (counter-- > 0){

                myLogFile.writeLine("Thread2 schreibt das");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        writer1.start();
        writer2.start();
        try {
            writer1.join();
            writer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    Schreiben Sie ein Programm,
    in dem mehrere Threads zur gleichen Zeit einen zufälligen Betrag auf dasselbe Konto einzahlen.
    Die Additionsmethode soll aus den einzelnen Threads mehrfach aufgerufen werden.
    Geben Sie am Ende die Anzahl der insgesamt durchgeführten Additionen und den aktuellen Kontostand aus.
    Schreiben Sie das Programm so, dass keine Additionen verloren gehen.
     */
    public void aufgabe955() throws InterruptedException {
        GeldAutomat automat = new GeldAutomat();

        ThreadLocal<String> localPlayer = new ThreadLocal<>();
        localPlayer.set("first");

        Thread einz1 = new Thread(()->{
            System.out.println("neuer Betrag für t1 = " + automat.einzahle(10) + " einzahlen: " + 10);
            localPlayer.set("richard");
            System.out.println("neuer Betrag für t1 = " + automat.einzahle(20)+ " einzahlen: " + 20);
            System.out.println(localPlayer.get());
        });

        Thread einz2 = new Thread(()->{
            System.out.println(localPlayer.get());
            System.out.println("neuer Betrag für t2 = " + automat.einzahle(5)+ " einzahlen: " + 5);
            System.out.println("neuer Betrag für t2 = " + automat.einzahle(15)+ " einzahlen: " + 15);
            System.out.println("neuer Betrag für t2 = " + automat.einzahle(10)+ " einzahlen: " + 10);
            System.out.println(localPlayer.get());
            localPlayer.set("unreachable wert");
        });
        Thread einz3 = new Thread(()->{
            System.out.println("neuer Betrag für t3 = " + automat.einzahle(11)+ " einzahlen: " + 11);
            localPlayer.set("waska");
            System.out.println("neuer Betrag für t3 = " + automat.einzahle(22) + " einzahlen: " + 22);
            System.out.println("neuer Betrag für t3 = " + automat.einzahle(8) + " einzahlen: " + 8);
            System.out.println(localPlayer.get());
        });

        einz1.start();
        einz2.start();
        einz3.start();
        einz3.join();
        einz2.join();
        einz1.join();
        System.out.println("Endbetrag: " + automat.getGuthaben() + " Anz an Additionen: " + automat.getAnz());
    }


}
