package challenges;

import java.nio.CharBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
Given a string s, find the length of the longest substring without repeating characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Example 4:
Input: s = ""
Output: 0


    0 <= s.length <= 5 * 104
    s consists of English letters, digits, symbols and spaces.

 */
public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {

        if (s.equals(" ")) {
            return 1;
        }
        AtomicInteger tmpBuffer = new AtomicInteger(0);

        Collector<Character, LinkedList<Character>, Integer> myConsumeCharCol = Collector.of(
                LinkedList::new,
                (a, b) -> {
                    if (a.isEmpty()) {
                        a.add(b);
                    } else if (a.contains(b)) {
                        if (a.getFirst() == b){
                            a.removeFirst();
                        } else {
                            if (a.getLast() == b && tmpBuffer.get() < a.size()){
                                tmpBuffer.set(a.size());
                            }
                            removeUntilFirst(a, b);
                        }
                        a.add(b);
                    } else {
                        a.add(b);
                    }
                    if (tmpBuffer.get() < a.size()) {
                        tmpBuffer.set(a.size());
                    }
                },
                (a, b) -> a,
                result -> tmpBuffer.get()
        );

        return CharBuffer.wrap(s.toCharArray())
                .chars()
                .mapToObj(i -> (char) i)
                .sequential()
                .collect(myConsumeCharCol);

    }

    private void removeUntilFirst(LinkedList<Character> setCharsInText1, Character a) {

        while (setCharsInText1.getFirst() != a) {
            setCharsInText1.removeFirst();
        }
        setCharsInText1.removeFirst();
    }

}
