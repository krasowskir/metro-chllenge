package uebung.structures;

import uebung.Player;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MyLinkedListExercise {

    public void testLinkedList(){
        LinkedList<Player> players = new LinkedList<>(Arrays.asList(
                new Player("lidia", 33, new String[]{"Daugavpils", "Altenberg", "Pirna", "Dublin", "Berlin"}, "Daugavpils"),
                new Player("waldemar", 28, new String[]{"Daugavpils", "Altenberg", "Dresden"}, "Daugavpils"),
                new Player("richard", 30, new String[]{"Daugavpils", "Altenberg", "Dresden", "Bonn", "Berlin"}, "Daugavpils"))
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

        System.out.println(players.stream()
                .map(Player::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList()));

        Comparator<Player> countAlter = Comparator.comparing(Player::getAlter);

        System.out.println("höchstes Alter: " + players.stream()
                .reduce((acc, player) -> countAlter.compare(acc, player) >= 0 ? acc : player)
                .orElseThrow(() -> new RuntimeException()));

        Pattern spacePattern = Pattern.compile(",");
        System.out.println("zusammengesetzte Liste: " + players.stream()
                .map(Player::getName)
                .flatMap(p -> spacePattern.splitAsStream(p))
                .collect(Collectors.toList()));

        Comparator<Player> compareAlter = Comparator.comparingInt(Player::getAlter);

        Comparator<String> compareLengthName = Comparator.comparingInt(e -> e.toCharArray().length);

        System.out.println("ältester Spieler: " + players.stream()
                .reduce((p1, p2) -> compareAlter.compare(p1, p2) >= 0? p1 : p2)
                .get());

        System.out.println("Spieler mit längstem Namen " +players.stream()
                .map(Player::getName)
                .max(compareLengthName)
                .get());

        System.out.println("Namen nach Anzahl ihres Auftretens: " +Arrays.stream(new String[]{"John", "Paul", "George", "John", "Paul", "John"})
                .collect(Collectors.groupingBy(e -> e, Collectors.counting())));

        System.out.println("Durchschnittsalter: " + players.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.averagingInt(e -> e.getAlter())))); //, Collectors.maxBy(Comparator.comparingInt(e -> (int)e)

        int[] ar1 = {1,2};
        int[] ar2 = {3,4};

        int[] concatedArray = IntStream.concat(Arrays.stream(ar1), Arrays.stream(ar2)).toArray();
        IntStream.of(concatedArray)
                .peek(i -> System.out.print(i))
                .max();

    }


}
