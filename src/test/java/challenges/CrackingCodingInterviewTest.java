package challenges;

import org.junit.jupiter.api.Test;

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
}