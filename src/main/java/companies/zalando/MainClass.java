package companies.zalando;

public class MainClass {

    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        App start = new App();
//        String binaryNumber = start.convertToBin(1032);
//        int zeroGap = start.calculateBiggestGapLength(binaryNumber);
//        System.out.println("Gap length: " + zeroGap);

//        MeineArrayUebung uebung = new MeineArrayUebung();
//        uebung.starteUebung();
//        MeineStringUebung uebung2 = new MeineStringUebung();
//        uebung2.testStrTokenizer();
//        uebung2.testStrBuilder();
//        uebung2.reverseWords();

//        ComplexUebung complexUebung = new ComplexUebung();
//        Random rand = new Random();
//        int[] randArr = rand.ints(10,1,12).toArray();
//        int[] solArr = complexUebung.solution(10, randArr);
//        Testuebung testuebung = new Testuebung();
//        HashMap<Character, Integer> lettersCount = testuebung.countLettersInString("ccaaffddecee"); //"example" -> 4, "aaaabbbb" -> 1
//        System.out.println("letters map: " + lettersCount.toString());
//        HashMap<Character, Integer> filteredOutLettersMap = testuebung.filterOutEmptyChars(lettersCount);
//        System.out.println("filtered out map: " + filteredOutLettersMap.toString());
//        List<Integer> finalNumbrs = testuebung.sortMap(filteredOutLettersMap);
//        System.out.println(finalNumbrs.toString());


//        KlarnaUebung klarnaUebung = new KlarnaUebung();
//        System.out.println("length bigger than 5: " + klarnaUebung.hasCorrectSize("RichardSkobidoo"));
//        System.out.println("test " + "4556364607935616");
//        System.out.println("maskified: " + klarnaUebung.maskify("4556364607935616"));
//
//        System.out.println("test2 " + "4556-3646-0793-5616");
//        System.out.println("maskified: " + klarnaUebung.maskify("4556-3646-0793-5616"));
//
//        System.out.println("test3 " + "64607935616");
//        System.out.println("maskified: " + klarnaUebung.maskify("64607935616"));
//
//        System.out.println("test4 " + "ABCD-EFGH-IJKLM-NOPQ");
//        System.out.println("maskified: " + klarnaUebung.maskify("ABCD-EFGH-IJKLM-NOPQ"));
//
//        System.out.println("test5 " + "A1234567BCDEFG89HI");
//        System.out.println("maskified: " + klarnaUebung.maskify("A1234567BCDEFG89HI"));
//
//        System.out.println("test6 " + "12345");
//        System.out.println("maskified: " + klarnaUebung.maskify("12345"));
//
//        System.out.println("test7 " + "");
//        System.out.println("maskified: " + klarnaUebung.maskify(""));
//
//        System.out.println("test8 " + "Skippy");
//        System.out.println("maskified: " + klarnaUebung.maskify("Skippy"));


//        KlarnaUebungChallenge2 klarnaUebung2 = new KlarnaUebungChallenge2();

//        System.out.println("test1: " + 11 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(11));
//        System.out.println("test2: " + 12 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(12));
//        System.out.println("test3: " + 13 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(13));
//        System.out.println("test4: " + 1 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(1));
//        System.out.println("test5: " + 2 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(2));
//        System.out.println("test6: " + 3 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(3));
//        System.out.println("test7: " + 4 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(4));
//        System.out.println("test8: " + 120 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(120));
//        System.out.println("test9: " + 82 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(82));
//        System.out.println("test10: " + 1143 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(1143));
//        System.out.println("test11: " + 1111 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(1111));
//        System.out.println("test12: " + 1212 + " - results in: " + KlarnaUebungChallenge2.numberToOrdinal(1212));


//        KlarnaUebung3 klarnaUebung3 = new KlarnaUebung3();
//        System.out.println("test1: 5 1 2 + 4 * + 3 - =>" + klarnaUebung3.evaluate("5 1 2 + 4 * + 3 -"));
//        System.out.println("test2: 10000 123 + =>" + klarnaUebung3.evaluate("10000 123 +"));
//        System.out.println("test2: 1 2 3.5 =>" + klarnaUebung3.evaluate("1 2 3.5"));
//        System.out.println("test2: 2 0 + 12 * 6 / =>" + klarnaUebung3.evaluate("2 0 + 12 * 6 /"));
//        System.out.println("test2: 4 2 / =>" + klarnaUebung3.evaluate("4 2 /"));
//        System.out.println("test2: 1 3 - =>" + klarnaUebung3.evaluate("1 3 -"));

//        Datumsuebung datumsuebung = new Datumsuebung();
//        datumsuebung.printDate();

//        NumberUebung numberUebung = new NumberUebung();
//        numberUebung.testIfValidNumber();
//        numberUebung.testNumberFormating();

    }

    public String convertToBin(int fromNumber) {
        String binaryNumber = Integer.toBinaryString(fromNumber);
//        System.out.println("binary form: " + binaryNumber);
        return binaryNumber;
    }

    public int calculateBiggestGapLength(String binNumb) {
        char[] bitArr = binNumb.toCharArray();

        int maxZeroLength = 0;

        //access last elem (length -1) because in sub iteration access i + 1
        for (int i = 0; i < bitArr.length - 1; i++) {
            if (bitArr[i] == '1' && bitArr[i + 1] == '0') {
                maxZeroLength = findMaxZeroGapFrom(bitArr, i, maxZeroLength);
            }
        }
        return maxZeroLength;
    }

    private int findMaxZeroGapFrom(char[] bitArr, int indexOfOne, int maxZeroLength) {
        int tmpZeroLength = 0;
        for (int z = indexOfOne + 1; z < bitArr.length; z++) {
            if (bitArr[z] == '0') {
                tmpZeroLength++;
                if (z == bitArr.length - 1 && bitArr[z] == '1'){
                    maxZeroLength = determineMaxOf(maxZeroLength, tmpZeroLength);
                }
            } else {
                maxZeroLength = determineMaxOf(maxZeroLength, tmpZeroLength);
                break;
            }
        }
        return maxZeroLength;
    }

    private int determineMaxOf(int maxZeroLength, int tmpZeroLength) {
        if (maxZeroLength < tmpZeroLength) {
            return tmpZeroLength;
        }
        return maxZeroLength;
    }
}
