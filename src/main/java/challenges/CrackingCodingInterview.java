package challenges;

import uebung.multithreading.firstSteps.MeinRunnableThread;
import uebung.multithreading.firstSteps.MeinThread;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void testCharArrStream(){
        char[] textChars = "Hello World".toCharArray();

        Collector<Character, StringBuilder, StringBuilder> charColl = Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append
        );

        CharBuffer.wrap(textChars)
                .chars()
                .mapToObj(e -> (char)e)
                .collect(charColl);
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

    /*
    R�mov� Dups! Write code to remove duplicates from an unsorted linked list.
     */
    public LinkedList<String> challenge21(LinkedList<String> fromList){

        Collections.sort(fromList);
        ListIterator<String> iter = fromList.listIterator();

        while (iter.hasNext()){
            String currentNode = iter.next();
            String nextNode;
            if (iter.hasNext()){
                nextNode = iter.next();
                iter.previous();
            } else {
                nextNode = "";
            }

            if (currentNode.equals(nextNode)){
                int rollbIndx = 0;
                while (iter.hasNext()){
                    String tmpNext = iter.next();
                    if (!tmpNext.equals(currentNode)){
                        iter.previous();
                        break;
                    }
                    rollbIndx += 1;
                }
                rollbackAndRemove(iter, rollbIndx);
            }
        }

        return fromList;
    }

    private void rollbackAndRemove(ListIterator<String> iter, int to){
        for (int i = 0; i < to; i++){
           iter.previous();
           iter.remove();
        }
    }

    /*
    Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
     */
    public LinkedList<String> challenge22(LinkedList<String> fromList, int k){
        ListIterator<String> iter = fromList.listIterator(k);
        LinkedList<String> result = new LinkedList<>();
        Stream.iterate(iter, w -> w.hasNext(), UnaryOperator.identity())
                .map(indx -> indx.next())
                .forEach(elem -> result.add(elem));

        return result;
    }

    /*
    Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but the first and last node,
    not necessarily the exact middle) of a singly linked list, given only access to that node.

    EXAMPLE
    lnput:the node c from the linked list a->b->c->d->e->f
    Result: nothing is returned, but the new linked list looks like a->b->d->e- >f
     */
    public LinkedList<String> challenge23(LinkedList<String> fromList){
        int len = fromList.size();
        int indx = 1;
        boolean isEven = len % 2 == 0;
        ListIterator<String> iter = fromList.listIterator();

        while (iter.hasNext()){
            String currentNode = iter.next();
            if (isEven){
                if (indx == (len / 2) && iter.hasNext()){
                    iter.previous();
                    if (iter.hasPrevious()){
                        iter.remove();
                    } else {
                        iter.next();
                    }
                }
            } else {
                if (indx == ((len / 2)+1) && iter.hasPrevious()){
                    iter.next();
                    if (iter.hasNext()){
                        iter.previous();
                        iter.previous();
                        iter.remove();
                    } else {
                        iter.previous();
                    }
                }
            }
            indx += 1;
        }
        return fromList;
    }

    /*
    challenge24 but with an array. System.arraycopy is needed for array operations
     */
    public int[] challenge24(int[] fromArr) {
        int len = fromArr.length;
        for (int i = 1; i < len; i++) {
            if (fromArr[i - 1] > fromArr[i]) {
                fromArr = replaceVal(fromArr, i);
            }
        }
        return fromArr;
    }

    protected int[] replaceVal(int[] fromArr, int indx) {
        int val = fromArr[indx];
        for (int i = 0; i < indx; i++) {
            if (fromArr[i] >= val) {
                int tmp = fromArr[i];
                if (indx - i > 1) {
                    System.arraycopy(fromArr, i, fromArr, i + 1, indx - (i));
                }
                fromArr[i] = val;
                fromArr[i + 1] = tmp;
                System.arraycopy(fromArr, i + 2, fromArr, i + 2, fromArr.length - (indx + 1));
                return fromArr;
            }
        }
        return fromArr;
    }

    /*
    Partition: Write code to partition a linked list around a value x,
    such that all nodes less than x come before all nodes greater than or equal to x. If x is contained within the list,
    the values of x only need to be after the elements less than x (see below).
    The partition element x can appear anywhere in the "right partition";
    it does not need to appear between the left and right partitions.

    EXAMPLE
    Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1[partition=5]
    Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
     */
    public LinkedList<Integer> challenge24(LinkedList<Integer> fromList){
        //Insertion sort
        ListIterator<Integer> iter = fromList.listIterator();
        while (iter.hasNext()){
            int currentNode = iter.next();
            int nextNode;
            if (iter.hasNext()){
                nextNode = iter.next();
                iter.previous();
            } else {
                nextNode = Integer.MAX_VALUE;
            }

            if (currentNode > nextNode){
                iter.remove();
                iter = shiftNodeFromLeft(nextNode, fromList.listIterator(0));
            }
        }
        return fromList;
    }

    public ListIterator<Integer> shiftNodeFromLeft(int val, ListIterator<Integer> iterator){
        while (iterator.hasNext()){
            int currentVal = iterator.next();

            if (currentVal > val){
                iterator.previous();
                iterator.add(val);
                break;
            }
        }
        return iterator;
    }

    /*
    Sum Lists: You have two numbers represented by a linked list, where each node contains a single digit.The digits are stored in reverse order,
    such that the 1 's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.

    EXAMPLE
    Input:(7-> 1 -> 6) + (5 -> 9 -> 2). Thatis,617 + 295.
    Output:2 -> 1 -> 9.Thatis,912.

    FOLLOW UP
    Suppose the digits are stored in forward order. Repeat the above problem. EXAMPLE
    lnput:(6 -> 1 -> 7) + (2 -> 9 -> 5). That is,617 + 295.
    Output:9 -> 1 -> 2. Thatis,912.
     */
    public LinkedList<Integer> challenge25(LinkedList<Integer> num1, LinkedList<Integer> num2){
        Collector<Integer, StringBuilder, String> digitCollector = Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString
        );
        String number1 = Stream.iterate(num1.descendingIterator(), Iterator::hasNext, UnaryOperator.identity())
                .map(Iterator::next)
                .collect(digitCollector);

        String number2 = Stream.iterate(num2.descendingIterator(), Iterator::hasNext, UnaryOperator.identity())
                .map(Iterator::next)
                .collect(digitCollector);
        int result = Integer.parseInt(number1) + Integer.parseInt(number2);
        String reversed = new StringBuilder(String.valueOf(result)).reverse().toString();
        LinkedList<Integer> resultReversedList = new LinkedList<>();
        CharBuffer.wrap(reversed.toCharArray())
                .chars()
                .map(c -> (char)c)
                .map(i -> { resultReversedList.add(Character.digit(i, 10)); return i;})
                .boxed()
                .collect(Collectors.toList());
        return resultReversedList;
    }

    public LinkedList<Integer> challenge25_forward(LinkedList<Integer> num1, LinkedList<Integer> num2){
        Collector<Integer, StringBuilder, String> digitCollector = Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString
        );
        String number1 = Stream.iterate(num1.listIterator(), Iterator::hasNext, UnaryOperator.identity())
                .map(Iterator::next)
                .collect(digitCollector);

        String number2 = Stream.iterate(num2.listIterator(), Iterator::hasNext, UnaryOperator.identity())
                .map(Iterator::next)
                .collect(digitCollector);
        int result = Integer.parseInt(number1) + Integer.parseInt(number2);

        LinkedList<Integer> resultReversedList = new LinkedList<>();
        CharBuffer.wrap(String.valueOf(result).toCharArray())
                .chars()
                .map(c -> (char)c)
                .map(i -> { resultReversedList.add(Character.digit(i, 10)); return i;})
                .boxed()
                .collect(Collectors.toList());
        return resultReversedList;
    }

    /*
    Palindrome: Implement a function to check if a linked list is a palindrome.
     */
    public boolean challenge26_isPalindrome(LinkedList<Character> text){
        if (text.size() == 0){
            return false;
        }
        Iterator<Character> reverseIter = text.descendingIterator();
        Iterator<Character> iter = text.iterator();

        while (iter.hasNext()){
            char nextChar = iter.next();
            char revNextChar;
            if (reverseIter.hasNext()){
                revNextChar = reverseIter.next();
            } else {
                return false;
            }
            if (nextChar != revNextChar){
                return false;
            }
        }
        return true;
    }

    /*
    Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
    Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
    threshold. Implement a data structure SetOfStacks that mimics this. SetO-fStacks should be composed of several stacks
    and should create a new stack once the previous one exceeds capacity.

    SetOfStacks. push() and SetOfStacks. pop() should behave identically to a single stack (that is, pop() should return
    the same values as it would if there were just a single stack).

    Implement a function popAt(int index) which performs a pop operation on a specific sub-stack
     */
    public void challenge33(){

    }

    /*
    Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
     */
    public void challenge34(){

    }

    /*
    Sort Stack: Write a program to sort a stack such that the smallest items are on the top.
    You can use an additional temporary stack, but you may not copy the elements into any other data structure (such as an array).
     The stack supports the following operations: push, pop, peek, and isEmpty.
     */
    public Stack<Integer> challenge35(Stack<Integer> unsortedStack){
        Stack<Integer> sortedStack = new Stack<>();

        while (!unsortedStack.isEmpty()){
            int currentElem = unsortedStack.pop();
            if (sortedStack.isEmpty()){
                sortedStack.push(currentElem);
            } else if (currentElem > sortedStack.peek()){
                sortedStack = mergeWithSortedStack(unsortedStack, sortedStack, currentElem);
            } else {
                sortedStack.push(currentElem);
            }
        }

        while (!sortedStack.isEmpty()){
            System.out.println("elem: " + sortedStack.pop() + " with size: " + sortedStack.size());
        }
        return sortedStack;
    }

    Stack<Integer> mergeWithSortedStack(Stack<Integer> fromSt, Stack<Integer> toSt, int elem){
        int len = toSt.size();
        for (int i = 0; i < len; i++){
            fromSt.push(toSt.pop());
        }
        for (int j = 0; j < len; j++){
            if (elem < fromSt.peek()){
                toSt.push(fromSt.pop());
            } else {
                toSt.push(elem);
                toSt.push(fromSt.pop());
                for (int k = 0; k < len - j -1; k++){
                    toSt.push(fromSt.pop());
                }
                break;
            }
        }
        return toSt;
    }

    /*
    Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly"first in, first out" basis.
    People must adopt either the"oldest" (based on arrival time) of all animals at the shelter,
    or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type).
    They cannot select which specific animal they would like.
    Create the data structures to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog, and dequeueCat.
     You may use the built-in Linked list data structure.
     */
    public void challenge36(){

    }

    /*
    Insertion: You are given two 32-bit numbers, N and M, and two bit positions, i and j.
    Write a method to insert M into N such that M starts at bit j and ends at bit i.
    You can assume that the bits j through i have enough space to fit all of M. That is, if M = 10011,
    you can assume that there are at least 5 bits between j and i. You would not, for example, have j = 3 and i = 2,
    because M could not fully fit between bit 3 and bit 2.

    EXAMPLE
    Input: N 10000000000, M 10011, i 2, j 6
    Output:N = 10001001100
     */
    public void challenge51(int n, int m, int i, int j){
        String number_n = Integer.toBinaryString(n);
        System.out.println("my number: " + number_n);

    }

    public void testMultiThreading(){
        MeinRunnableThread thread1 = new MeinRunnableThread(5, "A");
        MeinRunnableThread thread2 = new MeinRunnableThread(5, "B");
        thread1.run();
        thread2.run();

        MeinThread thread3 = new MeinThread(5, "C");
        MeinThread thread4 = new MeinThread(5, "D");
        thread3.start();
        thread4.start();
        Thread thread5 = new Thread(new MeinRunnableThread(5, "E"));
        thread5.start();

        Thread threadInner = new Thread(new Runnable() {

        String msg = "F";
        int count = 5;
            @Override
            public void run() {
                while (count > 0) {
                    System.out.println(msg);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ie) {
                        System.out.println("IE");
                    }
                    count--;
                }
            }
        });
        threadInner.start();
        try {
            thread3.join();
        } catch (InterruptedException e){
            System.err.println(e);
        }
//        ThreadsDemo3 threadsDemo3 = new ThreadsDemo3("G", 5);

    }

    public static void main(String[] args){
//        ThreadsDemo3 threadsDemo3 = new ThreadsDemo3("G", 5);
        MeinThread thread3 = new MeinThread(5, "C");
        Thread myJoin = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("waiting to read");
                try {
                    System.in.read();
                } catch (IOException e){
                    System.err.println(e);
                }
                System.out.println("finished thread");
            }
        });
        System.out.println("Starting");
        myJoin.start();
        thread3.start();
        System.out.println("joingin");
        try {
            myJoin.join();
        } catch (InterruptedException e){
            System.err.println(e);
        }
        System.out.println("main finished");
    }

    public void testMultithreading3() throws InterruptedException {
        MeinThread thread3 = new MeinThread(5, "C");
        MeinThread thread4 = new MeinThread(5, "D");
        thread3.start();
        thread4.start();
        thread4.join(2000);
    }
}
