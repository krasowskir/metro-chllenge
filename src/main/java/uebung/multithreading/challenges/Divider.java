package uebung.multithreading.challenges;

import java.util.Stack;

public class Divider implements DividibleBy, Runnable {

    private final int DIVIDING_FACTOR;
    private volatile boolean isDone = false;
    private Stack<Integer> source;


    public Divider(int divBy, Stack<Integer> source) {
        this.DIVIDING_FACTOR = divBy;
        this.source = source;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String handleNumber(int number) {
        System.out.println("numb: " + number);
        String value = number % DIVIDING_FACTOR == 0 ? "FIZZ" : "";
        if (!"".equals(value)){
            System.out.println("number: " + number + " " + value);
        }
        return value;
    }

    @Override
    public void run() {
        while (!isDone && !source.isEmpty()){
            try {
                Thread.sleep(500);
                this.handleNumber(this.source.peek());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
