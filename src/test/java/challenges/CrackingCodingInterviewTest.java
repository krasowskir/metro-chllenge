package challenges;

import org.junit.jupiter.api.Test;
import uebung.multithreading.challenges.Challenges;
import uebung.multithreading.aufgaben.Tasks;
import uebung.multithreading.firstSteps.MeinStoppableThread;
import uebung.multithreading.firstSteps.MeinThread;
import uebung.multithreading.syncAndLock.*;
import uebung.multithreading.waitAndNotify.Consumer;
import uebung.multithreading.waitAndNotify.MyThing;
import uebung.multithreading.waitAndNotify.Producer;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CrackingCodingInterviewTest {

    @Test
    void challenge16_aabcccccaaa() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();

        String inputText = "aabcccccaaa";
        String result = testClass.challenge16(inputText);
        System.out.println("result: " + inputText + " -> " + result);
        assert result.equals("a2b1c5a3");
    }

    @Test
    void challenge16_aaaaaabbbc() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();

        String inputText = "aaaaaabbbc";
        String result = testClass.challenge16(inputText);
        System.out.println("result: " + inputText + " -> " + result);
        assert result.equals("a6b3c1");
    }

    @Test
    void challenge16_abcd() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();

        String inputText = "abcd";
        String result = testClass.challenge16(inputText);
        System.out.println("result: " + inputText + " -> " + result);
        assert result.equals("abcd");
    }

    @Test
    void challenge16_aabbbcddeefffffgfhh() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();

        String inputText = "aabbbcddeefffffgfhh";
        String result = testClass.challenge16(inputText);
        System.out.println("result: " + inputText + " -> " + result);
        assert result.equals("a2b3c1d2e2f5g1f1h2");
    }

    @Test
    void challenge16_aabccdde() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();

        String inputText = "aabccdde";
        String result = testClass.challenge16(inputText);
        System.out.println("result: " + inputText + " -> " + result);
        assert result.equals("aabccdde");
    }

    @Test
    void challenge17_transponiereMatri() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        String[][] matrix = {
                {"A","B","C"},
                {"D","E","F"},
                {"G","H","I"}
        };
        String[][] transpMatr = testClass.transponiereMatrix(matrix);
        assert transpMatr[0][0].equals("A");
        assert transpMatr[0][1].equals("D");
        assert transpMatr[0][2].equals("G");

        assert transpMatr[1][0].equals("B");
        assert transpMatr[1][1].equals("E");
        assert transpMatr[1][2].equals("H");

        assert transpMatr[2][0].equals("C");
        assert transpMatr[2][1].equals("F");
        assert transpMatr[2][2].equals("I");
    }

    @Test
    void challenge17_revMatr() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        String[][] matrix = {
                {"A","B","C"},
                {"D","E","F"},
                {"G","H","I"}
        };
        String[][] revMatr = testClass.reverseColumns(matrix);
        assert revMatr[0][0].equals("C");
        assert revMatr[0][1].equals("B");
        assert revMatr[0][2].equals("A");

        assert revMatr[1][0].equals("F");
        assert revMatr[1][1].equals("E");
        assert revMatr[1][2].equals("D");

        assert revMatr[2][0].equals("I");
        assert revMatr[2][1].equals("H");
        assert revMatr[2][2].equals("G");
    }

    @Test
    void challenge17_test() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        String[][] matrix = {
                {"A","B","C","X"},
                {"D","E","F","Y"},
                {"G","H","I","Z"},
                {"J","K","L","M"}
        };
        String[][] rotMatr = testClass.challenge17(matrix);
        int len = rotMatr.length;
        for (int i = 0; i < len; i++){
            System.out.println();

            for (int j = 0; j < len; j++){
                System.out.print(rotMatr[i][j]);
            }
        }
    }

    @Test
    void challenge14_test_rotateChars() {
        CrackingCodingInterview testClass =new CrackingCodingInterview();
        char[] testText = "Richard".toCharArray();
        char[] result = testClass.rotateChars(testText);
        for (int i = 0; i < 3; i++){
            result = testClass.rotateChars(result);
        }
        System.out.print("rotatet text: " + String.valueOf(result));
    }

    @Test
    void challenge14_test_isPalindrome() {
        CrackingCodingInterview testClass =new CrackingCodingInterview();
        char[] testText = "Richard".toCharArray();
        System.out.println(String.format("text: %s", String.valueOf(testText)) + " is palindrome: " + testClass.isPalindrome(testText));

        char[] testText2 = "abccba".toCharArray();
        System.out.println(String.format("text: %s", String.valueOf(testText2)) + " is palindrome: " + testClass.isPalindrome(testText2));

        char[] testText3 = "abcccba".toCharArray();
        System.out.println(String.format("text: %s", String.valueOf(testText3)) + " is palindrome: " + testClass.isPalindrome(testText3));

        char[] testText4 = "abcba".toCharArray();
        System.out.println(String.format("text: %s", String.valueOf(testText4)) + " is palindrome: " + testClass.isPalindrome(testText4));

    }

    @Test
    void challenge14_digDeeper_aba() {
        CrackingCodingInterview testClass =new CrackingCodingInterview();
        String text = "aba";
        System.out.println(String.format("text: %s", text) + " is palindrome: " + testClass.challenge14(text));
        assert testClass.challenge14(text);
    }

    @Test
    void challenge14_digDeeper_abba() {
        CrackingCodingInterview testClass =new CrackingCodingInterview();
        String text = "abba";
        System.out.println(String.format("text: %s", text) + " is palindrome: " + testClass.challenge14(text));
        assert testClass.challenge14(text);
    }

    @Test
    void challenge14_digDeeper_abca() {
        CrackingCodingInterview testClass =new CrackingCodingInterview();
        String text = "abca";
        System.out.println(String.format("text: %s", text) + " is palindrome: " + testClass.challenge14(text));
        assert !testClass.challenge14(text);
    }

    @Test
    void challenge14_digDeeper_aabbccdddccbbaa() {
        CrackingCodingInterview testClass =new CrackingCodingInterview();
        String text = "aabbccdddccbbaa";
        System.out.println(String.format("text: %s", text) + " is palindrome: " + testClass.challenge14(text));
        assert testClass.challenge14(text);
    }
    @Test
    void challenge14_digDeeper_baba() {
        CrackingCodingInterview testClass =new CrackingCodingInterview();
        String text = "baba";
        System.out.println(String.format("text: %s", text) + " is palindrome: " + testClass.challenge14(text));
        assert testClass.challenge14(text);
    }

    @Test
    void challenge14_digDeeper_cababbccdaaddcb() {
        CrackingCodingInterview testClass =new CrackingCodingInterview();
        String text = "cababbccdaaddcb";
        System.out.println(String.format("text: %s", text) + " is palindrome: " + testClass.challenge14(text));
        assert testClass.challenge14(text);
    }

    @Test
    void challenge14_digDeeper_acbba() {
        CrackingCodingInterview testClass =new CrackingCodingInterview();
        String text = "acbba";
        System.out.println(String.format("text: %s", text) + " is palindrome: " + testClass.challenge14(text));
        assert testClass.challenge14(text);
    }

    @Test
    void challenge14_digDeeper_taco_cat() {
        CrackingCodingInterview testClass =new CrackingCodingInterview();
        String text = "taco cat";
        System.out.println(String.format("text: %s", text) + " is palindrome: " + testClass.challenge14(text));
        assert testClass.challenge14(text);
    }

    @Test
    void challenge14_digDeeper_atco_eta() {
        CrackingCodingInterview testClass =new CrackingCodingInterview();
        String text = " atco eta";
        System.out.println(String.format("text: %s", text) + " is palindrome: " + testClass.challenge14(text));
        assert !testClass.challenge14(text);
    }

    @Test
    void test_challenge21_unique() {
        CrackingCodingInterview challenge21 = new CrackingCodingInterview();
        LinkedList<String> fromList = new LinkedList<>(Arrays.asList("Richard", "wie", "hast", "du","heute", "geschlafen"));
        LinkedList<String> result = challenge21.challenge21(fromList);
        result.stream()
                .forEachOrdered(elem -> System.out.println(elem));
    }

    @Test
    void test_challenge21_doubles() {
        CrackingCodingInterview challenge21 = new CrackingCodingInterview();
        LinkedList<String> fromList = new LinkedList<>(Arrays.asList("Richard", "wie", "hast", "du","heute", "geschlafen", "hast", "du", "das", "auch", "wie", "du", "gelöst"));
        LinkedList<String> result = challenge21.challenge21(fromList);
        result.stream()
                .forEachOrdered(elem -> System.out.println(elem));
    }


    @Test
    void test_challenge22_unique() {
        CrackingCodingInterview challenge22 = new CrackingCodingInterview();
        LinkedList<String> fromList = new LinkedList<>(Arrays.asList("Richard", "wie", "hast", "du","heute", "geschlafen"));
        LinkedList<String> result = challenge22.challenge22(fromList, 3);
        result.stream()
                .forEachOrdered(elem -> System.out.println(elem));
    }

    @Test
    void test_challenge22_double() {
        CrackingCodingInterview challenge22 = new CrackingCodingInterview();
        LinkedList<String> fromList = new LinkedList<>(Arrays.asList("Richard", "wie", "hast", "du","heute", "geschlafen", "hast", "du", "das", "auch", "wie", "du", "gelöst"));
        LinkedList<String> result = challenge22.challenge22(fromList, 5);
        result.stream()
                .forEach(elem -> System.out.println(elem));
    }

    @Test
    void test_challenge23_unique() {
        CrackingCodingInterview challenge23 = new CrackingCodingInterview();
        LinkedList<String> fromList = new LinkedList<>(Arrays.asList("Richard", "wie", "hast", "du","heute", "geschlafen"));
        LinkedList<String> result = challenge23.challenge23(fromList);
        result.stream()
                .forEach(elem -> System.out.println(elem));
    }

    @Test
    void test_challenge23_tooSmall() {
        CrackingCodingInterview challenge23 = new CrackingCodingInterview();
        LinkedList<String> fromList = new LinkedList<>(Arrays.asList("Richard", "wie"));
        LinkedList<String> result = challenge23.challenge23(fromList);
        result.stream()
                .forEach(elem -> System.out.println(elem));
    }

    @Test
    void test_challenge23_long() {
        CrackingCodingInterview challenge23 = new CrackingCodingInterview();
        LinkedList<String> fromList = new LinkedList<>(Arrays.asList("Richard", "wie", "hast", "du","heute"));
        LinkedList<String> result = challenge23.challenge23(fromList);
        result.stream()
                .forEach(elem -> System.out.println(elem));
    }

    @Test
    void test_challenge23_longest() {
        CrackingCodingInterview challenge23 = new CrackingCodingInterview();
        LinkedList<String> fromList = new LinkedList<>(Arrays.asList("Richard", "wie", "hast", "du","heute", "geschlafen", "hast", "du", "das", "auch", "wie", "du", "gelöst"));
        LinkedList<String> result = challenge23.challenge23(fromList);
        result.stream()
                .forEach(elem -> System.out.println(elem));
    }

    @Test
    void test_challenge24_sorted() {
        CrackingCodingInterview challenge24 = new CrackingCodingInterview();
        LinkedList<Integer> fromList = new LinkedList<>(Arrays.asList(2,3,4,5,8,12,88,114, 150));
        LinkedList<Integer> result = challenge24.challenge24(fromList);
        List<Integer> sortedRes = result.stream()
                .peek(System.out::println)
                .collect(Collectors.toList());
        assert sortedRes.equals(Arrays.asList(2,3,4,5,8,12,88,114, 150));
    }

    @Test
    void test_challenge24_NotSorted() {
        CrackingCodingInterview challenge24 = new CrackingCodingInterview();
        LinkedList<Integer> fromList = new LinkedList<>(Arrays.asList(4,2,3,8,5,8,12,88,5,150));
        LinkedList<Integer> result = challenge24.challenge24(fromList);
        List<Integer> sortedRes = result.stream()
                .peek(System.out::println)
                .collect(Collectors.toList());
        assert sortedRes.equals(Arrays.asList(2, 3, 4, 5, 5, 8, 8, 12, 88, 150));
    }

    @Test
    void test_challenge24_longer_notSorted() {
        CrackingCodingInterview challenge24 = new CrackingCodingInterview();
        LinkedList<Integer> fromList = new LinkedList<>(Arrays.asList(4,2,10,1,3,8,5,8,7,7,12,88,5,150));
        LinkedList<Integer> result = challenge24.challenge24(fromList);
        List<Integer> sortedRes = result.stream()
                .peek(System.out::println)
                .collect(Collectors.toList());
        assert sortedRes.equals(Arrays.asList(1,2, 3, 4, 5, 5, 7,7, 8, 8, 10, 12, 88, 150));
    }

    @Test
    void test_challenge24_arr() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        int[] tmpArr = testClass.challenge24(new int[]{4,2,3,8,5,8,12,88,5,150});
        System.out.println("erg arr" + Arrays.toString(tmpArr));
        assert Arrays.equals(tmpArr, new int[]{2, 3, 4, 5, 5, 8, 8, 12, 88, 150});
    }

    @Test
    void test_challenge24_arr_another() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        int[] tmpArr = testClass.challenge24(new int[]{4,2,10,1,3,8,5,8,7,7,12,88,5,150});
        System.out.println("erg arr" + Arrays.toString(tmpArr));
        assert Arrays.equals(tmpArr, new int[]{1,2, 3, 4, 5, 5, 7,7, 8, 8, 10, 12, 88, 150});
    }

    @Test
    void test_challenge24_arr_reversed() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        int[] tmpArr = testClass.challenge24(new int[]{150,5,88,12,8,5,8,3,2,4});
        System.out.println("erg arr" + Arrays.toString(tmpArr));
        assert Arrays.equals(tmpArr, new int[]{2, 3, 4, 5, 5, 8, 8, 12, 88, 150});
    }

    @Test
    void test_challenge25_happyPath() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        LinkedList<Integer> number1 = new LinkedList<>(Arrays.asList(2,3,1));
        LinkedList<Integer> number2 = new LinkedList<>(Arrays.asList(4,6,6));

        LinkedList<Integer> sum = testClass.challenge25(number1, number2);
        Collector<Character, LinkedList<Integer>, LinkedList<Integer>> charToLkListCollector = Collector.of(
                LinkedList::new,
                (a,b) -> a.add(Character.digit(b,10)),
                (c,d) -> c
        );

        LinkedList<Integer> endResult = Stream.iterate(sum.descendingIterator(), i -> i.hasNext(), UnaryOperator.identity())
                .map(i -> i.next())
                .map(i -> Character.forDigit(i,10))
                .collect(charToLkListCollector);
        ListIterator<Integer> iterator = endResult.listIterator();
        Stream.iterate(iterator, iterator1 -> iterator1.hasNext(), UnaryOperator.identity())
                .map(i -> i.next())
                .peek(num -> System.out.println(num))
                .collect(Collectors.toList());
        assert endResult.equals(new LinkedList<>(Arrays.asList(7, 9, 6)));
    }

    @Test
    void test_challenge25_zeroAdd() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        LinkedList<Integer> number1 = new LinkedList<>(Arrays.asList(0));
        LinkedList<Integer> number2 = new LinkedList<>(Arrays.asList(1,1,1));

        LinkedList<Integer> sum = testClass.challenge25(number1, number2);
        Collector<Character, LinkedList<Integer>, LinkedList<Integer>> charToLkListCollector = Collector.of(
                LinkedList::new,
                (a,b) -> a.add(Character.digit(b,10)),
                (c,d) -> c
        );

        LinkedList<Integer> endResult = Stream.iterate(sum.descendingIterator(), i -> i.hasNext(), UnaryOperator.identity())
                .map(i -> i.next())
                .map(i -> Character.forDigit(i,10))
                .collect(charToLkListCollector);
        ListIterator<Integer> iterator = endResult.listIterator();
        Stream.iterate(iterator, iterator1 -> iterator1.hasNext(), UnaryOperator.identity())
                .map(i -> i.next())
                .peek(num -> System.out.println(num))
                .collect(Collectors.toList());
        assert endResult.equals(new LinkedList<>(Arrays.asList(1, 1, 1)));
    }

    @Test
    void test_challenge25_taskVal() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        LinkedList<Integer> number1 = new LinkedList<>(Arrays.asList(7,1,6));
        LinkedList<Integer> number2 = new LinkedList<>(Arrays.asList(5,9,2));

        LinkedList<Integer> sum = testClass.challenge25(number1, number2);
        Collector<Character, LinkedList<Integer>, LinkedList<Integer>> charToLkListCollector = Collector.of(
                LinkedList::new,
                (a,b) -> a.add(Character.digit(b,10)),
                (c,d) -> c
        );

        LinkedList<Integer> endResult = Stream.iterate(sum.descendingIterator(), Iterator::hasNext, UnaryOperator.identity())
                .map(Iterator::next)
                .map(i -> Character.forDigit(i,10))
                .collect(charToLkListCollector);

        Stream.iterate(endResult.listIterator(), ListIterator::hasNext, UnaryOperator.identity())
                .map(ListIterator::next)
                .peek(System.out::println)
                .collect(Collectors.toList());

        assert sum.equals(new LinkedList<>(Arrays.asList(2, 1, 9)));
        assert endResult.equals(new LinkedList<>(Arrays.asList(9, 1, 2)));
    }

    @Test
    void test_challenge25_forward() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        LinkedList<Integer> number1 = new LinkedList<>(Arrays.asList(6,1,7));
        LinkedList<Integer> number2 = new LinkedList<>(Arrays.asList(2,9,5));

        LinkedList<Integer> sum = testClass.challenge25_forward(number1, number2);
        Collector<Character, LinkedList<Integer>, LinkedList<Integer>> charToLkListCollector = Collector.of(
                LinkedList::new,
                (a,b) -> a.add(Character.digit(b,10)),
                (c,d) -> c
        );

        LinkedList<Integer> endResult = Stream.iterate(sum.descendingIterator(), Iterator::hasNext, UnaryOperator.identity())
                .map(Iterator::next)
                .map(i -> Character.forDigit(i,10))
                .collect(charToLkListCollector);

        Stream.iterate(endResult.listIterator(), ListIterator::hasNext, UnaryOperator.identity())
                .map(ListIterator::next)
                .peek(System.out::println)
                .collect(Collectors.toList());

        assert sum.equals(new LinkedList<>(Arrays.asList(9, 1, 2)));
    }

    @Test
    void test_challenge26_palindr() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        LinkedList<Character> text = new LinkedList<>(Arrays.asList('a','b','b','c','d','d','c','b','b','a'));
        assert testClass.challenge26_isPalindrome(text);
    }

    @Test
    void test_challenge26_noPal() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        LinkedList<Character> text = new LinkedList<>(Arrays.asList('r','i','c','h','a','r','d'));
        assert !testClass.challenge26_isPalindrome(text);
    }

    @Test
    void test_challenge26_Pal2() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        LinkedList<Character> text = new LinkedList<>(Arrays.asList('a','a','a'));
        assert testClass.challenge26_isPalindrome(text);
    }

    @Test
    void test_challenge26_noPal2() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        LinkedList<Character> text = new LinkedList<>();
        assert !testClass.challenge26_isPalindrome(text);
    }

    @Test
    void test_challenge33() {
        SetOfStacks setOfStacks = new SetOfStacks(5);

        setOfStacks.push("Hallo");
        setOfStacks.push("Richard");
        setOfStacks.push(", wie");
        setOfStacks.push("gehts");
        setOfStacks.push("dir?");

        setOfStacks.push("Das finde");
        String text1 = setOfStacks.pop();
        assert 0 == setOfStacks.getCurrentPosInStack();
        assert 1 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text1 " +text1 + " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
        String text2 = setOfStacks.pop();
        assert 4 == setOfStacks.getCurrentPosInStack();
        assert 0 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text2 " +text2+ " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
        String text3 = setOfStacks.pop();
        assert 3 == setOfStacks.getCurrentPosInStack();
        assert 0 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text3 " +text3+ " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
    }

    @Test
    void test_challenge33_longer_push() {
        SetOfStacks setOfStacks = new SetOfStacks(5);

        setOfStacks.push("Hallo");
        setOfStacks.push("Richard");
        setOfStacks.push(", wie");
        setOfStacks.push("gehts");
        setOfStacks.push("dir?");

        setOfStacks.push("mir");
        setOfStacks.push("gehts");
        setOfStacks.push("gut, ");
        setOfStacks.push("danke!");
        setOfStacks.push("Und selbst?");

        setOfStacks.push("Cooler shit");
        setOfStacks.push("Hätte");
        setOfStacks.push("nie");

        String text1 = setOfStacks.pop();
        assert 2 == setOfStacks.getCurrentPosInStack();
        assert 2 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text1 " +text1 + " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
        String text2 = setOfStacks.pop();
        assert 1 == setOfStacks.getCurrentPosInStack();
        assert 2 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text2 " +text2+ " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
        String text3 = setOfStacks.pop();
        assert 0 == setOfStacks.getCurrentPosInStack();
        assert 2 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text3 " +text3+ " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
        String text4 = setOfStacks.pop();
        assert 4 == setOfStacks.getCurrentPosInStack();
        assert 1 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text4 " +text4+ " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
    }

    @Test
    void test_challenge33_shortThreshold() {
        SetOfStacks setOfStacks = new SetOfStacks(3);

        setOfStacks.push("Hallo");
        setOfStacks.push("Richard");
        setOfStacks.push(", wie");
        setOfStacks.push("gehts");
        setOfStacks.push("dir?");

        String text1 = setOfStacks.pop();
        assert 1 == setOfStacks.getCurrentPosInStack();
        assert 1 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text1 " +text1 + " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
        String text2 = setOfStacks.pop();
        assert 0 == setOfStacks.getCurrentPosInStack();
        assert 1 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text2 " +text2+ " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
        String text3 = setOfStacks.pop();
        assert 2 == setOfStacks.getCurrentPosInStack();
        assert 0 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text3 " +text3+ " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
    }

    @Test
    void test_challenge33_longThreshold() {
        SetOfStacks setOfStacks = new SetOfStacks(7);

        setOfStacks.push("Hallo");
        setOfStacks.push("Richard");
        setOfStacks.push(", wie");
        setOfStacks.push("gehts");
        setOfStacks.push("dir?");
        setOfStacks.push("Alles gut");
        setOfStacks.push("Danke!");

        String text1 = setOfStacks.pop();
        assert 6 == setOfStacks.getCurrentPosInStack();
        assert 0 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text1 " +text1 + " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
        String text2 = setOfStacks.pop();
        assert 5 == setOfStacks.getCurrentPosInStack();
        assert 0 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text2 " +text2+ " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
        String text3 = setOfStacks.pop();
        assert 4 == setOfStacks.getCurrentPosInStack();
        assert 0 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text3 " +text3+ " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
    }

    @Test
    void test_challenge33_popAt() {
        SetOfStacks setOfStacks = new SetOfStacks(2);

        setOfStacks.push("Hallo");
        setOfStacks.push("Richard");
        setOfStacks.push(", wie");
        setOfStacks.push("gehts");
        setOfStacks.push("dir?");
        setOfStacks.push("Alles gut");
        setOfStacks.push("Danke!");

        String text1 = setOfStacks.pop();
        assert 0 == setOfStacks.getCurrentPosInStack();
        assert 3 == setOfStacks.getCurrentIndxOfStack();
        System.out.println("text1 " +text1 + " pos: " + setOfStacks.getCurrentPosInStack() + " indx: " + setOfStacks.getCurrentIndxOfStack());
        assert "gehts".equals(setOfStacks.popAt(1));

    }

    @Test
    void test_challenge33_popAt_With_pop() {
        SetOfStacks setOfStacks = new SetOfStacks(2);

        setOfStacks.push("Hallo");
        setOfStacks.push("Richard");
        setOfStacks.push(", wie");
        setOfStacks.push("gehts");
        setOfStacks.push("dir?");
        setOfStacks.push("Alles gut");
        setOfStacks.push("Danke!");

        String text1 = setOfStacks.pop();
        assert text1.equals("Danke!");
        assert 0 == setOfStacks.getCurrentPosInStack();
        assert 3 == setOfStacks.getCurrentIndxOfStack();
        assert "gehts".equals(setOfStacks.popAt(1));
        assert "Alles gut".equals(setOfStacks.pop());
        assert "dir?".equals(setOfStacks.pop());
        assert  ", wie".equals(setOfStacks.pop());
    }

    @Test
    void test_challenge34_myQueue() {
        MyQueue myQueue = new MyQueue();
        assert myQueue.isEmpty();

        myQueue.add("Richard");
        myQueue.add("das geht ab");
        myQueue.add("wir");
        myQueue.add("feiern die");
        myQueue.add("ganze Nacht!");


        assert myQueue.size() == 3;
        assert "Richard".equals(myQueue.poll());
        assert "das geht ab".equals(myQueue.poll());
        assert "wir".equals(myQueue.poll());

        assert myQueue.contains("ganze Nacht!");
        assert !myQueue.contains("Richard");
        assert !myQueue.isEmpty();
    }

    @Test
    void test_challenge34_myQueue_few_elems() {
        MyQueue myQueue = new MyQueue();
        assert myQueue.isEmpty();

        myQueue.add("Richard");
        myQueue.add("das geht ab");

        assert myQueue.size() == 0;
        assert myQueue.contains("Richard");

        assertThrows(RuntimeException.class, () -> {
            myQueue.element();
        });

        assert !myQueue.isEmpty();
    }

    @Test
    void test_challenge35() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        Stack<Integer> unsortedStack = new Stack<>();
        unsortedStack.push(0);
        unsortedStack.push(3);
        unsortedStack.push(5);
        unsortedStack.push(5);
        unsortedStack.push(8);
        unsortedStack.push(9);
        unsortedStack.push(2);
        unsortedStack.push(10);
        unsortedStack.push(4);
        unsortedStack.push(20);
        unsortedStack.push(16);
        unsortedStack.push(14);
        unsortedStack.push(12);

        testClass.challenge35(unsortedStack);
    }

    @Test
    void teste_mergeWithSortedStack() {
        CrackingCodingInterview crackingCodingInterview = new CrackingCodingInterview();
        Stack<Integer> fromStack = new Stack<>();
        fromStack.push(3);
        fromStack.push(7);

        Stack<Integer> toStack = new Stack<>();
        toStack.push(1);

        toStack = crackingCodingInterview.mergeWithSortedStack(fromStack, toStack, 4);
        assert toStack.pop() == 1;
        assert toStack.pop() == 4;

    }

    @Test
    void test_challenge35_longer() {
        Stack<Integer> unsortedStack = new Stack<>();
        unsortedStack.push(10);
        unsortedStack.push(9);
        unsortedStack.push(8);
        unsortedStack.push(7);
        unsortedStack.push(6);

        CrackingCodingInterview crackingCodingInterview = new CrackingCodingInterview();
        Stack<Integer> result = crackingCodingInterview.challenge35(unsortedStack);

        int[] solution = new int[]{6,7,8,9,10};
        Iterator<Integer> iter = result.iterator();
        int i = 0;
        while (iter.hasNext()){
            int currentElem = iter.next();
            assert currentElem == solution[i];
            i++;
        }
    }

    @Test
    void test_challenge36_animalShelter() {
        AnimalShelter animalShelter = new AnimalShelter();
        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.CAT, "kiki"));
        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.CAT, "bibi"));
        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.DOG, "riff"));
        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.DOG, "wauwu"));
        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.CAT, "mauzi"));

        assert animalShelter.dequeueAny().getName().equals("kiki");
        assert animalShelter.dequeueDog().getName().equals("riff");
        assert animalShelter.dequeueAny().getName().equals("bibi");
    }

    @Test
    void test_challenge51() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
