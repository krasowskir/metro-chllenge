package uebung.multithreading.aufgaben;

/*
    Entwickeln Sie eine Klasse, die das Interface Runnable implementiert sowie die Methoden main, start und stop.
    start erzeugt einen neuen Thread und startet ihn, stop beendet ihn.
    Für jeden neu erzeugten Thread ist auch ein neuer Name zur Unterscheidung zu wählen.
    Der Thread soll seinen Namen in Abständen von zwei Sekunden ausgeben.
    In main können die Methoden start und stop, gesteuert über Tastatureingabe, aufgerufen werden.
 */
public class RichThread implements Runnable{

    private volatile boolean done = false;
    private String name;

    public RichThread() {
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public void run() {
        while (!done){
            System.out.println(this.name);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        RichThread richThread = new RichThread();
        richThread.start("richard");

        RichThread olgaThread = new RichThread();
        olgaThread.start("olga");

        try {
            Thread.sleep(5000);
            olgaThread.stop("olga");
            Thread.sleep(1000);
            richThread.stop("richard");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start(String name){
        this.name = name;
        new Thread(this, this.name).start();
    }

    public void stop(String name){
        if (this.name.equals(name)){
            done = true;
        }
    }
}
