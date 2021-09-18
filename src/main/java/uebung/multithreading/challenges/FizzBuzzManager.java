package uebung.multithreading.challenges;

import java.util.Random;
import java.util.Stack;

public class FizzBuzzManager implements Runnable{

    private Stack<Integer> numbers;
    private Random rand = new Random();
    private volatile boolean isReady = false;

    public FizzBuzzManager() {
        this.numbers = new Stack<>();
    }

    public boolean isEmpty(){
        return numbers.isEmpty();
    }
    public void setReady(boolean ready) {
        isReady = ready;
    }

    public Stack<Integer> getNumbers() {
        return numbers;
    }

    public synchronized int readNumberFromStack(){
        return this.numbers.peek();
    }

    private int generateRandomNumb(){
        return rand.nextInt(100);
    }
    @Override
    public void run() {

        while (!isReady){
            try {
                Thread.sleep(500);
                numbers.push(generateRandomNumb());
                Thread.sleep(100);
                numbers.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
