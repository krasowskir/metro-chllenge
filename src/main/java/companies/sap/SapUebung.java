package companies.sap;

import java.nio.CharBuffer;
import java.util.*;
import java.util.stream.Collectors;

/*
    supercalifragilisticexpialidocious  -> f
    könnensienochfolgendesversuchenkö -> i
    In a given string find the first character that occurs only once
*/
public class SapUebung {
    HashMap<Character, Integer> charAmount = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("unique first char in: könnensienochfolgendesversuchenkö");
        System.out.println("erster eindeutige Buchstabe: " + findFirstUniqueWitStreams("könnensienochfolgendesversuchenkö")); //,könnensienochfolgendesversuchenkö -> i | supercalifragilisticexpialidocious -> f

    }

    private static char findFirstUniqueCharInString(String fromString){
        Character foundUniqueChar = null;
        for (char ch : fromString.toCharArray()){

            boolean charUnique = isUnique(fromString, ch);
            if (charUnique){
                foundUniqueChar = ch;
                return foundUniqueChar;
            }
        }
        return foundUniqueChar;

    }

    private static boolean isUnique(String fromString, char ch) {
        int count = 0;
        for (char tmpChar : fromString.toCharArray()){
            if (ch == tmpChar){
                count++;
                if (count > 1){
                    return false;
                }
            }
        }
        return true;
    }

    private static void findFirstUniqueWithoutStreams(String fromStringg){
//		Hashtable<Character, Integer> amountOfChars = new Hashtable<>(); //KEINE REIHENFOLGE!!! -> g
        HashMap<Character, Integer> orderedChars = new HashMap<>();
        Set<Character> charSet = new TreeSet<>();
        TreeMap<Character, Integer> charMap = new TreeMap<>();


        for (Character c : fromStringg.toCharArray()){
            charSet.add(c);
        }
        charSet.forEach(System.out::print);
        System.out.println("\n ================");

        for (Character c : fromStringg.toCharArray()){
            if (charMap.containsKey(c)){
                int val = charMap.get(c).intValue();
                charMap.replace(c, ++val);
            } else {
                charMap.put(c, 1);
            }
        }
        charMap.forEach((a, b) -> System.out.println("Buchstabe: " + a + " anz: " + b.toString()));

        for (Character c : fromStringg.toCharArray()){
            if (orderedChars.containsKey(c)){
                int val = orderedChars.get(c).intValue();
                orderedChars.replace(c, ++val);
            } else {
                orderedChars.put(c, 1);
            }
        }
        orderedChars.forEach((a,b) -> System.out.println("HashMap Buchstabe: " + a + " anz: " + b.toString()));

        List<Character> linkedListChars = CharBuffer.wrap(fromStringg.toCharArray()).chars().mapToObj(i -> (char)i).collect(Collectors.toList());
        LinkedList<Character> lLC = new LinkedList<>(linkedListChars);
        lLC.descendingIterator();
    }

    private static char findFirstUniqueWitStreams(String fromString){
        LinkedHashMap<Character, Integer> lCharMap = new LinkedHashMap<>();

        for (Character c : fromString.toCharArray()){
            if (lCharMap.containsKey(c)){
                int val = lCharMap.get(c);
                lCharMap.replace(c, ++val);
            } else {
                lCharMap.put(c, 1);
            }
        }

        return lCharMap.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .get()
                .getKey();
    }
}