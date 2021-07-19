package challenges;

import java.nio.CharBuffer;
import java.util.LinkedList;

public class OwnHashMap {

    private LinkedList<String>[] elemNodes;

    public OwnHashMap() {
        this.elemNodes = new LinkedList[15];
    }

    public void add(String key, String val){
        int hashCode = computeHash(key);
        if (elemNodes[hashCode] == null){
            elemNodes[hashCode] = new LinkedList<>();
            elemNodes[hashCode].addFirst(val);
        } else {
            elemNodes[hashCode].addLast(val);
        }
    }

    public String get(String key, int ind){
        int hash = computeHash(key);
        return elemNodes[hash].get(ind);
    }

    public LinkedList<String>[] getHashMap(){
        return elemNodes;
    }

    public int size(){
        return elemNodes.length;
    }

    private int computeHash(String text){
        int sumCode = CharBuffer.wrap(text.toCharArray())
                .chars()
                .mapToObj(c -> (char) c)
                .mapToInt(i -> i)
                .sum();
        return sumCode % elemNodes.length;
    }
}
