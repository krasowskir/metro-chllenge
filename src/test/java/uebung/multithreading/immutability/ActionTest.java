package uebung.multithreading.immutability;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

class ActionTest {

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
}