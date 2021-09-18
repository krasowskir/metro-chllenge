package uebung.multithreading.challenges;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzzManager implements Runnable {

    private Stack<Integer> numbers;
    private Random rand = new Random();
    private volatile boolean isReady = false;
    private Lock lock = new ReentrantLock();
    private volatile boolean processed = false;

    public FizzBuzzManager() {
        this.numbers = new Stack<>();
    }

    public boolean isEmpty() {
        return numbers.isEmpty();
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public synchronized void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public synchronized Result readNumberFromStack() {
        return new Result(this.processed, this.numbers.peek());
    }


    private int generateRandomNumb() {
        return rand.nextInt(100);
    }

    @Override
    public void run() {

        while (!isReady) {
            try {
                Thread.sleep(200);
                numbers.push(generateRandomNumb());
                Thread.sleep(500);
                this.setProcessed(false);
                System.out.println("Number: " + numbers.pop());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class Result{
        private boolean processed;
        private int number;

        public Result(boolean processed, int number) {
            this.processed = processed;
            this.number = number;
        }

        public boolean isProcessed() {
            return processed;
        }

        public int getNumber() {
            return number;
        }
    }
}
