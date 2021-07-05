package challenges;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/*
    Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside
    the signed 32-bit integer range [-231, 231 - 1], then return 0.
    Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

    Example 1:
    Input: x = 123
    Output: 321

    Example 2:
    Input: x = -123
    Output: -321

    Example 3:
    Input: x = 120
    Output: 21

     */
public class ReverseInt {


    public int reverse(int x) {
        char[] textInChars = Integer.toString(x).toCharArray();
        Character signed = null;
        int fromInd = 0;
        if (!Character.isDigit(textInChars[0])){
            signed = textInChars[0];
            fromInd = 1;
        }

        String numWithoutSign = String.valueOf(Arrays.copyOfRange(textInChars, fromInd, textInChars.length));
        try {
            Integer.parseInt(new StringBuilder(numWithoutSign).reverse().toString());
        } catch (NumberFormatException ne) {
            return 0;
        }
        char[] reversedTextInChars = new StringBuilder(numWithoutSign).reverse().toString().toCharArray();

        char[] newNumber = Arrays.copyOfRange(reversedTextInChars, leadingZeros(reversedTextInChars), reversedTextInChars.length);

        return Integer.parseInt(signed != null ? signed + String.valueOf(newNumber) : String.valueOf(newNumber));
    }

    private int leadingZeros(char[] textInChars){
        int i = 0, num;
        try {
            while (i < textInChars.length){
                num = Integer.parseInt(String.valueOf(textInChars[i]));
                if (num == 0){
                    i++;
                } else {
                    return i;
                }
            }
        } catch (NumberFormatException ne){
            return 0;
        }
        return 0;
    }
}
