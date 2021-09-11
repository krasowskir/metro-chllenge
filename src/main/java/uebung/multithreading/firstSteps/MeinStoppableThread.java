package uebung.multithreading.firstSteps;

public class MeinStoppableThread implements Runnable {

    protected volatile boolean running;
    private int count = 5;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running || count > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--- MeinStoppableThread is running ---");
            System.out.println("counter: " + count);
            count--;
        }
    }
}
