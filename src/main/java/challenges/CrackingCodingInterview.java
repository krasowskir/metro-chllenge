package challenges;

import java.nio.CharBuffer;
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
    Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palin­ drome.
    A palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters.
     The palindrome does not need to be limited to just dictionary words.
    EXAMPLE
    Input: Tact Coa
    Output: True (permutations: "taco cat", "atco eta", etc.)
     */
    public boolean challenge14(String text) {

        String textWithoutWhiteSpaces = text.replaceAll("\\s", "");
        char[] textAsCharArr = textWithoutWhiteSpaces.toCharArray();
        return digDeeper(textAsCharArr, null);
    }

    public boolean digDeeper(char[] textAsCharArr, char[] prefix) {

        int length = textAsCharArr.length;
        char firstChar = textAsCharArr[0];
        char[] restChars = null;
        if (length > 1) {
            restChars = Arrays.copyOfRange(textAsCharArr, 1, length);
        }

        if (length > 1) {
            for (int i = 0; i < length; i++) {
                char[] newPref = populatePrefix(prefix, firstChar);
                char[] tryPalindrome = new StringBuilder().append(newPref).append(restChars).toString().toCharArray();

                if (isPalindrome(tryPalindrome)) {
                    return true;
                } else {
                    if (digDeeper(restChars, newPref)) return true;
                }
                restChars = rotateChars(restChars);
            }
        } else {
            char[] newPref = populatePrefix(prefix, firstChar);
            char[] tryPalindrome = new StringBuilder().append(newPref).toString().toCharArray();
            return isPalindrome(tryPalindrome);
        }
        return false;
    }

    public char[] populatePrefix(char[] prefix, char firstChar) {
        if (prefix == null) {
            return new char[]{firstChar};
        } else {
            int len = prefix.length;
            char[] newPrefix = new char[len + 1];
            System.arraycopy(prefix, 0, newPrefix, 0, len);
            newPrefix[prefix.length] = firstChar;
            return newPrefix;
        }
    }

    public char[] rotateChars(char[] charArr) {
        int len = charArr.length;
        char[] tmpChar = new char[len];
        tmpChar[0] = charArr[len - 1];
        System.arraycopy(charArr, 0, tmpChar, 1, len - 1);
        return tmpChar;
    }

    public boolean isPalindrome(char[] text) {
        int len = text.length;
        for (int i = 0; i < len; i++) {
            if (text[i] != text[len - 1 - i]) {
                return false;
            }
        }
        System.out.println("isPalindrome: " + String.valueOf(text) + " -> " + true);
        return true;
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
            char nextChar = initializeNextChar(myIter);

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

    /*
    String Compression: Implement a method to perform basic string compression using the counts of repeated characters.
    For example, the string aabcccccaaa would become a2blc5a3. If the "compressed" string would not become smaller than
    the original string, your method should return the original string.
    You can assume the string has only uppercase and lowercase letters (a - z).
     */
    public String challenge16(String inputText){

        char[] textLetters = inputText.toCharArray();
        int textLength = inputText.length();
        ListIterator<Character> textIter = CharBuffer.wrap(inputText.toCharArray())
                .chars()
                .mapToObj(i -> (char)i)
                .collect(Collectors.toList()).listIterator();

        StringBuilder strB = new StringBuilder(textLength);

        while (textIter.hasNext()){
            char currentChar = textIter.next();
            char nextChar = initializeNextChar(textIter);
            int innerIndx = 0;

            if (currentChar == nextChar){
                while (textIter.hasNext()){
                    char tmpNextChar;
                    tmpNextChar = textIter.next();
                    if (currentChar == tmpNextChar){
                        innerIndx++;
                    } else {
                        textIter.previous();
                        break;
                    }
                }
            }
            strB.append(currentChar).append(innerIndx+1);
        }
        return strB.toString().length() >= inputText.length() ? inputText : strB.toString();

    }

    private char initializeNextChar(ListIterator<Character> textIter) {
        char nextChar;
        if (textIter.hasNext()) {
            nextChar = textIter.next();
            textIter.previous();
        } else {
            nextChar = '\0';
        }
        return nextChar;
    }

    /*
    Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
    write a method to rotate the image by 90 degrees. Can you do this in place?

    Matrix um 90° kippen besteht aus zwei Schritten!
        - matrix transponieren (Zeilen als Spalten schreiben (x mit y vertauschen))
        - Reihenfolge von den Spalten vertauschen
    */
    public String[][] challenge17(String[][] matrix){
        int yLen = matrix.length;
        int xLen = matrix[0].length;
        if (yLen != xLen){
            throw new RuntimeException("xlen is not equals to ylen");
        }
        String[][] transpMatr = transponiereMatrix(matrix);
        return reverseColumns(transpMatr);
    }

    public String[][] transponiereMatrix(String[][] matrix) {
        int len = matrix.length;
        String[][] transpMatr = new String[len][len];

        for (int i = 0; i < len; i++){
            for (int j = 0; j < len; j++){
                transpMatr[j][i] = matrix[i][j];
            }
        }
        return transpMatr;
    }

    public String[][] reverseColumns(String[][] fromMatrix){
        int len = fromMatrix.length;
        String[][] revMatr = new String[len][len];

        for (int i = 0; i < len; i++){
            for (int j = 0; j < len; j++){
                revMatr[i][len -1 -j] = fromMatrix[i][j];
            }
        }
        return revMatr;
    }
}
