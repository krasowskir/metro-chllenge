package com.example.metrochllenge;

import challenges.ReverseInt;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        ReverseInt challenge1 = new ReverseInt();
        int num = challenge1.reverse(-321); //1534236469, 123, 321, -321
        System.out.println("numb: " + num);
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
        tokens.stream()
                .collect(new StringCollector());

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
