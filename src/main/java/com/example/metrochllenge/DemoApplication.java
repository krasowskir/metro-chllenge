package com.example.metrochllenge;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import uebung.GroupByAccumulator;
import uebung.Player;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Comparator.comparingInt;

/*
Write a function that takes a 2 strings in, a sentence and a frame symbol, and prints words of sentence in a rectangular frame
(one per line). For example ("Hello World in a frame", "*") gets printed as:
*********
* Hello *
* World *
* in    *
* a     *
* frame *
*********
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {

        LeetCode leetCodeChallenge = new LeetCode();
//        ReverseInt challenge1 = new ReverseInt();
//        int num = challenge1.reverse(-321); //1534236469, 123, 321, -321
//        System.out.println("numb: " + num);
//        List<List<Integer>> liste = leetCodeChallenge.subsetsWithDup(new int[]{1,2,3});
//        for (List<Integer> elem : liste){
//            System.out.println("subset: " + elem.toString());
//        }

        //String sentence = Files.readString(Paths.get(new ClassPathResource("wiki.txt").getFile().getPath()));//"Hello Annagret Kramp Karrenbauer, wie gehts?";
//        String sentence = "Hello World in a frame"; //"Here, we use a map to extract the artists’ names and then collect the Stream using Collectors.joining.\ This method is a convenience for building up strings from streams. It lets us provide a delimiter (which goes between elements), a prefix for our result, and a suffix for the result.";
//        String delim = " ";
//        String frameSymbol = "*";
//
//        System.out.println(frameSentence(sentence, frameSymbol));

//        MyLinkedListExercise uebung1 = new MyLinkedListExercise();
//        uebung1.testLinkedList();

//        AddToNumbers uebung2 = new AddToNumbers();
        //System.out.println("Knoten: " + uebung2.testListNode());
//        LongestSubstring uebung3 = new LongestSubstring();
//        String inputText = "dadf"; //pwwkew -> 3, abcabcbb -> 3, bbbbb -> 1, dadf -> 3, "abcabcbb", "ohvhjdml" -> 6
//        System.out.println("longest substring of " + inputText + " is: " + uebung3.lengthOfLongestSubstring(inputText));

//        HashTableVsHashMap uebung4 = new HashTableVsHashMap();
//        uebung4.testHasTableVsHashMap();

//        MultiDimensionalArray multiDimArrUebung = new MultiDimensionalArray();
//        multiDimArrUebung.testMultiDimensionalArrays();

//        SquareCalculator uebung5 = new SquareCalculator();
//        uebung5.calculateSquares();

//        ZigZag uebung6 = new ZigZag();
//        uebung6.convertCharArr(new char[][]{{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}});
//        System.out.println("ZigZag text: " + "SUPERGUT -> " + uebung6.convert("SUPERGUT", 3));

        LinkedList<Player> players = new LinkedList<>(Arrays.asList(
                new Player("lidia", 33, new String[]{"Daugavpils", "Altenberg", "Pirna", "Dublin", "Berlin"}, "Daugavpils"),
                new Player("waldemar", 28, new String[]{"Daugavpils", "Altenberg", "Dresden"}, "Daugavpils"),
                new Player("richard", 30, new String[]{"Daugavpils", "Altenberg", "Dresden", "Bonn", "Berlin"}, "Daugavpils"))
        );

        Map<Object, Map<String, List<Player>>> gruppiertePlayer = players.stream()
                .collect(Collectors.groupingBy(Player::getBirthPlace, new GroupByAccumulator<Player, String>()));

        System.out.println("end");
    }

    public static String frameSentence(String sentence, String frameSymbol) {
        List<String> tokensFromSentence = breakIntoTokens(sentence, " ");
        int maxAmountOfChars = calculateAmountOfCharactersInLongestWord(tokensFromSentence);

        return drawFrame(tokensFromSentence, maxAmountOfChars, frameSymbol);
    }

    public static List<String> breakIntoTokens(String fromSentence, String delimChar) {
        StringTokenizer strTok = new StringTokenizer(fromSentence, delimChar);
        //Möglichkeit 1
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(strTok.asIterator(), Spliterator.ORDERED), false)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    private static int calculateAmountOfCharactersInLongestWord(List<String> elements) {
        //Möglichkeit 3
        return elements
                .stream()
                .map(e -> e.toCharArray().length)
                .max(comparingInt(e -> e))
                .get();

    }

    private static String drawFrame(List<String> tokens, int maxAmountOfChars, String frameSymbol) {
        String prefix = frameSymbol + " ";

        return drawLine(maxAmountOfChars, frameSymbol) +
                tokens.stream()
                        .map(e -> suffixTokens(e, frameSymbol, maxAmountOfChars))
                        .map(e -> prefix + e)
                        .collect(Collectors.joining()) +
                drawLine(maxAmountOfChars, frameSymbol);
    }

    private static String drawLine(int maxAmountOfChars, String frameSymbol) {
        int extraChars = 4;
        Map<String, Integer> alters = new HashMap<>();
        alters.put("lidia", 33);
        alters.put("richard", 30);
        alters.put("waldemar", 28);

        alters.forEach((key, val) -> System.out.println("key: " + key + " - value: " + val));

        alters.computeIfPresent("ludmlia", (key, val) -> val + 12 );
        return frameSymbol.repeat(maxAmountOfChars + extraChars) + "\n";
    }

    private static String suffixTokens(String token, String frameSymbol, int maxAmountOfChars) {
        int lengthDiff = maxAmountOfChars - token.length();
        return token + " ".repeat(lengthDiff + 1) + frameSymbol + "\n";
    }
}