//        testClass.challenge51(528, 13, 1,4);
        testClass.challenge51(-528, 3, 1,2);
    }

    //======= MULTITHREADING ======= //

    @Test
    void test_multithreading() {
        CrackingCodingInterview testClass = new CrackingCodingInterview();
        testClass.testMultiThreading();
    }

    @Test
    void test_multiThreadingSimpleThread() throws InterruptedException {
        MeinThread thread3 = new MeinThread(5, "C");
        MeinThread thread4 = new MeinThread(5, "D");
        thread3.start();
        thread4.start();
        thread3.join(2000); // wartet auf diesen Thread!
    }

    @Test
    void test_multiThreading3() throws InterruptedException {
        CrackingCodingInterview testClass3 = new CrackingCodingInterview();
        testClass3.testMultithreading3();
    }

    @Test
    void test_stoppableThread() {
        MeinStoppableThread stoppableThread = new MeinStoppableThread();
        stoppableThread.setRunning(true);
        Thread thread = new Thread(stoppableThread);
        thread.start();
        try {
            Thread.sleep(3000);
            stoppableThread.setRunning(false);
        } catch (InterruptedException e){

        }
    }

    /*
    === lockATM with concurrent access ===
    Thread1 atm start
    Thread2 atm start
    Thread3 atm start
    ende sync meth mit thread: thread5
    new ballance is: 110
    new ballance is: 125
    new ballance is: 95
    Thread3 atm end
    new ballance is: 105
    new ballance is: 120
    new ballance is: 90
    Thread2 atm end
    new ballance is: 105
    new ballance is: 116
    new ballance is: 96
    new ballance is: 76
    Thread3 atm end
     */

    /*
    ======= falsche Synch ========
    === lockATM with concurrent access ===
    Thread1 atm start
    Thread2 atm start
    Thread3 atm start
    new ballance is: 110
    new ballance is: 115
    new ballance is: 110
    Thr2: 110
    Thr3: 115
    Thr1: 110
    new ballance is: 125
    new ballance is: 121
    new ballance is: 125
    Thr2: 125
    Thr3: 121
    Thr1: 125
    new ballance is: 95
    Thr2: 95
    Thread2 atm end
    new ballance is: 95
    new ballance is: 105
    Thr3: 105
    Thr1: 95
    Thread3 atm end
    new ballance is: 85
    Thr3: 85
    Thread3 atm end


    === ohne synchr ===
    === lockATM with concurrent access ===
    Thread1 atm start
    Thread2 atm start
    Thread3 atm start
    new ballance is: 110
    Thr1: 110
    new ballance is: 120
    Thr2: 120
    new ballance is: 135
    Thr3: 135
    new ballance is: 150
    Thr1: 150
    new ballance is: 165
    Thr2: 165
    new ballance is: 176
    Thr3: 176
    new ballance is: 146
    Thr1: 146
    Thread3 atm end
    new ballance is: 116
    Thr2: 116
    Thread2 atm end
    new ballance is: 96
    Thr3: 96
    new ballance is: 76
    Thr3: 76
    Thread3 atm end
     */
    @Test
    void test_mySyncThreads() throws InterruptedException {
        MeinSynchronizedThread thread1 = new MeinSynchronizedThread("thread1", new MeinObject());
        MeinSynchronizedThread thread2 = new MeinSynchronizedThread("thread2", new MeinObject());
        thread1.start();
        thread2.start();
        thread1.join();

        System.out.println("=== now try with same obj ===");
        MeinObject mySingleObj = new MeinObject();
        MeinSynchronizedThread thread3 = new MeinSynchronizedThread("thread1", mySingleObj);
        MeinSynchronizedThread thread4 = new MeinSynchronizedThread("thread2", mySingleObj);
        thread3.start();
        thread4.start();
        thread4.join();

        System.out.println("=== now try with static sync ===");
        MeinStaticSynchThread thread5 = new MeinStaticSynchThread("thread5");
        MeinStaticSynchThread thread6 = new MeinStaticSynchThread("thread6");
        thread5.start();
        thread6.start();
        thread6.join();


        System.out.println("=== lockATM with concurrent access ===");
        LockATM atm = new LockATM();
        Thread atmThr1 = new Thread(() -> {
            System.out.println("Thread1 atm start");
            System.out.println("Thr1: " + atm.deposit(10));
            System.out.println("Thr1: " +atm.deposit(15));
            System.out.println("Thr1: " +atm.withdraw(30));
            System.out.println("Thread3 atm end");
        });
        Thread atmThr2 = new Thread(() -> {
            System.out.println("Thread2 atm start");
            System.out.println("Thr2: " +atm.deposit(10));
            System.out.println("Thr2: " + atm.deposit(15));
            System.out.println("Thr2: " +atm.withdraw(30));
            System.out.println("Thread2 atm end");
        });
        Thread atmThr3 = new Thread(() -> {
            System.out.println("Thread3 atm start");
            System.out.println("Thr3: " +atm.deposit(15));
            System.out.println("Thr3: " + atm.deposit(11));
            System.out.println("Thr3: " + atm.withdraw(20));
            System.out.println("Thr3: " +atm.withdraw(20));
            System.out.println("Thread3 atm end");
        });
        atmThr1.start();
        atmThr2.start();
        atmThr3.start();
        atmThr3.join();
        atmThr2.join();
        atmThr1.join();
    }

    /*
    ==== with notify() ====
    t2 joined waiterMethod
    t1 joined notifierMethod (t1 hat t2 befreit)
    t1 left notifierMethod
    t1 joined waiterMethod
    t3 joined waiterMethod
    t2 left waiterMethod
    t2 joined notifierMethod
    t2 left notifierMethod (t2 hat t1 befreit, t3 vergammelt für immer im lock modus)
    t2 did related work
    t1 left waiterMethod
    t1 did related work

    ==== with notifyAll() ====
    t2 joined waiterMethod
    t3 joined waiterMethod
    t1 joined notifierMethod
    t1 left notifierMethod (hat alle befreit)
    t1 joined waiterMethod
    t2 left waiterMethod
    t2 joined notifierMethod
    t2 left notifierMethod
    t3 left waiterMethod
    t3 joined notifierMethod
    t3 left notifierMethod
    t3 did related work
    t2 did related work
    t1 left waiterMethod
    t1 did related work
     */
    @Test
    void test_waitAndNotify() throws InterruptedException {
        MyThing lockObject = new MyThing();

        Thread t1 = new Thread(() -> {
            try {
                lockObject.notifierMethod("t1");
                lockObject.waiterMethod("t1");
                lockObject.relatedMethod("t1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                lockObject.waiterMethod("t2");
                lockObject.notifierMethod("t2");
                lockObject.relatedMethod("t2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                lockObject.waiterMethod("t3");
                lockObject.notifierMethod("t3");
                lockObject.relatedMethod("t3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        t1.start();
        t3.start();
        t1.join();
        t3.join();
    }

    @Test
    void test_producer_consumer() {
        Producer producer = new Producer();
        Consumer consumer = new Consumer(producer);

        Thread p1 = new Thread(producer);
        Thread p2 = new Thread(producer);
        Thread p3 = new Thread(producer);
        Thread c1 = new Thread(consumer);
        p1.start();
        p2.start();
        p3.start();
        c1.start();
        try {
            c1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_readWriterLockDemo() throws InterruptedException {
        ReadWriterDemoWithWaitingTime demo = new ReadWriterDemoWithWaitingTime();
        demo.doStuff();
        Thread.sleep(5000);
        demo.setDone(true);
    }

    @Test
    void test_readWriterLockWithoutWaiting() throws InterruptedException {
        ReadWriterDemoWithoutWaitingTime demo = new ReadWriterDemoWithoutWaitingTime();
        demo.doStruff();
        Thread.sleep(5000);
        demo.setDone(true);
    }

    @Test
    void test_aufgabe951() {
        Tasks threadingTasks = new Tasks();
        threadingTasks.aufgabe951();
    }

    @Test
    void test_aufgabe952() {
        Tasks threadingTasks = new Tasks();
        threadingTasks.main(null);
    }

    @Test
    void test_aufgabe954() {
        Tasks threadingTasks = new Tasks();
        threadingTasks.aufgabe954();
    }

    /* === richtig ===
    neuer Betrag für t1 = 110 einzahlen: 10
    neuer Betrag für t3 = 121 einzahlen: 11
    neuer Betrag für t2 = 126 einzahlen: 5
    neuer Betrag für t3 = 148 einzahlen: 22
    neuer Betrag für t1 = 168 einzahlen: 20
    neuer Betrag für t3 = 176 einzahlen: 8
    neuer Betrag für t2 = 191 einzahlen: 15
    neuer Betrag für t2 = 201 einzahlen: 10
    Endbetrag: 201 Anz an Additionen: 8


     === falsch ===
     neuer Betrag für t1 = 110 einzahlen: 10
    neuer Betrag für t2 = 105 einzahlen: 5
    neuer Betrag für t3 = 111 einzahlen: 11
    neuer Betrag für t2 = 126 einzahlen: 15
    neuer Betrag für t3 = 133 einzahlen: 22
    neuer Betrag für t1 = 131 einzahlen: 20
    neuer Betrag für t2 = 141 einzahlen: 10
    neuer Betrag für t3 = 139 einzahlen: 8
    Endbetrag: 141 Anz an Additionen: 8
     */
    @Test
    void test_aufgabe955() {
        Tasks threadingTasks = new Tasks();
        try {
            threadingTasks.aufgabe955();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    richard setzt sich an Tisch
    olga setzt sich an Tisch
    lidia setzt sich an Tisch
    waldemar setzt sich an Tisch
    tanja setzt sich an Tisch
    henrietta setzt sich an Tisch
    tanja hat ein Essstäbchen
    ... warten unendlich
     */
    @Test
    void test_challenge153() {
        Challenges challenge = new Challenges();
        challenge.challenge153();
    }

    @Test
    void test_challenge155() {
        Challenges challenge = new Challenges();
        challenge.challenge155();
    }
}