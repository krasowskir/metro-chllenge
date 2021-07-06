package uebung;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MyLinkedListExercise {

    public void testLinkedList(){
        LinkedList<Player> players = new LinkedList<>(Arrays.asList(
                new Player("lidia", 33, new String[]{"Daugavpils", "Altenberg", "Pirna", "Dublin", "Berlin"}),
                new Player("waldemar", 28, new String[]{"Daugavpils", "Altenberg", "Dresden"}),
                new Player("richard", 30, new String[]{"Daugavpils", "Altenberg", "Dresden", "Bonn", "Berlin"}))
        );


        players.stream()
                .map(player -> {System.out.println("SPieler: " + player.toString()); return player;})
                .collect(Collectors.toList());

        StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(players.iterator(), Spliterator.ORDERED), false);


        List<String> reversedPlayerList = Stream.iterate(players.descendingIterator(), Iterator::hasNext, UnaryOperator.identity())
                .map(playerIterator -> playerIterator.next())
                .map(player -> player.toString())
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println("=== final result ===");
        System.out.println(players.peekLast());
        //System.out.println(reversedPlayerList.toString());

    }
}
