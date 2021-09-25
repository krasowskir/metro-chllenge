package uebung.multithreading.immutability;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Action {

    void demoListAdd(List<String> list){
        System.out.println("list: " + list);
        try {
            for (String elem : list){
                System.out.println(elem);
                if (!list.contains("Four")){
                    System.out.println("adding four");
                    list.add("Four");
                }
            }
        } catch (Exception e){
            System.out.println(e.getClass().getName());
        }
        System.out.println("final list: " + list);
    }

    void doListRemove(List<String> list){
        try {
            System.out.println("list: " + list);
            for (String elem : list){
                System.out.println(elem);
                if (list.contains("Two")){
                    System.out.println("list remove two");
                    list.remove("Two");
                }
            }
        } catch (Exception e){
            System.out.println(e.getClass().getName());
        }
        System.out.println("final list: " + list);
    }

    void doListIterRemove(List<String> list){
        try {
            System.out.println("list: " + list);
            Iterator<String> iter = list.iterator();
            while (iter.hasNext()){
                String elem = iter.next();
                System.out.println(elem);
                if ("Two".equals(elem)){
                    iter.remove();
                }
            }
        } catch (Exception e){
            System.out.println(e.getClass().getName());
        }
        System.out.println("final list: " + list);
    }

    void removeIf(Collection<String> collection){
        System.out.println("collection: " + collection);
        System.out.println("removeIf...");
        collection.removeIf(e -> "Two".equals(e));
        System.out.println("final collection: " + collection);
    }
}
