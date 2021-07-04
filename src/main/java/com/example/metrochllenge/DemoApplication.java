package com.example.metrochllenge;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    public static void main(String[] args) {

        String sentence = "Hello Annagret Kramp Karrenbauer, wie gehts?"; //"Hello World in a frame";
        String delim = " ";
        String frameSymbol = "*";

        System.out.println(frameSentence(sentence, frameSymbol));
    }

    public static String frameSentence(String sentence, String frameSymbol) {
        List<String> tokensFromSentence = breakIntoTokens(sentence, " ");
        int maxAmountOfChars = calculateAmountOfCharactersInLongestWord(tokensFromSentence);
        return drawFrame(tokensFromSentence, maxAmountOfChars, frameSymbol);
    }

    public static List<String> breakIntoTokens(String fromSentence, String delimChar) {
        StringTokenizer strTok = new StringTokenizer(fromSentence, delimChar);

        //Möglichkeit 1
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(strTok.asIterator(),Spliterator.ORDERED), false)
                .map(Object::toString)
                .collect(Collectors.toList());

        //Möglichkeit 2
//        return Stream.iterate(strTok.asIterator(), w -> w.hasNext(), UnaryOperator.identity())
//                .map(Iterator::next)
//                .map(e -> e.toString())
//                .collect(Collectors.toList());
//        List<String> tokens = new ArrayList<>();
//
//        while (strTok.hasMoreTokens()) {
//            tokens.add(strTok.nextToken());
//        }
//        return tokens;
    }

    private static int calculateAmountOfCharactersInLongestWord(List<String> elements) {

        //Möglichkeit 1
        elements.stream()
                .map(e -> e.toCharArray().length)
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();

        Function<Integer, Integer> countChars = e -> e;

        //Möglichkeit 2
        elements.stream()
                .max(comparingInt(e -> e.toCharArray().length))
                .get();

        //Möglichkeit 3
        return elements
                .stream()
                .map(e -> e.toCharArray().length)
                .max(comparingInt(e -> (Integer)e))
                .get();

    }

    private static String drawFrame(List<String> tokens, int maxAmountOfChars, String frameSymbol) {
        StringBuilder strB = new StringBuilder();
        drawLine(maxAmountOfChars, frameSymbol, strB);

        strB.append(tokens.stream()
                .map(elem -> prefixAndSuffixTokens(elem, frameSymbol, maxAmountOfChars))
                .collect(Collectors.joining()));

        drawLine(maxAmountOfChars, frameSymbol, strB);
        return strB.toString();
    }

    private static void drawLine(int maxAmountOfChars, String frameSymbol, StringBuilder strB) {
        int extraChars = 4;
        strB.append(frameSymbol.repeat(maxAmountOfChars));
        strB.append(frameSymbol.repeat(extraChars));
        strB.append("\n");
    }

    private static String prefixAndSuffixTokens(String token, String frameSymbol, int maxAmountOfChars) {
        int lengthDiff = maxAmountOfChars - token.length();
        StringBuilder strB = new StringBuilder();
        strB.append(frameSymbol);
        strB.append(" ");
        strB.append(token);
        strB.append(" ".repeat(lengthDiff + 1));
        strB.append(frameSymbol);
        strB.append("\n");
        return strB.toString();
    }


}
