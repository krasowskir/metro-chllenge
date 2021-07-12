package uebung;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class HashTableVsHashMap {

    public void testHasTableVsHashMap(){

        int[] numbers = {1,2,3,6,5,8,22,11,7,101};
        Arrays.sort(numbers);
        Arrays.binarySearch(numbers, 22);

        ArrayList<String> arrListText = new ArrayList<>();
        arrListText.add("hallo");
        arrListText.add(" ich");
        arrListText.add(" bin");
        arrListText.add(" richard!");

        String[] names = arrListText.toArray(new String[arrListText.size()]);
        for (String name : names){
            System.out.println("name konvertiert: " + name);
        }

        LinkedList<String> linkedListText = new LinkedList<>();
        linkedListText.add("hallo");
        linkedListText.add(" ich");
        linkedListText.add(" bin");
        linkedListText.add(" richard!");

        Arrays.sort(arrListText.toArray());
        Collections.sort(linkedListText);

        LinkedHashMap<String, Integer> hashMapText = new LinkedHashMap<>();
        Collections.sort(new ArrayList<>(hashMapText.values()));

        hashMapText.put("hallo", 0);
        hashMapText.put(" ich", 1);
        hashMapText.put(" bin", 2);
        hashMapText.put(" richard!", 3);

        HashSet<String> hashSetText = new HashSet<String>();
        hashSetText.add("hallo");
        hashSetText.add(" ich");
        hashSetText.add(" bin");
        hashSetText.add(" richard!");

        Hashtable<String, Integer> hashTableText = new Hashtable<>();
        hashTableText.put("hallo", 0);
        hashTableText.put(" ich", 1);
        hashTableText.put(" bin", 2);
        hashTableText.put(" richard!", 3);

//        for (Map.Entry<String, Integer> elem : hashMapText.entrySet()){
////            if (elem.getKey().equals(" bin")){
////                hashMapText.remove(elem.getKey());
////            }
////        }

        Iterator<Map.Entry<String, Integer>> hashMapIter = hashMapText.entrySet().iterator();
        while (hashMapIter.hasNext()){
            String keyElem = hashMapIter.next().getKey();
            if (keyElem.equals(" bin")){
                hashMapIter.remove();
            }
        }

//        for (Map.Entry<String, Integer> elem : hashMapText.entrySet()){
//            if (elem.getKey().equals(" bin")){
//                hashMapText.remove(elem.getKey());
//            }
//        }

        System.out.println("=== HashMap ===");
        hashMapText.forEach((a, b) -> System.out.println(a + " index: " + b));

//        for (String elem : arrListText){
//            if (elem == " bin"){
//                arrListText.remove(elem);
//            }
//        }
//        arrListText.forEach(System.out::println);

        System.out.println("=== Iterator ===");
        Iterator<String> arrIter = arrListText.listIterator();
        while (arrIter.hasNext()){
            String elem = arrIter.next();
            if (elem.equals(" bin")){
                arrIter.remove();
            }
        }

        Iterator<String> secondIterator = arrListText.listIterator();

        List<String> text = Stream
                .iterate(secondIterator, a -> a.hasNext(), UnaryOperator.identity())
                .map(a -> a.next())
                .peek(e -> System.out.println("iter: " + e))
                .collect(Collectors.toList());
//                .forEach(System.out::println);


        System.out.println("=== HashSet ===");
        for (Object elem : hashSetText.toArray()){
            if (elem.toString().equals(" bin")){
                hashSetText.remove(elem);
            }
        }

        hashSetText.forEach(e -> System.out.println("set: " + e));

        System.out.println("=== HashTable ===");
//        for (Map.Entry<String, Integer> elem : hashTableText.entrySet()){
//            if (elem.getKey().equals(" ich")){
//                hashTableText.remove(elem.getKey());
//            }
//        }
        Iterator<Map.Entry<String, Integer>> hashTableIter = hashTableText.entrySet().iterator();
        while (hashTableIter.hasNext()){
            String key = hashTableIter.next().getKey();
            if (key.equals(" ich")){
                hashTableIter.remove();
            }
        }
        hashTableText.forEach((a, b) -> System.out.println(a + " " + b));
    }
}
