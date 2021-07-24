package challenges;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
}