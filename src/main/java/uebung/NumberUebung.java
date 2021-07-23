package uebung;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.text.ChoiceFormat;
import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.IntStream;

public class NumberUebung {

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
}
