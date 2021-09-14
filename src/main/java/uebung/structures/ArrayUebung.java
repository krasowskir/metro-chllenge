package uebung.structures;

import java.util.Arrays;

public class ArrayUebung {
    public void starteUebung(){
        int[] zahlen = new int[] {1,2,3,4,5};
        int[] zahlen2 = new int[] {8,2,9,3,16};

        int[] shiftZahlen = new int[zahlen.length];

        int[][] mehrdimensionaleZahle = new int[][] {{0,1}, {2,3}, {4,5}, {6,7}, {8,9}};
        for (int zahl : zahlen){
            System.out.println("zahl: " + zahl);
            System.out.println("mehrdim Zahl: " + mehrdimensionaleZahle[zahl-1][0]);
        }
        System.out.println("laenge: " + zahlen.length);

        System.out.println("binaere Suche: " + Arrays.binarySearch(zahlen,4));
        System.out.println("comparison arrays: " + Arrays.compare(zahlen2, zahlen));
        Arrays.sort(zahlen2);
        System.out.println(Arrays.toString(Arrays.copyOfRange(zahlen, 1, zahlen.length)));
        System.out.println("comparison arrays: " + Arrays.toString(zahlen2));
//        System.out.println("uebung aufgabe: " + Arrays.toString(solution(new int[] {2,3,4,5}, 3) ));
        System.out.println("frog solution: " + solutionFrog(5,1000000, 25) + " jumps");
    }

    public int[] solution(int[] myArrays, int shiftFactor) {
        // write your code in Java SE 8
        int[] inputArray = myArrays;
        for (int i = 0; i < shiftFactor; i++){
            inputArray = shiftRightByOne(inputArray);
        }
        return inputArray;
    }

    public int[] shiftRightByOne(int[] arrToShift){
        //saving last elem because this will be moved
        int tmpLastElem = arrToShift[arrToShift.length - 1];

        //create a helper array, that will be filled with new values
        int[] tmpArr = new int[arrToShift.length];

        tmpArr[0] = tmpLastElem;

        //copying the values, that will stay in this order
        int[] shiftedValues = Arrays.copyOfRange(arrToShift, 0, arrToShift.length -1 );

        for (int i = 1; i < arrToShift.length; i++){
            tmpArr[i] = shiftedValues[i-1];
        }

        return tmpArr;
    }

    public int solutionFrog(int X, int Y, int D) {
        // write your code in Java SE 8
        if (X == Y){
            return 0;
        }
        if (X > Y || X < 1 || Y < 1 || D < 1){
            throw new IllegalArgumentException("X cannot be bigger than Y");
        }
        int tmpPosition = Y - X;
        int jumps;
        int rest = calculateRest(tmpPosition, D);
        if (rest > 0){
            int base = calculateBase(tmpPosition, rest);
            jumps = base / D;
            jumps++;
        } else {
            jumps = tmpPosition / D;
        }

        return jumps;
    }

    public int calculateRest(int fromNum, int divisor){
        return fromNum % divisor;
    }
    public int calculateBase(int fromNum, int rest){
        return fromNum - rest;
    }
}
