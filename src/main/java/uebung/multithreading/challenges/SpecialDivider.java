package uebung.multithreading.challenges;

import java.util.Stack;

public class SpecialDivider implements DividibleBy, Runnable {

    private final int DIVIDING_FACTOR1 = 5;
    private final int DIVIDING_FACTOR2 = 3;
    private volatile boolean isDone = false;
    private Stack<Integer> source;
    private FizzBuzzManager manager;

    public SpecialDivider(FizzBuzzManager manager) {
        this.manager = manager;
        this.source = manager.getNumbers();
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public void handleNumber() {
        int number = this.source.peek();
        if (number % DIVIDING_FACTOR1 == 0 && number % DIVIDING_FACTOR2 == 0) {
            this.source.pop();
            System.out.println("Number: " + number + " " + "FizzBuzz");
        }
    }

    @Override
    public void run() {
        while (!isDone) {
            synchronized (source) {
                if (!source.isEmpty()) {
                    this.handleNumber();
                }
            }
            manager.setProcessed(true);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
