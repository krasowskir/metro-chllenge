package uebung.multithreading.syncAndLock;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriterDemoWithoutWaitingTime {

    private static final int NUMBER_READER_THREADS = 3;
    private volatile boolean done  = false;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private MyBallotBox data;

    public ReadWriterDemoWithoutWaitingTime() {
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


        public synchronized Set<Map.Entry<String, Integer>> getElements() {
//            System.out.println("thread entered getElements");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep((long)(Math.random() * 300));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Set<Map.Entry<String, Integer>> entries = this.votings.entrySet();
            notifyAll();
//            System.out.println("thread left getElements");
            return entries;
        }

        public synchronized void voteFor(int numbr) {
//            System.out.println("thread entered voteFor");
            Iterator<Map.Entry<String, Integer>> iter = this.votings.entrySet().iterator();
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
            try {
                Thread.sleep((long)(Math.random() * 300));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyAll();
//            System.out.println("thread left voteFor");
        }
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void doStruff(){
        for (int i = 0; i < NUMBER_READER_THREADS; i++){
            new Thread(() -> {
                while (!done){
                    this.data.getElements().forEach(e -> {
                        System.out.println("voting: " + e.getKey() + " value: " + e.getValue());
                    });
                }
            }).start();
        }

        Random rand = new Random();
        Thread writerThread = new Thread(() -> {
            while (!done){
                this.data.voteFor(rand.nextInt(3));
            }
        });
        writerThread.start();
    }
}
