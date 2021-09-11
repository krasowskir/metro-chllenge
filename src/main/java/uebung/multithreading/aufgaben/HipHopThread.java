package uebung.multithreading.aufgaben;

public class HipHopThread extends Thread {

    private String word = "Hip";
    private volatile boolean done = false;
    private int SLEEP = 500;

    public HipHopThread(String name, String word, int sleepingTime) {
        super(name);
        this.word = word;
        this.SLEEP = sleepingTime;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public void run() {
        super.run();
        while (!done){
            System.out.println(word);
            try {
                Thread.sleep(this.SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
