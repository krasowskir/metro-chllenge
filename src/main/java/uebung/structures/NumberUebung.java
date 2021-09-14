package uebung.structures;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;

public class NumberUebung {
    public static final Double data[] = {0d, 1d, 22d/7, 100.2345678, 2034588.12345};

    public void testBignumbers(){
//        BitSet fromNumber = BitSet.valueOf("01101".getBytes());
        Integer fromNumber = 15;
        Integer number = Integer.parseInt("01101", 2);
        String toNumber = Integer.toString(fromNumber, 2);
        System.out.println("number in dual: " + number);
        System.out.println("toNumber in dual: " + toNumber);
        System.out.println("toNumber in hex: " + Integer.toHexString(fromNumber));

        System.out.println("long max: " + Double.MAX_VALUE);
    }

    public void testParallelWork(){
        long fromTime = System.nanoTime();
        int result = IntStream.range(0,10000000)
                .map(i -> i * i)
                .sum(); //2+3+4+5+6+7+8+9+10+11
//        int result = IntStream.range(0,10000000)
//                .parallel()
//                .map(i -> i * i)
//                .sum();

        long toTime = System.nanoTime();
        System.out.println("sum time: " + String.valueOf(toTime - fromTime));
//        System.out.println("sum time: " + String.valueOf(toTime - fromTime));
        System.out.println("Ergebnis: " + result);
    }

    /*
    2. The code in Example 6-11 multiplies every number in a list together and multiplies the result by 5. This works fine sequentially, but has a bug when running in parallel. Make the code run in parallel using streams and fix the bug.
    Example 6-11. A buggy way of multiplying every number in a list together and multiplying the result by 5
    public static int multiplyThrough(List<Integer> linkedListOfNumbers) { return linkedListOfNumbers.stream()
                          .reduce(5, (acc, x) -> x * acc);
    }
    3. The code in Example 6-12 also calculates the sum of the squares of numbers in a list. You should try to improve the performance of this code without degrading its quality. I’m only looking for you to make a couple of simple changes.
    Example 6-12. Slow implementation of summing the squares of numbers in a list
    public int slowSumOfSquares() {
    return linkedListOfNumbers.parallelStream()
    }
    .map(x->x*x)
    .reduce(0, (acc, x) -> acc + x);
     */
    public void testParallelWorkExercise2(){
        int[] myNumbers = {2,3,4,5,6};

        int result = Arrays.stream(myNumbers)
                .parallel()
                .peek(i -> System.out.println(i))
                .reduce(5, (acc, b) -> acc * b);

        int correctResult = 5 * Arrays.stream(myNumbers)
                .parallel()
                .peek(i -> System.out.println(i))
                .reduce(1, (acc, b) -> acc * b);
//        int result = IntStream.range(1, myNumbers.length)
//                .sequential()
//                .peek(i -> System.out.println(i))
//                .reduce(5, (a,b) -> a * b);
        System.out.println("buggy result " + result);
        System.out.println("correct result " + correctResult);

        Integer[] tmpArr = new Integer[10000000];
        Integer[] myNumbersArr = IntStream.range(0,10000000)
                                .mapToObj(i -> Integer.valueOf(i))
                                .toArray(i -> tmpArr);

        long fromTime = System.nanoTime();
//        int sumResult = Arrays.stream(myNumbersArr)
//                .map(i -> i + i)
//                .reduce(0, (acc, b) -> acc + b);
        int sumResult = Arrays.stream(myNumbersArr)
                .mapToInt(i -> i)
                .map(i -> i+i)
                .sum();
        long toTime = System.nanoTime();
        System.out.println("Erg: " + sumResult);
        System.out.println("Zeit: " + String.valueOf((toTime - fromTime) /1000000) +" ms");
    }

    public void testNumbers() {
        float a = 0.3f;
        System.out.println("multiply with 3: " + a * 3);
        System.out.println("divide by 3: " + a / 3);
        System.out.println("again multiply with 3: " + (a / 3) * 9);
    }

    public void testIfValidNumber() {
        String[] toTestNumbrs = {"3000", "20L"};


        for (String elem : toTestNumbrs) {
            try {
                Double.parseDouble(elem);
                System.out.println(String.format("%s is a valid number!", elem));
            } catch (NumberFormatException nfe) {
                System.out.println(String.format("%s is not a valid number!", elem));
            }
        }
    }

    public void testStoreLargerNumberInSmaller() {
        String[] toTestNumbrs = {"30000000000000.50000000", "200000.2000"};


        for (String elem : toTestNumbrs) {

            double largeNum = Double.parseDouble(elem);
            largeNum = largeNum * largeNum;
            float smallNum = (float) largeNum;
            System.out.println(String.format("%f is small Num", smallNum));
            System.out.println(String.format("%f is small Num", largeNum));
        }
    }

    public void testAutoboxing(){
        int basicNumb = 42;
        Integer intObj = Integer.valueOf(42);
        System.out.println("numbr basic: " + basicNumb + " instanceOf: " + basicNumb );
        System.out.println("numbr boxed: " + intObj + " instanceOf: " + intObj.getClass() );
    }

    public void testFraction(){
        double result = 2/3 * 5;
        System.out.println("2/3 * 5 = " + result);

        double result2 = 2d/3d * 5;
        System.out.println("2d/3d * 5 = " + result2);

        double result3 = 5/3 * 2;
        System.out.println(" 5/3 * 2 = " + result3);

        double result4 = 5d/3 * 2;
        System.out.println(" 5/3d * 2 = " + result4);
    }

    public void testForInfinity(){
        double e1 = 123;
        double e2 = 0;

        int a = 42;

        System.out.println("e1/e2 is infinite ? ==> " + Double.isInfinite(e1/e2));
        System.out.println("e1/e2 is Nan ? ==> " + Double.isNaN(e1/e2));
        System.out.println("a/e2 is infinite ? ==> " + Double.isInfinite(a/e2));
        System.out.println("1 / 0 infinity? ==> " + ((1/0d) == Double.POSITIVE_INFINITY));
    }

    public void testForEquality(){
        double da = 3.0d * 0.33333333333;
        double db = 0.99999999999;


        final double EPSILON = 0.0001d;
        System.out.println("da == db ? -> " + (da == db));
        System.out.println("da same db with EPSILON ? -> " + (Math.abs(da - db) < EPSILON));

        double dc = Double.NaN;
        double dd = Double.NaN;
        System.out.println("dc == dd ? -> " + (dc == dd));

    }

    public void testRound(){
        double a = 3.64999d;
        double b = 0.34999d;

        System.out.println("rounding a = " + Math.round(a));
        System.out.println("rounding b = " + Math.round(b));
    }

    public void testNumberFormating(){
        NumberFormat instance = NumberFormat.getInstance(Locale.GERMAN);
        instance.setMinimumIntegerDigits(1);
//        instance.setMaximumIntegerDigits(3);
        instance.setMinimumFractionDigits(2);
        instance.setMaximumFractionDigits(3);

        for (double elem : data){
            System.out.println("elem: " + elem + " format-> " + instance.format(elem));
        }

    }
}


