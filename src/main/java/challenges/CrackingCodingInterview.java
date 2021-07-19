package challenges;

import java.nio.CharBuffer;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CrackingCodingInterview {

    /*
    Is Unique: Implement an algorithm to determine if a string has all unique characters.
    What if you cannot use additional data structures?
     */
    public void challenge11(){
        String testText = "abcdefghijklmnoppa";
        HashMap<Character, Integer> charCounter = new HashMap<>();
        for (Character c : testText.toCharArray()){
            int tmpVal = charCounter.getOrDefault(c, 0);
            charCounter.computeIfPresent(c,(a, b) -> ++b);
            charCounter.putIfAbsent(c, ++tmpVal);
        }
        Boolean isUnique = charCounter.entrySet().stream().noneMatch(elem -> elem.getValue() > 1);
        System.out.println(String.format("Text %s enthält nur einzigartige Zeichen: ", testText) + isUnique);
    }

    /*
    Check Permutation: Given two strings,write a method to decide if one is a permutation of the
    other.
     */
    public void challenge12(){
        String text1 = "abc"; //"abcdefgabddegghcba"; "bagdegabczyxdf"
        String text2 = "abbc"; //"zyxdegabcdefbag"; "def" -> false, "abbc" -> true, "cbcbbabc" -> true, zyxdegabcdefbag -> true (bagdegabczyxdf)

        Collector<Character, TreeSet<Character>, TreeSet<Character>> charCollector = Collector.of(
                TreeSet::new,
                TreeSet::add,
                (c,d) -> {
                    c.addAll(d);
                    return c;
                }

        );
        TreeSet<Character> text3 = CharBuffer
                .wrap(text2.toCharArray())
                .chars().mapToObj(c -> (char)c)
                .collect(charCollector);

        Boolean isPermutatuion = CharBuffer.wrap(text1.toCharArray())
                .chars()
                .mapToObj(c -> (char)c)
                .allMatch(text3::contains);

        System.out.println(String.format("is permutation of text: %s is contained in text: %s ?: %b", text2,text1, isPermutatuion));

    }

    /*
    URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient
    space at the end to hold the additional characters,and that you are given the "true" length of the string.
    (Note: If implementing in Java,please use a character array so that you can perform this operation in place.)
    EXAMPLE
    Input: "Mr John Smith ", 13 Output: "Mr%20John%20Smith"
     */
    public void challenge13(){
        String textWithWhiteSpaces = "Mr John Smith ";
        char[] textAsCharArr = textWithWhiteSpaces.stripLeading().stripTrailing().toCharArray();
        Collector<Character, StringBuilder, StringBuilder> charCollector = Collector.of(
                StringBuilder::new,
                (a,b) -> {
                    if (b == ' '){
                        a.append("%20");
                    } else {
                        a.append(b);
                    }
                },
                (c,d) -> {
                    c.append(d.toString());
                    return c;
                }
        );
//        String value = CharBuffer.wrap(textAsCharArr)
//                .chars()
//                .mapToObj(c -> (char)c)
//                .collect(charCollector).toString();

        String value = CharBuffer.wrap(textAsCharArr)
                .chars()
                .mapToObj(c -> (char) c)
                .reduce(new StringBuilder(), (a, b) -> {
                            if (b == ' ') {
                                a.append("%20");
                            } else {
                                a.append(b);
                            }
                            return a;
                        },
                        (c, d) -> {
                            c.append(d.toString());
                            return c;
                        }).toString();
        System.out.println(String.format("text: %s replaced whitespaces leads to text: %s", textWithWhiteSpaces, value));
    }

    public void testOwnHashMap(){
        OwnHashMap myMap = new OwnHashMap();
        myMap.add("richard", "testRich");
        myMap.add("waldemar", "telekom");
        myMap.add("lidia", "sellics");
        myMap.add("olga", "städtisches Krankenhaus");

        System.out.println(String.format("länge: %d | elem 1.1: %s",myMap.size(), myMap.get("waldemar", 1)));
    }

    /*
    One Away: There are three types of edits that can be performed on strings: insert a character, remove a character,
    or replace a character.
    Given two strings, write a function to check if they are one edit (or zero edits) away.

    EXAMPLE
    pale, ple -> true
    pales, pale -> true
    pale, bale -> true
    pale, bake -> false
     */
    public void challenge15(){
        String originalText = "palesse"; //[pale,bale] -> 1, [pale,bake] -> 2, [pale,ple] -> 1, [pales, pale] -> 1, [palesse, pale] -> 3,[paless, ale] -> 3
        String input = "ale";

        char[] origTextCharArr = originalText.toCharArray();
        int indx = 0;
        int amountOfChanges = 0;

        List<Character> textAsList = CharBuffer.wrap(input.toCharArray())
                .chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.toList());

        ListIterator<Character> myIter = textAsList.listIterator();
        StringBuilder stringBuilder = new StringBuilder(originalText.length());

        while (myIter.hasNext()){
            char currentChar = myIter.next();
            char nextChar;
            if (myIter.hasNext()){
                nextChar = myIter.next();
                myIter.previous();
            } else {
                nextChar = '\0';
            }

            if (currentChar != origTextCharArr[indx]){

                //deletion prale -> pale
                if (nextChar == origTextCharArr[indx]){
                    amountOfChanges++;
                    myIter.previous();
                    myIter.remove();
                }

                //adding ple -> pale
                else if (currentChar == origTextCharArr[indx+1]){
                    amountOfChanges++;
                    myIter.previous();
                    myIter.add(origTextCharArr[indx]);
                    //replacing bale -> pale
                } else if (nextChar == origTextCharArr[indx+1]){
                    amountOfChanges++;
                    myIter.previous();
                    myIter.remove();
                    myIter.add(origTextCharArr[indx]);
                }

            }
            indx += 1;
            myIter.previous();
            stringBuilder.append(myIter.next());
        }
        String adjustedText = stringBuilder.toString();

        amountOfChanges = handleLastChanges(originalText, amountOfChanges, adjustedText);
        System.out.println("Änderungen: " + amountOfChanges);
    }

    private int handleLastChanges(String originalText, int amountOfChanges, String adjustedText) {
        if (originalText.length() > adjustedText.length() + 1){
            amountOfChanges = amountOfChanges + originalText.length() - adjustedText.length();
        } else if (originalText.length() == adjustedText.length() +1){
            amountOfChanges += 1;
        }
        return amountOfChanges;
    }
}
