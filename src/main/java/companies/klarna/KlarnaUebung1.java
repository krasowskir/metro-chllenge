package companies.klarna;

import java.nio.CharBuffer;
import java.util.stream.Collector;

public class KlarnaUebung1 {
    private static final int MIN_TEXT_LENGTH = 6;

    public static String maskify(String creditCardNumber) {
        // Add code here
        if (creditCardNumber.length() == 0){
            return "";
        } else if (creditCardNumber.length() < MIN_TEXT_LENGTH){
            return creditCardNumber;
        } else {
            return handleValidInput(creditCardNumber);
        }
    }

    private static String handleValidInput(String creditCardNumber){
        final CharSequence suffix = creditCardNumber.subSequence(creditCardNumber.length() - 4, creditCardNumber.length());
        Collector<Character, StringBuilder, String> characterMaskifier = Collector.of(
                StringBuilder::new,
                (a , b) -> {
                    //first value
                    if (a.length() == 0){
                        a.append(b);
                    }
                    else if (Character.isDigit(b)){
                        a.append('#');
                    } else {
                        a.append(b);
                    }
                },
                (a,b) -> {
                    a.append(b);
                    return a;
                },result -> {
                    int length = result.length();
                    result.replace(length - 4, length, suffix.toString());
                    return result.toString();
                }
        );

        String result = CharBuffer.wrap(creditCardNumber.toCharArray())
                .chars()
                .mapToObj(i -> (char)i)
                .collect(characterMaskifier);
        return result;
    }
}
