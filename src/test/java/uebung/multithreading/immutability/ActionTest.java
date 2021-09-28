package uebung.multithreading.immutability;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

class ActionTest {

    Map createMap(){
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Zero");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        return map;
    }
    @Test
    void test_demoAdd() {
        Action immColTest = new Action();
        System.out.println("*********** ArrayList add ***********");
        immColTest.demoListAdd(new ArrayList<>(Arrays.asList("One", "Two", "Three")));

        System.out.println("*********** CopyOnWriteList add ***********");
        immColTest.demoListAdd(new CopyOnWriteArrayList<>(Arrays.asList("One", "Two", "Three")));
    }

    @Test
    void test_demoRemove() {
        Action immColTest = new Action();
        System.out.println("*********** ArrayList remove ***********");
        immColTest.doListRemove(new ArrayList<>(Arrays.asList("One", "Two", "Three")));

        System.out.println("*********** CopyOnWriteList remove ***********");
        immColTest.doListRemove(new CopyOnWriteArrayList<>(Arrays.asList("One", "Two", "Three")));
    }

    @Test
    void test_doIterRemove() {
        Action immColTest = new Action();
        System.out.println("*********** ArrayList doListIterRemove ***********");
        immColTest.doListIterRemove(new ArrayList<>(Arrays.asList("One", "Two", "Three")));

        System.out.println("*********** CopyOnWriteList doListIterRemove ***********");
        immColTest.doListIterRemove(new CopyOnWriteArrayList<>(Arrays.asList("One", "Two", "Three")));
    }

    @Test
    void test_removeIf_and_addIfAbsent() {
        Action immColTest = new Action();
        System.out.println("*********** ArrayList removeIf ***********");
        immColTest.removeIf(new ArrayList<>(Arrays.asList("One", "Two", "Three")));

        System.out.println("*********** CopyOnWriteList removeIf ***********");
        immColTest.removeIf(new CopyOnWriteArrayList<>(Arrays.asList("One", "Two", "Three")));

        System.out.println("*********** CopyOnWriteList addIfAbsent ***********");
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>(Arrays.asList("One", "Two", "Three"));
        list.addIfAbsent("Four");
        System.out.println("final list: " +list);
    }

    @Test
    void test_removeNavigableSet() {
        Action immColTest = new Action();
        System.out.println("*********** TreeSet navigableSetRemove ***********");
        immColTest.navigableSetRemove(new TreeSet<>(Arrays.asList(0, 1, 2, 3)));

        System.out.println("*********** ConcurrentSkipListSet navigableSetRemove ***********");
        immColTest.navigableSetRemove(new ConcurrentSkipListSet<>(Arrays.asList(0,1,2,3)));
    }

    @Test
    void test_navigableSetIterRemove() {
        Action immColTest = new Action();
        System.out.println("*********** TreeSet navigableSetIterRemove ***********");
        immColTest.navigableSetIterRemove(new TreeSet<>(Arrays.asList(0, 1, 2, 3)));

        System.out.println("*********** ConcurrentSkipListSet navigableSetIterRemove ***********");
        immColTest.navigableSetIterRemove(new ConcurrentSkipListSet<>(Arrays.asList(0,1,2,3)));
    }

    @Test
    void test_navigableSetRemoveIf() {
        Action immColTest = new Action();
        System.out.println("*********** TreeSet navigableSetIterRemoveIf ***********");
        immColTest.navigableSetIterRemoveIf(new TreeSet<>(Arrays.asList(0, 1, 2, 3)));

        System.out.println("*********** ConcurrentSkipListSet navigableSetIterRemoveIf ***********");
        immColTest.navigableSetIterRemoveIf(new ConcurrentSkipListSet<>(Arrays.asList(0,1,2,3)));
    }

    @Test
    void test_navigableSetAdd() {
        Action immColTest = new Action();
        System.out.println("*********** TreeSet navigableSetAdd ***********");
        immColTest.demoNavigableSetAdd(new TreeSet<>(Arrays.asList(0, 1, 2, 3)));

        System.out.println("*********** ConcurrentSkipListSet navigableSetAdd ***********");
        immColTest.demoNavigableSetAdd(new ConcurrentSkipListSet<>(Arrays.asList(0,1,2,3)));
    }

    @Test
    void test_demoMapPut() {
        Action immColTest = new Action();
        System.out.println("*********** HashMap demoMapPut ***********");
        immColTest.demoMapPut(createMap());

        System.out.println("*********** ConcurrentHashMap demoMapPut ***********");
        immColTest.demoMapPut(new ConcurrentHashMap<>(createMap()));

        System.out.println("*********** ConcurrentSkipListMap demoMapPut ***********");
        immColTest.demoMapPut(new ConcurrentSkipListMap<>(createMap()));
    }
}