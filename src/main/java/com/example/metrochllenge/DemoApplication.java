package com.example.metrochllenge;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.StringTokenizer;
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

//        String sentence = Files.readString(Paths.get(new ClassPathResource("wiki.txt").getFile().getPath()));//"Hello Annagret Kramp Karrenbauer, wie gehts?";
        String sentence = "Hello World in a frame"; //"Here, we use a map to extract the artists’ names and then collect the Stream using Collectors.joining.\ This method is a convenience for building up strings from streams. It lets us provide a delimiter (which goes between elements), a prefix for our result, and a suffix for the result.";
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
        return frameSymbol.repeat(maxAmountOfChars + extraChars) + "\n";
    }

    private static String suffixTokens(String token, String frameSymbol, int maxAmountOfChars) {
        int lengthDiff = maxAmountOfChars - token.length();
        return token + " ".repeat(lengthDiff + 1) + frameSymbol + "\n";
    }
}
