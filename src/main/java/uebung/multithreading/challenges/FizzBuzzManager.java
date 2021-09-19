package uebung.multithreading.challenges;

import java.util.Random;
import java.util.Stack;

public class FizzBuzzManager implements Runnable {

    private Stack<Integer> numbers;
    private Random rand = new Random();
    private volatile boolean isDone = false;
    private volatile boolean processed = false;

    public FizzBuzzManager() {
        this.numbers = new Stack<>();
    }

    public Stack<Integer> getNumbers() {
        return numbers;
    }

    public synchronized boolean isProcessed() {
        return processed;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public synchronized void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public void run() {
        while (!isDone) {
            try {
                Thread.sleep(200);
                synchronized (numbers) {
                    numbers.push(generateRandomNumb());
                }
                Thread.sleep(200);
                synchronized (numbers) {
                    if (!numbers.isEmpty()) {
                        System.out.println("Number: " + numbers.pop());
                    }
                }
                this.setProcessed(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int generateRandomNumb() {
        return rand.nextInt(100);
    }
}
