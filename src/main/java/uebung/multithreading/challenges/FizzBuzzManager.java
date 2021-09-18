package uebung.multithreading.challenges;

import java.util.Random;
import java.util.Stack;

public class FizzBuzzManager implements Runnable{

    private Stack<Integer> numbers;
    private Random rand = new Random();
    private volatile boolean isReady = false;

    DividibleBy fizz, buzz, fizzBuzz;
    public FizzBuzzManager() {
        this.numbers = new Stack<>();
        this.fizz = new Divider(3, numbers);
        this.buzz = new Divider(5, numbers);
        this.fizzBuzz = new SpecialDivider(numbers);
    }

    public synchronized void addNumber(int number){
        this.numbers.push(number);
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    private int generateRandomNumb(){
        return rand.nextInt(100);
    }
    @Override
    public void run() {
        Thread t1 = new Thread((Runnable)fizz);

        Thread t2 = new Thread((Runnable)buzz);
        new Thread((Runnable)fizzBuzz).start();
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!isReady){
            try {
                Thread.sleep(500);
                numbers.push(generateRandomNumb());
                System.out.println("size: " + numbers.size());
                Thread.sleep(2000);
//                numbers.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
