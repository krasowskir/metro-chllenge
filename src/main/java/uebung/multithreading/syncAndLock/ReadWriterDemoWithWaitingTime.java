package uebung.multithreading.syncAndLock;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriterDemoWithWaitingTime {

    private static final int NUMBER_READER_THREADS = 3;
    private volatile boolean done  = false;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private MyBallotBox data;

    public ReadWriterDemoWithWaitingTime() {
        List<String> questionList = new ArrayList<>();
        questionList.add("Eminem");
        questionList.add("Governeur Richard");
        questionList.add("Dr. Dre");
        this.data = new MyBallotBox(questionList);
    }

    public class MyBallotBox {

        private HashMap<String, Integer> votings = new HashMap<>();

        public MyBallotBox(List<String> elements) {
            elements.forEach(e -> { this.votings.put(e, 0);});
        }


        public Set<Map.Entry<String, Integer>> getElements(){
            return this.votings.entrySet();
        }

        public void voteFor(int numbr){
            Iterator<Map.Entry<String, Integer>> iter = this.getElements().iterator();
            int i = 1;
            while (iter.hasNext()){
                Map.Entry<String, Integer> currentVal = iter.next();
                if (i == (numbr+1)){
                    int tmpVal = currentVal.getValue();
                    tmpVal += 1;
                    currentVal.setValue(tmpVal);
                }
                i++;
            }
        }
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void doStuff() {
        for (int i = 0; i < NUMBER_READER_THREADS; i++) {
            new Thread(() -> {
                while (!done) {
                    this.lock.readLock().lock();
                    try {
                        this.data.getElements().forEach(v -> {
                            System.out.println("voting: " + v.getKey() + " amount: " + v.getValue());
                        });
                    } finally {
                        this.lock.readLock().unlock();
                    }
                    try {
                        Thread.sleep((long)(Math.random() * 300));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Random rand = new Random();
        Thread writerThread = new Thread(() -> {
           while (!done){
               lock.writeLock().lock();
               try {
                   System.out.println("===== voting =====");
                this.data.voteFor((rand.nextInt(3)));
               } finally {
                   lock.writeLock().unlock();
               }
               try {
                   Thread.sleep((long)(Math.random()*300));
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        writerThread.start();
    }

}
