package uebung.multithreading.challenges;

import java.util.Stack;

public class Divider implements DividibleBy, Runnable {

    private final int DIVIDING_FACTOR;
    private volatile boolean isDone = false;
    private FizzBuzzManager manager;
    private Stack<Integer> source;
    private String call;


    public Divider(int divBy, FizzBuzzManager manager) {
        this.DIVIDING_FACTOR = divBy;
        this.manager = manager;
        this.source = manager.getNumbers();
        this.call = DIVIDING_FACTOR == 3 ? "FIZZ" : "BUZZ";
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public void run() {
        while (!isDone) {
            if (manager.isProcessed()) {
                if (!source.isEmpty()) {
                    synchronized (source) {
                        this.handleNumber();
                    }
                }
                this.manager.setProcessed(true);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void handleNumber() {
        int number = this.source.peek();
        if (number % DIVIDING_FACTOR == 0) {
            this.source.pop();
            System.out.println("Number: " + number + " " + call);
        }
    }
}
