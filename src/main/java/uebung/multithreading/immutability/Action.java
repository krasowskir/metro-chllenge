package uebung.multithreading.immutability;

import java.util.*;

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

    void navigableSetRemove(NavigableSet<Integer> set){
        System.out.println("set: " + set);
        try {
            for (int i : set){
                System.out.println(i);
                System.out.println("calling set remove(2)...");
                set.remove(2);
            }
        } catch (Exception e){
            System.out.println(e.getClass().getName());
        }
        System.out.println(set);
    }

    void navigableSetIterRemove(NavigableSet<Integer> set){
        System.out.println("set: " + set);
        try {
            Iterator<Integer> iter = set.iterator();
            while (iter.hasNext()){
                Integer elem = (Integer)iter.next();
                System.out.println(elem);
                if (elem == 2){
                    System.out.println("calling remove(2)...");
                    iter.remove();
                }
            }
        } catch (Exception e){
            System.out.println(e.getClass().getName());
        }
        System.out.println(set);
    }

    void navigableSetIterRemoveIf(NavigableSet<Integer> set){
        System.out.println("set: " + set);
        try {
            for (int i : set){
                System.out.println(i);
                set.removeIf(elem -> elem == 2);
            }
        } catch (Exception e){
            System.out.println(e.getClass().getName());
        }
        System.out.println(set);
    }

    void demoNavigableSetAdd(NavigableSet<Integer> set){
        System.out.println("set: " + set);
        try {
            int m = set.stream().max(Comparator.naturalOrder()).get() +1;
            for (int i : set){
                System.out.println(i);
                System.out.println("calling set.add(" + m + ")");
                set.add(m++);
                if (m > 6){
                    break;
                }
            }
        } catch (Exception e){
            System.out.println(e.getClass().getName());
        }
        System.out.println(set);
    }

    void demoMapPut(Map<Integer, String> map){
        System.out.println("map: " + map);
        try {
            Set<Integer> keys = map.keySet();
            for (int i : keys){
                System.out.println(i);
                System.out.println("calling put(8)...");
                map.put(8, "Eight");

                System.out.println(i);
                System.out.println("calling put(8)...");
                map.put(8, "Eight");

                System.out.println(i);
                System.out.println("calling putIfAbsent(9)...");
                map.putIfAbsent(9, "Nine");

                System.out.println(i);
                System.out.println("calling putIfAbsent(9)...");
                map.putIfAbsent(9, "Nine");

                System.out.println("size: " + map.size());
                System.out.println("map: " + map);
            }
        } catch (Exception e){
            System.out.println(e.getClass().getName());
        }
    }
}
