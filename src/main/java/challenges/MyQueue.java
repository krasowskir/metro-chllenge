package challenges;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/*
    Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
 */
public class MyQueue implements Queue<String> {

    private Stack<String> st1;
    private Stack<String> st2;
    private int threshold = 3;

    public MyQueue() {
        this.st1 = new Stack<>();
        this.st2 = new Stack<>();
    }

    @Override
    public boolean add(String s) {
        if (st1.size() == threshold){
            rebalance();
        }
        return st1.add(s);
    }

    @Override
    public boolean offer(String s) {
        return false;
    }

    @Override
    public String remove() {
        if (st1.size() < 1){
            int len = Math.min(st2.size(), threshold);
            for (int i = 0; i < len; i++){
                st1.add(st2.pop());
            }
        }
        return st1.pop();
    }

    @Override
    public String poll() {
        return st2.pop();
    }

    @Override
    public String element() {
        if (st2.size() == 0){
            throw new RuntimeException("Queue is empty or no rebalancing has occured");
        } else {
            return st2.peek();
        }
    }

    @Override
    public String peek() {
        return st2.peek();
    }

    @Override
    public int size() {
        return st2.size();
    }

    @Override
    public boolean isEmpty() {
        return st1.isEmpty() && st2.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return st1.search(o) != -1 || st2.search(o) != -1;
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        st1.clear();
        st2.clear();
    }

    private void rebalance(){
        int len = Math.min(st1.size(), threshold);
        for (int i = 0; i < len; i++){
            st2.add(st1.pop());
        }
    }
}
