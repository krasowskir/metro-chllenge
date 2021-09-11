package uebung.multithreading.firstSteps;

public class MeinThread extends Thread {

    private int count;
    private String msg;

    public MeinThread(int count, String msg) {
        this.count = count;
        this.msg = msg;
        this.setName("This is thread: " + this.msg);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {

        while (count > 0) {
            System.out.println(this.msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.println("IE");
            }
            count--;
        }
        System.out.println("all messages printed");
    }

}
