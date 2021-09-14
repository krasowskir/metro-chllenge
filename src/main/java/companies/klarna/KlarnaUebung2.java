package companies.klarna;

public class KlarnaUebung2 {
    //    public static String numberToOrdinal(Integer number) {
//        char[] digitsCharArr = String.valueOf(number).toCharArray();
//        StringBuilder strB = new StringBuilder();
//
//        if (isSingleDigitNumber(digitsCharArr)) {
//            return lookupSuffixForSingleDigit(number);
//
//        } else if (isTeenNumber(digitsCharArr)) {
//            return strB.append(digitsCharArr).append("th").toString();
//
//        } else {
//            return lookupSuffixForOther(digitsCharArr);
//        }
//    }
//
//    private static boolean isSingleDigitNumber(char[] numb) {
//        return numb.length == 1;
//    }
//
//    private static boolean isTeenNumber(char[] numb) {
//        int numLength = numb.length;
//        if (numLength > 1) {
//            if (numb[numLength - 2] == '1') {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static String lookupSuffixForSingleDigit(int singleDigit) {
//        switch (singleDigit) {
//            case 1:
//                return "1st";
//            case 2:
//                return "2nd";
//            case 3:
//                return "3rd";
//            default:
//                return singleDigit + "th";
//        }
//    }
//
//    private static String lookupSuffixForOther(char[] digit) {
//        StringBuilder strB = new StringBuilder();
//        strB.append(digit);
//        int last = digit.length - 1;
//        switch (digit[last]) {
//            case '1':
//                return strB.append("st").toString();
//            case '2':
//                return strB.append("nd").toString();
//            case '3':
//                return strB.append("rd").toString();
//            default:
//                return strB.append("th").toString();
//        }
//    }

    public static String numberToOrdinal(Integer number) {
        return number == 0 ? "0" : handleValidUserInput(number);
    }

    private static String handleValidUserInput(Integer number) {
        char[] digitsCharArr = String.valueOf(number).toCharArray();
        StringBuilder strB = new StringBuilder();

        if (isSingleDigitNumber(digitsCharArr)) {
            return lookupSuffixForSingleDigit(number);

        } else if (isTeenNumber(digitsCharArr)) {
            return strB.append(digitsCharArr).append("th").toString();

        } else {
            return lookupSuffixForOther(digitsCharArr);
        }
    }

    private static boolean isSingleDigitNumber(char[] numb) {
        return numb.length == 1;
    }

    private static boolean isTeenNumber(char[] numb) {
        int numLength = numb.length;
        if (numLength > 1) {
            if (numb[numLength - 2] == '1') {
                return true;
            }
        }
        return false;
    }

    private static String lookupSuffixForSingleDigit(int singleDigit) {
        switch (singleDigit) {
            case 1:
                return "1st";
            case 2:
                return "2nd";
            case 3:
                return "3rd";
            default:
                return singleDigit + "th";
        }
    }

    private static String lookupSuffixForOther(char[] digit) {
        StringBuilder strB = new StringBuilder();
        strB.append(digit);
        int last = digit.length - 1;
        switch (digit[last]) {
            case '1':
                return strB.append("st").toString();
            case '2':
                return strB.append("nd").toString();
            case '3':
                return strB.append("rd").toString();
            default:
                return strB.append("th").toString();
        }
    }
}
